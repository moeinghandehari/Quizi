# Quizi Compose Multiplatform Application www.quizi.org

You can open the applications by running the following Gradle tasks:

**Desktop**
```bash
:composeApp:run
```
**Web**
```bash
:composeApp:wasmJsBrowserDevelopmentRun
```

**Server**

&emsp;Package         
```bash
./gradlew installDist
```
&emsp;EXECUTABLE_PATH
```bash
PATH_TO_PROJECT/server/build/install/server/bin/
```
&emsp;Copy files to the server (scp needed)
```bash
scp -r PATH_TO_LOCAL_FOLDER USERNAME@SERVER_IP:~
scp -r ~/StudioProjects/Quizi/server azureuser@131.189.200.54:~
```
&emsp;Run             
```bash
./server
```
&emsp;Run in background using nohup <br />
&emsp;&emsp;1. Install nohup if not already installed
```bash
sudo apt update
```
```bash
sudo apt install nano
```
&emsp;&emsp;2. Run using nohup
```bash
nohup ./server > ~/ktor.log 2>&1 &
```
&emsp;Server logs
```bash
tail -f ~/ktor.log
```
&emsp;Verify if server is running
```bash
ps aux | grep server
```
&emsp;Stop server (Find PID using previous command)
```bash
kill <PID>
```
&emsp;&emsp;Ktor Service Status
```bash
sudo systemctl status quizi-server
```
&emsp;&emsp;Ktor Service Configs
```bash
sudo nano /etc/systemd/system/quizi-server.service
```
&emsp;&emsp;Reload Service Configs
```bash
sudo systemctl daemon-reload
```
&emsp;&emsp;Restart Ktor Service
```bash
sudo systemctl restart quizi-server.service
```

Update WebApp 
```bash
./gradlew clean
./gradlew wasmJsBrowserDistribution
sudo rm -r /var/www/html/*
scp -r ~/StudioProjects/Quizi/composeApp/build/dist/wasmJs/productionExecutable azureuser@131.189.200.54:~
sudo mv ~/productionExecutable/* /var/www/html/
```

New web commands for WebPack
```bash
./gradlew clean
./gradlew wasmJsBrowserProductionWebpack
sudo rm -r /var/www/html/*
scp -r ~/StudioProjects/Quizi/composeApp/build/dist/wasmJs/productionWebpack azureuser@131.189.200.54:~
sudo mv ~/productionExecutable/* /var/www/html/
```
Server is currently running on an Azure ubuntu VM.

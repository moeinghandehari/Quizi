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

Server is currently running on an Azure ubuntu VM.

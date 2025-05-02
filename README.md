You can open the applications by running the following Gradle tasks:

Desktop             `:composeApp:run`
Web                 `:composeApp:wasmJsBrowserDevelopmentRun`

Server:
    Package         
                    `./gradlew installDist`
    EXECUTABLE_PATH:
                    `PATH_TO_PROJECT/server/build/install/server/bin/`
    Copy files to the server (scp needed)
                    `scp -r PATH_TO_LOCAL_FOLDER USERNAME@SERVER_IP:~`
    Run             
                    `./server`
    Run in background using nohup
        1. Install nohup if not already installed
                    `sudo apt update`
                    `sudo apt install nano`
        2. Run using nohup
                    `nohup ./server > ~/ktor.log 2>&1 &`
    Server logs
                    `tail -f ~/ktor.log`
    Verify if server is running
                    `ps aux | grep server`
    Stop server (Find PID using previous command)
                    `kill <PID>`

Server is currently running on an Azure VM :)
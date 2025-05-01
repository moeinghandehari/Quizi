You can open the applications by running the following Gradle tasks:

Desktop             `:composeApp:run`
Web                 `:composeApp:wasmJsBrowserDevelopmentRun`

Server:
    Package         `./gradlew installDist`
        Generates & saves executable under /server/build/install/server/bin/
    Run             `./server`
        Inside /server/build/install/server/bin/

Server is currently running on an Azure VM :)
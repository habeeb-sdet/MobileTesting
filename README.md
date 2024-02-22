# Mobile Test Framework - IOS/Android


## About

Mobile Test Framework to automate test cases in Ios & Android devices

Powered with
    - Appium
    - Selenium Grid capability
    - Cloud Service Provider - Sauce Labs

Sauce Lab Demo app is used as a test application

## How to Run the Tests?

# Pre-Requisites:
    - [] Java JDK Version > 17 should be installed
    - [] npm installation can be optional
    - [] Appium v2 should installed
    - [] Install Android studio and add add the emulator
    - [] Add the following environment variables - Java Home, NPM path, Android command line tools

Since this is a maven project, this project is designed to run using mvn commands.
```
mvn clean verify
```
To have more control over the environment/devices where the tests needs to be executed, pass the following properties along with the above command,
    - -DtestSetup={{testSetup}}
    - -Dplatform={{platform} 
    - -DosVersion={{version}}


Eg :
```
mvn clean verify -DtestSetup=local -Dplatform=android -DosVersion=9.0
mvn clean verify -DtestSetup=sauce_labs -Dplatform=ios -DosVersion=16.0
```

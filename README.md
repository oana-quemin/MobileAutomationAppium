# MobileAutomationAppium

## Setup
- Install Appium on the computer (https://appium.io/)
- Install IntelliJ IDEA (https://www.jetbrains.com/help/idea/installation-guide.html)
- Have an Android phone
- Enable develop options on the phone (https://developer.android.com/studio/debug/dev-options)
- Install `adb` on the computer (https://developer.android.com/studio/releases/platform-tools)
- Install `Files` app on the phone if not already installed
- Install `Fleetio go` app on the phone if not already installed and be sure to be logged out

## Running the test
- Start IntelliJ
- Open the project
- Plug the phone in the computer. On the phone you will see a popup asking `Allow USB Debugging?`. Please tick the box and press allow.
- Open a terminal and run `adb devices`
- Get the ID of the phone (it will look something like `0A181JECB07431`)
- In `BaseTest` class, replace the existing ID at line 25 with the ID from the previous step:
```java
    dc.setCapability(MobileCapabilityType.DEVICE_NAME, "0A181JECB07431");
```
- Start Appium server (start a terminal and run `appium`).The command will not return, it will show the server logs 
- In IntelliJ, select `AddFuelEntryTest` class in the project view. Right click, then select `Run AddFuelEntryTest`

## Troubleshooting
- When running the tests many times, the app becomes flaky and when trying to add a new fuel type (line 29 inside VehiclesPage), a wrong popup is being opened. Instead of the correct popup, there is a different one that opens which has similar options, but more of them and they cannot be clicked. This looks like a bug in the application. If this happens, stop the test and rerun it. On the right side of the toolbar you will see a red rectangle. Press it in order to stop the test.
- Appium server can become flaky when left running for longer. In the terminal where Appium has been started you might see some errors being thrown or when running the tests you might see an error about a session not being opened. If this happens, stop Appium server (`CTRL + C`), and start it again (`appium`)
- If the test expects you to select an account after logging in (Mobila QA), stop the test, uncomment line 26 from `AddFuelEntryTest` and rerun the test

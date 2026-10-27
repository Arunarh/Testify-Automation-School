
from appium import webdriver
from appium.options.android import UiAutomator2Options

options = UiAutomator2Options()
options.platform_name = "Android"
options.automation_name = "UiAutomator2"
options.udid = "89VOUSLJS4ZTGYZT"
options.device_name = "CPH2349"
options.app_package = "com.saucelabs.mydemoapp.rn"
options.app_activity = "com.saucelabs.mydemoapp.rn.MainActivity"
options.no_reset = True

driver = webdriver.Remote(
    "http://127.0.0.1:4723",
    options=options
)

print("Appium connected successfully!")
print("App package:", driver.current_package)

driver.quit()

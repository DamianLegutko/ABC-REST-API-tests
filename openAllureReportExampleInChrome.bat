set OPEN_ALLURE_REPORT_FROM=%cd%
echo OPEN_ALLURE_REPORT_FROM is: %OPEN_ALLURE_REPORT_FROM%
"%ProgramFiles(x86)%\Google\Chrome\Application\chrome.exe" --disable-web-security --user-data-dir="C:/ChromeDevSession" "%OPEN_ALLURE_REPORT_FROM%\example\report\index.html"

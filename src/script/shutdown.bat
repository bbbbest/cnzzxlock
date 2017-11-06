@echo off
set "T=-1"
for /f "delims=" %%i in ('jps -l ^| find /i "smart-lock-server.jar"') do set "T=%%i"
if T == -1 goto error
echo Find running application...
echo Closing...
taskkill /F /FI "imagename eq javaw.exe"
echo Application has been closed!
goto quit
:error
echo Not find running application!
:quit
pause
@echo on

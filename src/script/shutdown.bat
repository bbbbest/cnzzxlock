@echo off
set "T=-1"
for /f "delims=" %%i in ('jps -l ^| find /i "smart-lock-server.jar"') do set "T=%%i"
if T == -1 goto error
echo Find running application...
echo Closing...
set PID="%T:~0,-22%"
start "" taskkill /f /pid %PID%
echo Application has been closed!
goto quit
:error
echo Not find running application!
:quit
pause
@echo on

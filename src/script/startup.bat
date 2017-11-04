@echo off
if not exist "%~dp0\smart-lock-server.jar" goto error
start "" javaw -jar -Xmx512m -Xms256m "%~dp0\smart-lock-server.jar"
echo server started!
goto quit
:error
echo smart-lock-server.jar is not exist!
:quit
pause
@echo on
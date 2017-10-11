@echo off
if not exist "%cd%\smart-lock-server.jar" goto error
start "" javaw -jar -Xmx512m -Xmx256m smart-lock-server.jar
echo server started!
goto quit
:error
echo smart-lock-server.jar is not exist!
:quit
pause
@echo on
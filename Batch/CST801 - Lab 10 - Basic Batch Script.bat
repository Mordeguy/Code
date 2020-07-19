echo off

:menu
cls
title Lab 10 Menu in CMD Line

echo:
echo Welcome! Please read carefully and choose from the options below.
echo:

echo Press 1 - to run the ipconfig command
echo Press 2 - to run the ipconfig/all command
echo Press 3 - to release current IP address for your adaptor
echo Press 4 - to renew your adapter's IP address
echo Press 5 - to remove all entries from your DNS cache
echo Press 6 - to stop the print spooler
echo Press 7 - to start the print spooler
 
echo:
echo press q to quit program
echo: 

set /p choice="Enter the number of the operation you wish to run: "

if "%choice%"=="1" goto one
if "%choice%"=="2" goto two
if "%choice%"=="3" goto three
if "%choice%"=="4" goto four
if "%choice%"=="5" goto five
if "%choice%"=="6" goto six
if "%choice%"=="7" goto seven
if "%choice%"=="q" exit

:one
ipconfig
pause
goto menu

:two
ipconfig/all
pause
goto menu

:three
ipconfig /release
pause
goto menu

:four
ipconfig /renew
pause 
goto menu

:five
ipconfig /flushdns
pause
goto menu

:six
net stop spooler
pause
goto menu

:seven
net start spooler
pause
goto menu
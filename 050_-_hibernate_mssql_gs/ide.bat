@echo off
if not "%1" == "max" start /MAX cmd /c %0 max & exit/b

:CHOOSE_IDE
echo ====================================
echo.
echo     Choise ide for you work
echo         [1] netbeans
echo         [2] visual code
echo         [3] intellij idea
echo.
echo ====================================
SET /P ch="Choise an ide: "

IF [%ch%] EQU [1] (
	GOTO IDE_NETBEANS
) 
IF [%ch%] EQU [2] (
	GOTO IDE_VSCODE
)
IF [%ch%] EQU [3] (
	GOTO IDE_IDEA
)

:IDE_NETBEANS
SET NB_VERSION=17
SET JAVA_VERSION=11
SET NB_HOME=E:\Tools\netbeans\netbeans-%NB_VERSION%
SET WORKDIR=D:\Workspaces\netbeans
SET lightTheme=com.formdev.flatlaf.FlatLightLaf
SET darkTheme=com.formdev.flatlaf.FlatDarkLaf

SET cacheDir=%WORKDIR%\cachedir\%NB_VERSION%
IF exist %cacheDir% ( echo myDirName exists ) ELSE ( mkdir myDirName && echo myDirName created)
SET userDir=%WORKDIR%\userdir\%NB_VERSION%
IF exist myDirName ( echo myDirName exists ) ELSE ( mkdir myDirName && echo myDirName created)


REM --nosplash
start "" %NB_HOME%/bin/netbeans64.exe --console suppress ^
	--jdkhome e:\Tools\jdk\openjdk\jdk-%JAVA_VERSION% ^
	--cachedir %WORKDIR%\cachedir\%NB_VERSION% ^
	--userdir %WORKDIR%\userdir\%NB_VERSION% ^
    --open-group cam.be ^	
	--laf %darkTheme% & exit	
goto eof

:IDE_VSCODE
call code . & exit
goto eof

:IDE_IDEA
start "" E:\Tools\jetbrains\idea\bin\idea64.exe .
goto eof

:eof
exit

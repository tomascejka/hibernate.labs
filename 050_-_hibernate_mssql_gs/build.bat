@echo off

SET WORKDIR=%cd%

SET /P opt="Build docker image with database, eg. y: "
IF [%opt%] EQU [y] (
    cd docker/mssql
    call .\build.bat
)

SET /P opt2="Run docker image with database, eg. y: "
IF [%opt2%] EQU [y] (
    cd %WORKDIR%
    cd docker/mssql
    call .\run.bat
)

cd %WORKDIR%
mvn clean package
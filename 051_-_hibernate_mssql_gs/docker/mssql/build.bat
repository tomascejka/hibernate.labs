@echo off

call .\_var.bat
docker build --build-arg MSSQL_VERSION=%MSSQL_VERSION% -t %imgName% -f Dockerfile-mssql .

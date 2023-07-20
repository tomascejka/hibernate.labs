@echo off

call .\_var.bat

REM -- Spusti mssql kontejner
docker run --rm --name %appName% -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=%password%" -p 1433:1433 -d %imgName%
echo pockam 15s... start mssql serveru
TIMEOUT /T 15

REM -- zobrazi, seznam databasi jedna z nich MUSI byt sample!
REM docker exec -it %appName% /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "%password%" -Q "select name from sys.databases"
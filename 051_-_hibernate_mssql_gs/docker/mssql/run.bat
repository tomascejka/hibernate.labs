@echo off

call .\_var.bat

echo ---------------------------------------------
echo Spusti mssql kontejner
echo ---------------------------------------------
docker run --rm --name %appName% -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=%password%" -p 1433:1433 -d %imgName%
echo pockam 30s... start mssql serveru
TIMEOUT /T 30

REM -- zobrazi, seznam databasi jedna z nich MUSI byt flywaydb!
echo ---------------------------------------------
echo Seznam databasi v mssql kontejneru
echo ---------------------------------------------
docker exec -it %appName% /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "%password%" -Q "select name from sys.databases"
pause
# Hibernate - mssql getting started
Hibernate a jednoduche/minimalisticke nastaveni s SQL server-em (ktery je nastaven via docker image, viz. docker/mssql adresar).

## Predpoklady
Musis mit nainstalovany docker/docker desktop, aby sli provadet docker-cli prikazy (```docker build``` a ```docker run```).

## Build
Spust ```build.bat``` a pri prvnim spusteni nech zbuildovat/spustit docker image pro mssql server (skript se Vas na to zepta - obe volby). 

```Build docker image with database, eg. y:```

```Run docker image with database, eg. y: n```

Pote se spusti ```maven clean package```, ktery zbuilduje projekt a spusti junit testy, v kt. se spousti hibernate testy. Testy loguji do konsole/formatuji sql prikazy.

## Run
Neni potreba - vse bezi v ramci Build (mvn test - v ramci junit testu).
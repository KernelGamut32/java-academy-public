# Registration Manager

Create a .env file in the root of the project (.gitignore is configured to ignore this file)

```env
DB_HOST=<host name>
POSTGRES_USER=<username>
POSTGRES_PASSWORD=<password>
POSTGRES_DB=<db name>
```

To launch the PostgreSQL instance (using docker-compose) execute `docker-compose --env-file .env up -d`

## OPTION 1

In IntelliJ, you can add this .env file to the Run/Debug configuration for the application so it gets picked up automatically when running interactively:

* In IntelliJ, navigate to "Run" | "Edit Configurations..."
* Select your Spring Boot application in the treeview on the left-hand side of the dialog
* Under "Build and Run", click "Modify options"
* Choose "Environment variables" under "Operating System"
* Select the path to the .env file and click Apply
* You can now launch the application in either Run or Debug mode in IntelliJ

## OPTION 2

To launch the Spring Boot application from a terminal execute `mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DDB_HOST=<host name> -DPOSTGRES_USER=<username> -DPOSTGRES_PASSWORD=<password> -DPOSTGRES_DB=<db name>"`

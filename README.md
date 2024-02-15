# SoigneMoi - BackEnd API
## Context
The following project is part of the "SoigneMoi" school project,
made for the Studi's "ECF" of the summer 2024 exam session.
### Author
Alexis ANEAS

## Presentation
This API is made to be interacted with by the following front-end:
- SoigneMoi Mobile App https://github.com/HalTobin/SoigneMoi_Mobile
- SoigneMoi Web Site https://github.com/HalTobin/SoigneMoi_Web
- SoigneMoi Desktop App https://github.com/HalTobin/SoigneMoi_Desktop

## Stack
The project is made with the Spring Framework using Java and Gradle.

## Setup
1. Copy the project from Github
2. Install PostgresSQL database: https://www.postgresql.org/download/
3. Create a database called "soigne_moi" by running the following command in your postgre terminal
(a superuser is required for the creation of a database):
```
CREATE DATABASE soigne_moi
```
4. Setup environmental variables SM_USER and SM_PWD that correspond to a PostgreSQL user
with the read and write authorization for the 'soigne_moi' database.
SM_USER must correspond to the PostgreSQL username and SM_PWD to its password.
5. Make sure that all your dependencies are installed, depending on your IDE,
you might need to manually install the Java SDK and correctly set up the JAVA_HOME environment variable,
you might also need to install the "Lombok" extension in your IDE.
6. Compile and run the code with the following command
```
gradlew bootRun
```
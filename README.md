## Requisitos

To launch this repository you will first need this software:

- Java 23
- MySQL8.0

## Instalación

### 1. Clone the repo

```sh
git clone https://github.com/Holos-INC/Holos.git
cd holos
```

### 2. Configure Database

Open mysql command line searching on the search bar Mysql8.0 Command Line Client

Create the database and user

```SQL
CREATE DATABASE holos_db;
CREATE USER 'H0l0s_DB'@'localhost' IDENTIFIED BY 'H0l0s1$PP';
GRANT ALL PRIVILEGES ON holos_db.* TO 'H0l0s_DB'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Run the app
Run the app with the top top right play button

### Recomendations
Install the java extension package for VS Code, it will make everything easier


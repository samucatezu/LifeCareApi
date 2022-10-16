# LifeCare insurance company
(EN-US)
Spring Boot Application, in which the user can calculate the amount of a given insurance, and then purchase it and print the policy in PDF form. When new user registers an account, the application sends an e-mail verification link necessary to use its functionality.

(PT-BR)
Aplicativo Spring Boot, no qual o usuário pode calcular o valor de um determinado seguro, adquiri-lo e imprimir a apólice em formato PDF. Quando um novo usuário registra uma conta, o aplicativo envia um link de verificação de e-mail necessário para acessar o aplicativo.
## Tools and Technologies

* **Technology** : Java 11, Spring (Boot, MVC, Data JPA) , Lombok, JUnit 5, Mockito, Maven, AWS
* **Application Servicer** : Apache Tomcat Server
* **Database** : MySQL Database

## Getting Started (Começando)
(EN-US)
Download the code on your machine and test the application, importing it as a maven project. Follow these steps for the command-line option:

(PT-BR)
Baixe o código em sua máquina e teste o aplicativo, importando-o como um projeto maven. Siga estas etapas para a opção de linha de comando:

### Prerequisites (Pré-requisitos)

- [JDK 1.11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/installer/)


### Build & Run

1. Invoke `git clone https://github.com/samucatezu/PIMBackend.git`
2. Invoke `cd PIMBackend`
3. Create Database ```CREATE DATABASE insurancecompanydb;```
4. Create Database ```CREATE DATABASE insurancecompanytestdb;```
5. Set Username and Password in the ```main/resources/application.yml``` file
6. Set Username and Password in the ```test/resources/application.yml``` file
7. Invoke `mvn clean install`
8. Invoke `mvn spring-boot:run`
9. The server is running on **localhost:5000**

### Login

#### To login, enter the username and password for the account with selected role:
#### Para fazer login, insira o nome de usuário e a senha da conta com a função selecionada:
|   Role  	| Username 	| Password 	|
|:-------:	|:--------:	|:--------:	|
|   User  	|   user   	|   user   	|
| Manager 	|  manager 	|  manager 	|
|  Admin  	|   admin  	|   admin  	|

## Explore API

To explore documentation, run the application and go to `http://localhost:5000/swagger-ui.html`

### :warning: To use the e-mail functionality, you need to setup your credentials in these files MailSenderConfiguration and application.yml

volaconnoi-webapp
=================

<h3>Enjoy the pleasure to flight around the world with VolaConNoi.it<h3>

The application has been deployed by using JDK 8.0 and Glassfish Server 4.0 (build 89) on a Mac OS X platform.

In order to deploy and run it correctly on your machine, you must install JDK (the latest current version 8.0 since new Java Date and Time API have been used) along with Glassfish Server 4.0. It might seem obvious and useless but you must be connected to internet as well in order to browse correctly the application since it makes good use of some remote well-known resources (Jquery, JqueryUI, ecc...) to perform some features that improve the UX. Consequently to enjoy a full web experience you have to enable Javascript in your web browser.

The project has been developed in Netbeans 8.0 IDE - Java EE version. It comes with Glassfish bundled but you are free to choose to not install it during the installation procedure and download the stand-alone version of Glassfish directly from the official Glassfish's web page.

Maven dependecies managament has been used in order to manage dependencies coming from external libraries. The external libraries have been used to develop important features of the application. For this reason you must have installed Maven in your machine. Anyway it comes already installed in Netbeans 8.0 IDE. Before to run properly the project in your machine, remember to  right-click on Maven project and choose "Build with dependencies". Only after that, you will be ready to run it correctly.

The model tier has been developed using MySQL database version 5.5.34 which come installed along with the latest current version of MAMP (3.0.5).

<h5>Glassfish Resources</h5>

<h6>Here below all the resources that you have to add through GlassFish Admin Console in order to deploy correctly the web application</h6>

**JDBC connections Pools**

Pool name: VolaConNoiMySQLPool

Resource type: javax.sql.ConnectionPoolDataSource

Datasource Classname: com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

*Additional Properties:*

URL: jdbc:mysql://localhost:8889/volaconnoiDB?zeroDateTimeBehavior=convertToNull

user: root

password: root

**JDBC Resource**

JNDI name: dbc/volaconnoiDB

Pool name: VolaConNoiMySQLPool

**JMS Resources**

*Connection Factories*

JNDI name: ms/reservationProcessorQueueFactory

resource type: javax.jms.ConnectionFactory


*Destination Resources*

JNDI name: jms/bookingProcessorQueueReceiver

Physical Destination Name: bookingProcessorQueueReceiver

Type: javax.jms.Queue


**JavaMail Session** (set using Gmail account)
(used from the application to send booking confirmation to the client)

JNDI name: mail/bookingConfirmationMailer

Mail Host: smtp.gmail.com

defult user: your-google-email

default sender address: your-google-email

*Advanced*

Store protocol: imap

Store protocol class: com.sun.mail.imap.IMAPStore

Transport protocol: smtp

Transport protocol class: com.sun.mail.smtp.SMTPTransport

*Additional properties*

mail.smtp.socketFactory.class: javax.net.ssl.SSLSocketFactory

mail.smtp.password: your-password

mail.smtp.auth: true

mail.smtp.socketFactory.fallback: false

mail.smtp.port: 465

mail.smtp.socketFactory.port: 465

<h5>Configurations</h5>
  --server-config
    --security
      --realms
      
New Realms

Real name: JDBCRealm

Class name: com.sun.enterprise.security.ee.auth.realm.jdbc.JDBCRealm

JAAS context: jdbcRealm

JNDI: jdbc/volaconnoiDB

User table: volaconnoiDB.USER_CREDENTIAL

User name column: USERNAME

Password column: PASSWORD

Group table: volaconnoiDB.USER_CREDENTIAL

Group table user name column: USERNAME

Group name column: GROUP_NAME

Password Encryption Algorithm: AES

Encoding: Base64

Charset: UTF-8

**User credential**

The following user credential are already stored in the database. Just import sql_dump.sql into your sql database.

Group 'ADMIN'

username: admin

password: admin

Group 'CHECKIN'

username: operator

password: operator


<h4>Troubleshooting</h4>

In the case you get the following error in the attempting to ping the database pool

```
Error An error has occurred
Ping Connection Pool failed for name-pool. Class name is wrong or classpath is not set for: com.mysql.jdbc.jdbc2.optional.MysqlDataSource Please check the server.log for more details.
```

you have to download mysql connector and put inside your glassfish lib folder.








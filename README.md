# Worker
Spring Boot application that provides RESTful service for CRUD operations
for company employees.

![Alt text](Screenshot_graph.png?raw=true "Graph Api")

### Deployment database

Install DB-server via [doc](https://orientdb.org/getting-started)

Create database:

* database   : ```workero```

* login      : ```root```

* password   : ```2019```

* storage    : ```plocal```

* type       : ```graph```

### Build

Run next command into application path:

```mvn clean install```

### Run

For start server on:

Linux/OSX:

Change access permissions to the start script

```sudo chmod 755 worker-0.0.1-SNAPSHOT.jar```

Run

```java -jar /<path>/worker-0.0.1-SNAPSHOT.jar```

Windows:

Run

```java -jar <path>\worker-0.0.1-SNAPSHOT.jar```

Where - is an absolute path to jar-file.

### Server STATUS

Go to link:

```<serverIp>:<serverPORT>/actuator```

For example,
[http://localhost:8888/actuator](http://localhost:8888/actuator)

### API

Full info about documentation of REST locate into

```<serverIp>:<serverPORT>/swagger-ui.html```

For example, 
[http://localhost:8888/swagger-ui.html](http://localhost:8888/swagger-ui.html)

![Alt text](Screenshot_swagger.png?raw=true "Swagger documentation")

You can see it after start application.

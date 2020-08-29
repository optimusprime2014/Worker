# Worker
Spring Boot application what provides RESTfull service for CRUD operations
for company employees. Show relations between members and company.

![Alt text](Screenshot_graph.png?raw=true "Graph Api")

### [Demo](http://162.250.120.118:8002/swagger-ui.html)

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

### API

Full info about a documentation of REST locate into

```<serverIp>:<serverPORT>/swagger-ui.html```

For example, [Localhost](http://localhost:8002/swagger-ui.html). You can see it after start application.

## Prerequisites
You must have [maven](https://maven.apache.org/install.html), [jdk8](http://www.oracle.com/technetwork/java/javase/downloads/index.html), [mysql](https://dev.mysql.com/downloads/), [nodejs](https://nodejs.org/en/download/) installed on your machine.
## Steps 
1.Run the folowing mysql commands
```sh
CREATE DATABASE candidate_management;
GRANT ALL PRIVILEGES ON candidate_management.* To 'dev_user'@'localhost' IDENTIFIED BY 'letmein';
```

2.Run the spring boot server. Within `cm-server` folder run:
```sh
mvn spring-boot:run
```

3.Install the client project dependencies. Within `cm-client` folder run:
```sh
npm install
```

4.Run the react client. Within `cm-client` folder run:

```sh
npm run start
```
5. For cm-external

Go to resourcer/config.resources
  Uncomment and set the following properties:
    PROXYHOST
    PROXYPORT
    GOOGLESECRET

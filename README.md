# REST-service for Alfa Bank

The application uses entities: an item and a box. 
When the application starts, it receives a link to an XML file in the form of a command line parameter, in which the relative position of items and boxes is specified. 
XML file can be loaded from different sources: external file, file in classpath, URL. 
At startup, the application creates tables in the database and fills them with data in accordance with the transferred XML file. 
After loading the file, the application works as a REST-service that returns the id of items of the given color, contained in the box and in all nested boxes with the given id of the root box.

## Features:
- REST-service
- Java 8
- PostgreSQL

##  How to run?

First, create a database in PostgreSQL. 
Then, in the application.properties file, change the database connection configuration.
The tables in the database are created automatically when the application is first started.

Run the application

```
mvn package
java -jar target/*.jar --classpath=src\\main\\resources\\file\\input.xml
```

You also have other options for launching the application

```
java -jar target/*.jar --file=C:\\test\\input.xml
```

and

```
java -jar target/*.jar --url=file:///D:/Desktop/input.xml
```

You can also change the port by adding it as the second argument

```
java -jar target/*.jar --classpath=src\\main\\resources\\file\\input.xml --port=8087
```

After starting the application, you can send POST requests to the address http://localhost/test with the "box" and "color" parameters.
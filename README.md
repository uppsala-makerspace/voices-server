# voices-server

Building and starting
---------------------
To build and run the project, execute the following two commands:

    $ ./gradlew build
    $ java -jar build/libs/voices-server-1.0-SNAPSHOT.jar
    
The server will then be available on http://localhost:8080.  
Another port may be specified with the parameter
`-Dserver.port=xxxx` on the java command, like this:

    $ java -Dserver.port=80 -jar build/libs/voices-server.1.0-SNAPSHOT.jar
    
Using the admin interface
-------------------------
The admin interface (Work In Progress) may be reached using the following URL:

    http://<server>:<port>/admin
    
    

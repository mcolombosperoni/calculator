calculator example project
===============

An example application using AngularJS as frontend and Java Spark as REST API.


[![Build Status](https://drone.io/bitbucket.org/cd-k/java-calculator/status.png)](https://drone.io/bitbucket.org/cd-k/java-calculator/latest)

##Contribution

_Appreciate any contribution for this project, including suggestions, documentation improvements, reporting issues, forks and bugfixs,  etc._

Thanks.


##Requirements

   * JDK 8

     Oracle Java 8 is required, go to [Oracle Java website](http://java.oracle.com) to download it and install into your system.
     Optionally, you can set **JAVA\_HOME** environment variable and add *&lt;JDK installation dir>/bin* in your **PATH** environment variable.

   * Apache Maven

     Download the latest Apache Maven from [http://maven.apache.org](http://maven.apache.org), and uncompress it into your local system.
     Optionally, you can set **M2\_HOME** environment varible, and also do not forget to append *&lt;Maven Installation dir>/bin* your **PATH** environment variable.

   * (Optional) Docker

     Download the latest Docker from [https://docs.docker.com](), and install it. More detail information about installation below.

##Documentation

   More detailed documentation about project and specific implementation here: [documentation](documentation.md)

## Run this project

  1. Clone the codes.

   ```sh
      git clone 
   ```

  2. (Optional) Enter the root folder, run `mvn clean site` to use maven site plugin and generate in target folder the project site including surefire and cobertura reports.

   ```sh
      mvn clean site
   ```

  3. Enter the root folder, run `mvn clean compile` to use maven compile plugin to resolve all project dependencies and generate in target folder all classes files.

  ```sh
      mvn clean compile
  ```

  4. Enter the root folder, run `mvn clean compile` to use maven compile plugin to resolve all project dependencies and generate in target folder all classes files.

  ```sh
      mvn clean compile
  ```

  5. From root folder command line (or using IDE for developers) run SparkWebMain java class
     (Optional) Instead of Web Application there is also a Command line interface, run CommandLineMain java class

  ```sh
      java SparkWebMain
  ```

  6. Go to [http://localhost:4567]() to test it.

  <!-- 7. (Optional) If you want to explore the REST API docs, there is a *Swagger UI* configured for visualizing the REST APIs,
  just go to [http://localhost:4567/app/swagger-ui.html](http://localhost:4567/app/swagger-ui.html). -->


## Run Project using Docker

  1. Download Docker from [https://docs.docker.com](). Here direct link for windows users [https://docs.docker.com/windows/step_one]().
   Docker installation provides also git.
   BE CAREFUL: windows 7 users needs to install also a component for .Net Framework, please follow the guide.

  2. Clone the code.
  `sh
    git clone 
  ``
    

  3. Open Docker quickstart terminal and wait initialization. Then you can use command line as a linux bash shell:
   - go to project main directory `cd /d/....../java-calculator` initial /d/ stays for D: in the example
   - launch build docker command `docker build -t calculator .` to download and create a virtual machine as defined in Dockerfile project
   - launch run docker command `docker run -d -p 4567:4567 calculator` to run docker container just created
   - launch process list docker command to see status of docker container `docker ps -a` the result seems like:
    ```sh
        CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                    PORTS                    NAMES
        b3425a36e28d        calculator          "/usr/lib/jvm/java-8-"   43 minutes ago      Up 43 minutes             0.0.0.0:4567->4567/tcp   drunk_lichterman
    ```
    
   - launch logs docker command specifying container id to inspect logs `docker logs b3425a36e28d` the result seems like:
    ```sh
               2016-02-04_14:12:24.825 [main] DEBUG spark.route.RouteMatcherFactory - creates RouteMatcher
               2016-02-04_14:12:24.843 [main] DEBUG spark.route.SimpleRouteMatcher - Adds route: get, /add, spark.RouteImpl$1@3f3afe78
               2016-02-04_14:12:24.844 [main] DEBUG spark.route.SimpleRouteMatcher - Adds route: get, /sub, spark.RouteImpl$1@36d64342
               2016-02-04_14:12:24.848 [main] DEBUG spark.route.SimpleRouteMatcher - Adds route: get, /mult, spark.RouteImpl$1@511baa65
               2016-02-04_14:12:24.850 [main] DEBUG spark.route.SimpleRouteMatcher - Adds route: get, /div, spark.RouteImpl$1@30c7da1e
               2016-02-04_14:12:24.851 [main] DEBUG spark.route.SimpleRouteMatcher - Adds route: get, /string-operation, spark.RouteImpl$1@57829d67
               2016-02-04_14:12:24.978 [Thread-0] INFO  spark.webserver.JettySparkServer - == Spark has ignited ...
               2016-02-04_14:12:24.979 [Thread-0] INFO  spark.webserver.JettySparkServer - >> Listening on 0.0.0.0:4567
    ```
   - the ip of docker machine where you can reach the application is (docker-machine ip default): 192.168.99.100
   - Go to [http://192.168.99.100:4567] to test it.

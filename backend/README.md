## backend

The backend is a Java Web Application so in order to run it you will need to have an application server.
The application has been tested with Apache Tomcat 9.0.4. The instructions that follow assume that you 
have correctly installed and configured Apache Tomcat and they make use of tomcat7-maven-plugin. 

The instructions make use of the MacOS terminal and they are similar in other environments.

1. Start the server if it is not already running.

Go to the installation directory of Tomcat. 
```
cd /Library/apache-tomcat-9.0.4/
```
Execute the startup script located in the bin directory.
```
./bin/startup.sh
```
2. Deploy the backend Web Application to the server.

Open the pom.xml file in an editor and adjust the configuration of tomcat7-maven-plugin. A sample configuration is given below.
```xml
<plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <version>2.2</version>
  <configuration>
    <server>Tomcat-localhost</server>
    <url>http://localhost:8080/manager/text</url>
    <path>/macrofoods</path>
  </configuration>
</plugin>
```
Execute the maven goal for deploying the app to Tomcat.
```
mvn tomcat7:deploy
```
The goal must finish by indicating that the build was succesfull. 

3. Check that application is correctly deployed by opening a browser and typing the following url.
```
http://localhost:8080/macrofoods/test
```
If the application is running you must see an appropriate message.

4. (Optional) If you want to undeploy the application it suffices to execute the respective maven goal.
```
mvn tomcat7:undeploy
```
5. (Optional) If you want to shutdown the application server you have to perform the following commands.

Go to installation directory of Tomcat.
```
cd /Library/apache-tomcat-9.0.4/
```
Execute the shutdown script located in the bin directory.
```
./bin/shutdown.sh
```

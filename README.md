# Shortest Path Problem
Problem: Find the shortest path of a given graph

#Technology used: Java 8, Spring Boot, REST API, Angular 6, Durby DB (Embedded), Eclipse, Tomcat 9, Maven 

#Problem Resolution: 
1. I used Dijkstra’s shortest path algorithm to solve the problem. Will not explaing the Dijkstra’s algorithim here.
2. Created two JPA entities, Planet and AdjacentPlanet and used the same objects to carry the data to UI layer, due to time constraint.
3. Created three .csv files from given excel documents.
	a)Planets.csv
	b)DistanceBetweenPlanets.csv
	c)TrafficBetweenPlanets.csv
 
 And all data automatically imported into two tables planet and AdjacentPlanet on startup of the application
 
 4. Exposed REST APIs for CRUD operations 
 5. And prepared a graph with calculated shortest distance and total traffic.
 5. Then represent the solution in UI using angular code
 6. Build: Using maven you can build the Spring boot project. Angular project is not configured in maven build script.
 7. You need to separately run following command to build the angular project, (You should have node.js and Angular CLI installed in your system) 
   ng build for build the project and 
   ng start for start the server
 
 
#Ran The application in browser:
1. WAR File: Please download the file from https://github.com/girijec/Girish_Repo/spp.war
2. Past the spp.war file into webapps directory inside installed Tomcat 9
5. Open the application in chrome browser (Not tested on other browsers) : 
		URL should be like this http://localhost:8080/spp/
		Where localhost is the host name and 8080 is the tomcat installed port
		and "spp" name of the application
6. Now select a Planet from dropdown , you will see the result	



#Ran application in any REST client (Postman recommended):
Get a planet with below details 
         a) total distance from Earth 
	  b) total traffic from earth. 
	  c) Time taken to given Planet from Earth with traffic and without trafic
	  d) Adjacent planet info
	  c) Shortest Path from Earth
	  
	 URI 1: http://localhost:8080/spp/api/planet/node/<planet_node>, Here planet_node is the path variable
	 Example URL : http://localhost:8080/spp/api/planet/node/F
	  
	 URI 2: http://localhost:8080/api/planet/graph , this end point will give the settled graph with all the planet details
		
     Http method: Get and no authentication required.
	

 Thanks
 Girish Lochan Nath
 girijec@gmail.com


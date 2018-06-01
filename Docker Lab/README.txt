A basic lab introduction to Docker.

Hello!

To get this up and running you're going to need to do three things:

1) Pull and run nmbruce/lab5map with the following command: docker container run -d -p 5437:5432 -p 8087:8080 nmbruce/lab5map
2) Pull and run nmbruce/lab5calc with the following command: docker container run -d -p 5438:5432 -p 8088:8080 nmbruce/lab5calc
3) Build and run the client servlet with the following command from the root of the Lab5 folder: ant deploy
	a) Note: you'll need to change (at least) the following to get this to load:
		i) In build.properties: tomcat.home needs to be changed to the location of tomcat in your environment
		ii) If you changed any ports in steps 1 or 2, you need to change the port of the associated init-param (letterURL or gradeURL respectively) in the web.xml of the lab5 servlet before building.


That should be good to get it up and running! Some other things to note:
1) If you manually build the CalculateGrade servlet (of course after changing the build.properties as with Lab5), the servlets will build and deploy, but without being connected to a postgreSQL database as in the containers on dockerhub, a request will only return 0.0).
2) The responses from CalculateGrade and MapToLetter are very simple and definitely not remotely RESTful. They expect to receive the correct parameters (year and subject for CalculateGrade and grade for MapToLetter). Similarly, they return a simple plain/text representation of the results, which they expect any client to know what to do with. Given the simplicity of the service, this isn't an issue here, but of course, the responses should ideally be more sophisticated.
3) The docker images were built from the provided kgaryasu/lab5given with the war files copied into the appropriate container and then published on my docker hub under new names. You shouldn't have to copy over my war files into a new docker container or anything like that.

lab5map image available at docker hub here: https://hub.docker.com/r/nmbruce/lab5map/
lab5calc image availble at docker hub here: https://hub.docker.com/r/nmbruce/lab5calc/

(Or simply search for nmbruce/lab5map or nmbruce/lab5calc respectively. The submission version for each are tagged as latest)
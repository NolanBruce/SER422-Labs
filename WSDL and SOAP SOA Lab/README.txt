A simple lab involving WSDL and SOAP-based SOA.

For Task 1:

As requested all the library files from the provided project were removed, leaving on the json library file. So the following files will need to be added back to the Task1/lib directory before deploying:

axis.jar
commons-discovery-0.2.jar
commons-logging.jar
jaxrpc.jar
saaj.jar
wsdl4j.jar


If I misunderstood the submission instructions, I can resubmit with those library files included.


Apart from that, all you should need to do to get the service deployed is change tomcat.home in build.properties to the correct path for your environment.




For Task 2:

Included at the base of the directory is the aar file, but running the ant script (a simple "ant" command will work) will build the project and generate that same aar file as well, if you'd prefer.



Anyway, good luck, and I hope grading this one is easier than the last two (I don't believe you should encounter any library errors at least if you place those files in the correct folder).
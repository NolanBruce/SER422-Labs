You'll need to do the following to deploy through build.xml:

1) build.properties will need to be adjusted for your environment. Most notably, tomcat_webapps will need to be changed to the filepath for your own environment.

2) phone.properties (inside the folder ./properties) will need to be adjusted to your environment. That is, folderPath should be changed to the path to the included resource folder on your machine.

3) Copy the lib folder from the BooktownREST example.



Note that any consumed content is simple text/plain, so you'll need to look at the API to see the expected format to avoid a BadRequestError

FROM openjdk
ADD ./dist/app.jar /srv/app.jar
ADD ./dist/restlet.jar /srv/restlet.jar
CMD java -cp /srv/restlet.jar:/srv/app.jar HelloWorldServer

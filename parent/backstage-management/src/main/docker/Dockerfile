FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD backstage-management-0.0.1-SNAPSOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
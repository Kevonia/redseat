From tomcat:8.5.68-jdk11-corretto
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/redseat-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]
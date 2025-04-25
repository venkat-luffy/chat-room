# Base Tomcat image with Java 17
FROM tomcat:9.0-jdk17-temporal

# Clean default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR into Tomcat (rename it to ROOT.war so it runs at /)
COPY target/chat-room.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]

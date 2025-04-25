# Use Tomcat with Java 17
FROM tomcat:9.0-jdk17

# Remove default apps from Tomcat (optional but recommended for your app)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the chat-room WAR file to the Tomcat webapps directory
COPY target/chat-room.war /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Run Tomcat server
CMD ["catalina.sh", "run"]

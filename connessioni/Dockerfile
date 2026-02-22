######################################################
# Dockerfile per connessioni 
######################################################

FROM eclipse-temurin:21-jdk 

ADD build/libs/connessioni.jar connessioni.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "connessioni.jar"]

HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1



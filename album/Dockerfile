######################################################
# Dockerfile per album
######################################################

FROM eclipse-temurin:21-jdk 

ADD build/libs/album.jar album.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "album.jar"]

HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1


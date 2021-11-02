FROM openjdk:8
EXPOSE 8083
ADD /target/projet-devops-0.0.1-SNAPSHOT.jar projet-devops-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","projet-devops-0.0.1-SNAPSHOT.jar"]
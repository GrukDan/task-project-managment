FROM openjdk:8
ADD target/backend.jar backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "backend.jar"]


#https://medium.com/codefountain/develop-a-spring-boot-and-mysql-application-and-run-in-docker-end-to-end-15b7cdf3a2ba
#docker build -t backend .
#docker run -d -p 8081:8081 --name backend --link mysql-standalone:mysql backend

FROM openjdk:11
EXPOSE 8081
ADD target/springboot-k8s-curd.jar springboot-k8s-curd.jar
ENTRYPOINT ["java", "-jar", "springboot-k8s-curd.jar"]
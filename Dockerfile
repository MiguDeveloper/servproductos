FROM openjdk:11
VOLUME /tmp
ADD ./target/servproductos-0.0.1-SNAPSHOT.jar servicio-productos.jar
ENTRYPOINT ["java","-jar","/servicio-productos.jar"]
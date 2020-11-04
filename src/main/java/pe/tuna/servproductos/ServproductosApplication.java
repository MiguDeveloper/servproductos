package pe.tuna.servproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"pe.tuna.servicommons.models"})
public class ServproductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServproductosApplication.class, args);
    }

}

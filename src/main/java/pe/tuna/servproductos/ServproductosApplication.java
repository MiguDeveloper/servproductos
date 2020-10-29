package pe.tuna.servproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServproductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServproductosApplication.class, args);
    }

}

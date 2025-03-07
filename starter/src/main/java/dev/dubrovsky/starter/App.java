package dev.dubrovsky.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dev.dubrovsky")
@EnableJpaRepositories(basePackages = "dev.dubrovsky.repository")
@EntityScan(basePackages = "dev.dubrovsky.model")
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}

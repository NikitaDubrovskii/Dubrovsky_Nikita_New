package dev.dubrovsky.configuration;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "dev.dubrovsky")
public class OnlineStoreContextConfiguration {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return new ConnectionDataBase().getEntityManagerFactory();
    }

}

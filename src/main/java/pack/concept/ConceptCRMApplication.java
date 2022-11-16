package pack.concept;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import pack.concept.file_service.service.StorageService;
import pack.concept.file_service.storage.StorageProperties;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
        "pack.concept.security.repository",
        "pack.concept.calculator_service.repository"})
@EnableWebSecurity
@EnableConfigurationProperties(StorageProperties.class)
public class ConceptCRMApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConceptCRMApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService){
        return (args -> {
            storageService.deleteAll();
            storageService.init();
        });
    }
}

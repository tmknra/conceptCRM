package pack.concept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.catalogue.repository")
@EnableWebSecurity
public class ConceptCRMApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConceptCRMApplication.class, args);
	}

}

package nl.hakktastic.order.api.report;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Report Service",
				description = "Demo Rest Report Service for Spring Boot",
				version = "0.0.1",
				contact = @Contact(url = "https://www.github.com/hakktastic", email = "hakktastic@gmail.com")))
@SpringBootApplication
@EnableFeignClients("nl.hakktastic.order.api")
public class ReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
	}

}

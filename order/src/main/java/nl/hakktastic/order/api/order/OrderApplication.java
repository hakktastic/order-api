package nl.hakktastic.order.api.order;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Order Service",
				description = "Demo Rest Order Service for Spring Boot",
				version = "0.0.1",
				contact = @Contact(url = "https://www.github.com/hakktastic", email = "hakktastic@gmail.com")))
@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
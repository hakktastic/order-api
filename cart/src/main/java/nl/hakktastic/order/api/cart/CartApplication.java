package nl.hakktastic.order.api.cart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Cart Service",
				description = "Demo Rest Cart Service for Spring Boot",
				version = "0.0.1",
				contact = @Contact(url = "https://www.github.com/hakktastic", email = "hakktastic@gmail.com")))
@SpringBootApplication
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}

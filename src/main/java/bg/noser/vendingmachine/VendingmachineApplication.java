package bg.noser.vendingmachine;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Vending Machine",
				version = "0.0.1",
				description = "The REST API of the Vending Machine. Please inset coins and Products if the db is not seeded(readme.md)"
		),
		servers = {
				@Server(
						url = "http://localhost:8080",
						description = "Local Server"
				)
		}

)
@SpringBootApplication
public class VendingmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendingmachineApplication.class, args);
	}

}

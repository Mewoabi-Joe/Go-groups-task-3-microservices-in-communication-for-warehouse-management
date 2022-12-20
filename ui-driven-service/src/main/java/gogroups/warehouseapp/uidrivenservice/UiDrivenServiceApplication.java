package gogroups.warehouseapp.uidrivenservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Ui-driven service"
		)
)

@SpringBootApplication
@EnableFeignClients
public class UiDrivenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiDrivenServiceApplication.class, args);
	}

}

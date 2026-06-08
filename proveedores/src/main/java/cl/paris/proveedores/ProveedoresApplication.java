package cl.paris.proveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProveedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProveedoresApplication.class, args);
	}

}

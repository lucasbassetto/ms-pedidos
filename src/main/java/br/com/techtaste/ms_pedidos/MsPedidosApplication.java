package br.com.techtaste.ms_pedidos;

import br.com.techtaste.ms_pedidos.mapper.PedidoMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPedidosApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PedidoMapper pedidoMapper) {
		return args -> {
			System.out.println(pedidoMapper);
		};
	}

}

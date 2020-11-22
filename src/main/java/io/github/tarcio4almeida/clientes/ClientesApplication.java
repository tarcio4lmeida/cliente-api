package io.github.tarcio4almeida.clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.tarcio4almeida.clientes.entity.Cliente;
import io.github.tarcio4almeida.clientes.repositories.ClienteRepository;

@SpringBootApplication
public class ClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}
	@Bean // -> cria um objeto no contexto da aplicacao
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			// builder do lombok -> um jeito mais "simples" de instanciar um objeto
			Cliente cliente = Cliente.builder().cpf("00000000000").nome("Fulano").build();
			repository.save(cliente);
		};
	}
}

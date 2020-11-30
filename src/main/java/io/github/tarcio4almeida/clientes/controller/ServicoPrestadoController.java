package io.github.tarcio4almeida.clientes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.tarcio4almeida.clientes.dto.ServicoPrestadoDTO;
import io.github.tarcio4almeida.clientes.entity.Cliente;
import io.github.tarcio4almeida.clientes.entity.ServicoPrestado;
import io.github.tarcio4almeida.clientes.repositories.ClienteRepository;
import io.github.tarcio4almeida.clientes.repositories.ServicoPrestadoRepository;
import io.github.tarcio4almeida.clientes.util.BigDecimalConverter;


@RestController
@RequestMapping("api/servicos-prestados")
public class ServicoPrestadoController  {
	
	@Autowired
	private ServicoPrestadoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		
		LocalDate  data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")); // mm -> minusculo = minutos
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = 
				clienteRepository.
						findById(idCliente)
						.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));
		
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servicoPrestado);
	}
	
	@GetMapping()
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required=false, defaultValue="") String nome,
			@RequestParam(value = "mes", required=false, defaultValue="") Integer mes) {
		return repository.findByNomeClienteAndMes(nome="%" + nome + "%", mes);
	}
	
	
//	@GetMapping()
//	public List<ServicoPrestado> obterTodos() {
//		return repository.findAll();
//	}
	
	@GetMapping("{id}")
	public ServicoPrestado acharPorId(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // cod 204
	public void deletar(@PathVariable Integer id) {
		repository.findById(id).map(ServicoPrestado -> { // metodo map -> mapeia um objeto para fazer algo
			repository.delete(ServicoPrestado);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoPrestado ServicoPrestadoAtualizado) {
		repository.findById(id).map(ServicoPrestado -> {
			return repository.save(ServicoPrestado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}
}	

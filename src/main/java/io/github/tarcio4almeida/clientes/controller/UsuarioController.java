package io.github.tarcio4almeida.clientes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.tarcio4almeida.clientes.entity.Usuario;
import io.github.tarcio4almeida.clientes.exception.UsuarioCadastradoException;
import io.github.tarcio4almeida.clientes.service.UsuarioService;

@RestController // é um plus de Controller + ResponseBody
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		try {
			return service.salvar(usuario);
		} catch (UsuarioCadastradoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
//	@GetMapping()
//	public List<Usuario> obterTodos() {
//		return service.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Usuario acharPorId(@PathVariable Integer id) {
//		return repository.findById(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
//	}
//
//	@DeleteMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT) // cod 204
//	public void deletar(@PathVariable Integer id) {
//		repository.findById(id).map(usuario -> { // metodo map -> mapeia um objeto para fazer algo
//			repository.delete(usuario);
//			return Void.TYPE;
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
//	}
//
//	@PutMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Usuario usuarioAtualizado) {
//		repository.findById(id).map(usuario -> {
//			return repository.save(usuario);
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
//	}

}

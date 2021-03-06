package io.github.tarcio4almeida.clientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.tarcio4almeida.clientes.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String username);
	
	// ta dentro das especificações do query method, logo n precisa ser implementado
	boolean existsByUsername(String username);

}

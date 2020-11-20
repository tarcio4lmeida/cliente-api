package io.github.tarcio4almeida.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.tarcio4almeida.clientes.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

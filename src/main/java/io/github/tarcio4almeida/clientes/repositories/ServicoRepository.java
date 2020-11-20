package io.github.tarcio4almeida.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.tarcio4almeida.clientes.entity.Servico;


@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}

package io.github.tarcio4almeida.clientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.tarcio4almeida.clientes.entity.ServicoPrestado;

@Repository
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

	@Query("select s from ServicoPrestado s join s.cliente c where upper(c.nome) like upper(:nome) and MONTH(s.data) = :mes")
	List<ServicoPrestado> findByNomeClienteAndMes(
			@Param("nome") String nome, @Param("mes") Integer mes);

}

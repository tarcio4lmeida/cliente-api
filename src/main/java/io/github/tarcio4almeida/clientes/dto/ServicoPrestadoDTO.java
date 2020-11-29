package io.github.tarcio4almeida.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoPrestadoDTO {

	private String descricao;
	private String preco;
	private String data;
	private Integer idCliente;
}

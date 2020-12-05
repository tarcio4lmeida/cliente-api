package io.github.tarcio4almeida.clientes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, name = "login") // é unico no banco de dados, ou seja, n pode ter repeticao
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String username;
	
	@Column(name="senha")
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String password;
}

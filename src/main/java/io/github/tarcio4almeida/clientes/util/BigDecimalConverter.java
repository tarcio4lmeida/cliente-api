package io.github.tarcio4almeida.clientes.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component // para poder fazer injeção de dep.
public class BigDecimalConverter {
	// 1.000,00 -> 1000.00
	public BigDecimal converter(String value) {
		if(value == null) {
			return null;
		}
		value = value.replace(".", "").replace(",", ".");
		return new BigDecimal(value);
	}
}

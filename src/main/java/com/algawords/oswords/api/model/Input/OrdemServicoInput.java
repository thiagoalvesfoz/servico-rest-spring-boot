package com.algawords.oswords.api.model.Input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoInput {
	
	@NotNull
	private Long clienteId;
	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal preco;
	
}

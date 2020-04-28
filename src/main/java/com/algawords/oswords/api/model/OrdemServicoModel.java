package com.algawords.oswords.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.algawords.oswords.domain.model.enumeration.StatusOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoModel {
	
	private Long id;
	private ClienteModel cliente;
	private String descricao;
	private BigDecimal preco;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	private StatusOrdemServico status;
	private List<ComentarioModel> comentarios;
}

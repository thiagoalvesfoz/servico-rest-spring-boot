package com.algawords.oswords.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.algawords.oswords.domain.model.enumeration.StatusOrdemServico;

public class OrdemServicoModel {
	
	private Long id;
	private ClienteModel cliente;
	private String descricao;
	private BigDecimal preco;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	private StatusOrdemServico status;
	private List<ComentarioModel> comentarios;
	
	public OrdemServicoModel() { }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}

}

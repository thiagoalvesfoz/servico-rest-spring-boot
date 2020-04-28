package com.algawords.oswords.api.model;

import java.time.OffsetDateTime;

public class ComentarioModel {
	
	public Long id;
	public OffsetDateTime dataEnvio;
	public String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

package com.algawords.oswords.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioModel {
	
	public Long id;
	public OffsetDateTime dataEnvio;
	public String descricao;
}

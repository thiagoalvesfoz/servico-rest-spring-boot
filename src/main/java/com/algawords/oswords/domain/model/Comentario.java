package com.algawords.oswords.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne //-> Muitos comentários para uma ordem de serviço
	private OrdemServico ordemServico;
	
	private String descricao;
	
	//@Column(name="data_envio")
	private OffsetDateTime dataEnvio;
}

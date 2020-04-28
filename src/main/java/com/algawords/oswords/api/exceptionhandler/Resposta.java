package com.algawords.oswords.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL) //só inclua em formato json os atributos que não forem nulos
public class Resposta {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	public Resposta(Integer status, OffsetDateTime dataHora, String titulo) {
		this.status = status;
		this.dataHora = dataHora;
		this.titulo = titulo;
	}
	
	@Data
	@AllArgsConstructor
	public static class Campo {
		private String nome;
		private String mensagem;
	}


}

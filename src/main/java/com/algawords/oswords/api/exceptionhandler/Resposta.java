package com.algawords.oswords.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(Include.NON_NULL) //só inclua em formato json os atributos que não forem nulos
public class Resposta {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	@Getter @Setter
	public static class Campo {
		private String nome;
		private String mensagem;
		
		public Campo() {}
		
		public Campo(String nome, String mensagem) {
			this.nome = nome;
			this.mensagem = mensagem;
		}
	}
	
	public Resposta() {}

	public Resposta(Integer status, OffsetDateTime dataHora, String titulo) {
		this.status = status;
		this.dataHora = dataHora;
		this.titulo = titulo;
	}

}

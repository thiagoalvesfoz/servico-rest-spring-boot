package com.algawords.oswords.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algawords.oswords.domain.service.ValidationGroup;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Cliente {
	
	@NotNull(groups = ValidationGroup.ClienteId.class)	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank //Quando um post é executa, essa anotação garante que o atributo não será nulo ou apenas espaços.
	@Size(max = 50) //valida o tamanho
	private String nome;
	
	@NotBlank
	@Size(max = 50)
	@Email //Valida o formato do email
	private String email;
	
	@NotBlank
	@Size(max = 15)
	private String telefone;	
}

package com.algawords.oswords.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algawords.oswords.domain.exception.DomainException;
import com.algawords.oswords.domain.model.enumeration.StatusOrdemServico;
import com.algawords.oswords.domain.service.ValidationGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroup.ClienteId.class)
	@NotNull						// -> cliente não pode ser nulo
	@ManyToOne 						// -> relacionamento unidirecional ( um para muitos )
//	@JoinColumn(name="cliente_id")  // -> especifica o nome da coluna chave estrangeira, spring já identifica automaticamente.
	private Cliente cliente;
	
	@NotBlank //-> pão pode estar em branco
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY) // define que no corpo Json será somente leitura, impede o preenchimento (regra de negócio)
	private StatusOrdemServico status;
	
	@OneToMany(mappedBy = "ordemServico") // -> Uma ordem de serviço tem muitos comentários
	private List<Comentario> comentarios = new ArrayList<>();;
	
	public OrdemServico() { }

	public OrdemServico(String descricao, BigDecimal preco, OffsetDateTime dataAbertura, OffsetDateTime dataFinalizacao,
			Cliente cliente, StatusOrdemServico status) {
		this.descricao = descricao;
		this.preco = preco;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
		this.cliente = cliente;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private boolean podeSerFinalizada() {
		return this.status.equals(StatusOrdemServico.ABERTA);
	}
	
	private boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
	
	public void finalizar() {
		
		if(naoPodeSerFinalizada())
			throw new DomainException("A ordem de servico não pode ser finalizada");
		
		setStatus(StatusOrdemServico.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
		
	}
	
	
	
	
	

}

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

import com.algawords.oswords.domain.exception.DomainException;
import com.algawords.oswords.domain.model.enumeration.StatusOrdemServico;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne 						
//	@JoinColumn(name="cliente_id") 
	private Cliente cliente;
	
	private String descricao;
	private BigDecimal preco;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;
	
	@OneToMany(mappedBy = "ordemServico") // -> Uma ordem de serviço tem muitos comentários
	private List<Comentario> comentarios = new ArrayList<>();;
	
	public OrdemServico() { }

	public OrdemServico(String descricao, 
						BigDecimal preco, 
						OffsetDateTime dataAbertura, 
						OffsetDateTime dataFinalizacao,
						Cliente cliente, 
						StatusOrdemServico status) {
		
		this.descricao = descricao;
		this.preco = preco;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
		this.cliente = cliente;
		this.status = status;
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

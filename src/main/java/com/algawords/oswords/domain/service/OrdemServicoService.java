package com.algawords.oswords.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algawords.oswords.domain.exception.DomainException;
import com.algawords.oswords.domain.exception.EntityNotFoundException;
import com.algawords.oswords.domain.model.Cliente;
import com.algawords.oswords.domain.model.Comentario;
import com.algawords.oswords.domain.model.OrdemServico;
import com.algawords.oswords.domain.model.enumeration.StatusOrdemServico;
import com.algawords.oswords.domain.repository.ClienteRepository;
import com.algawords.oswords.domain.repository.ComentarioRepositoy;
import com.algawords.oswords.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository bd;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepositoy comentarioRepository;
	
	public OrdemServico criar(OrdemServico os) {
		
		//Realiza a busca do cliente no banco, se não encontrar lança uma exceção de negócio
		Cliente cliente = clienteRepository.findById(os.getCliente().getId())
				.orElseThrow(() -> new DomainException("Cliente não encontrado"));
		
		os.setCliente(cliente);
		os.setStatus(StatusOrdemServico.ABERTA);
		os.setDataAbertura(OffsetDateTime.now());
		
		return bd.save(os);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico os = buscarOrdemServico(ordemServicoId);
		os.finalizar();
		bd.save(os);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String comentarioInput) {
		
		OrdemServico os = buscarOrdemServico(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setOs(os);
		comentario.setDescricao(comentarioInput);
		comentario.setDataEnvio(OffsetDateTime.now());
		
		return comentarioRepository.save(comentario);
	}
	
	public OrdemServico buscarOrdemServico(Long ordemServicoId) {
		return bd.findById(ordemServicoId)
				.orElseThrow( () -> new EntityNotFoundException("Ordem de Servico não encontrado"));
	}
}

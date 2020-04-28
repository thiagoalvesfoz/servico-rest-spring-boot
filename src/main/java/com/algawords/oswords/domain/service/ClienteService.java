package com.algawords.oswords.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algawords.oswords.domain.exception.DomainException;
import com.algawords.oswords.domain.model.Cliente;
import com.algawords.oswords.domain.repository.ClienteRepository;

@Service //componente do spring
public class ClienteService {
	
	@Autowired
	private ClienteRepository bd;
	
	public Cliente salvar(Cliente cliente) {
		
		Cliente clienteExistente = bd.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente))
			throw new DomainException("Já existe um cliente cadastrado com esse email.");
		
		return bd.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		bd.deleteById(clienteId);
	}

}

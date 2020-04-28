package com.algawords.oswords.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algawords.oswords.domain.model.Cliente;
import com.algawords.oswords.domain.repository.ClienteRepository;
import com.algawords.oswords.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes") // evita repetir o caminho "/cliente"
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	//Listando clientes
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	//Busca cliente por id
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent())
			return ResponseEntity.ok(cliente.get());
		
		return ResponseEntity.notFound().build();
	}
	
	//Cadastrando clientes
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // retorna o código 201
	public Cliente adicionar(@Valid @RequestBody Cliente newCliente) {
		return clienteService.salvar(newCliente);
		
	}
	
	//Atualizar Cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		
		if(!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente );
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		
		if(!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		//implementar regra de negócio na camada service para não quebrar o banco
		//banco de dados. Motivo: Id de usuário vinculado a ordens de servico
		
		clienteService.excluir(clienteId);	
		return ResponseEntity.noContent().build();
	}

}

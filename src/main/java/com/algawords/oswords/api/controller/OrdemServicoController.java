package com.algawords.oswords.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algawords.oswords.api.model.OrdemServicoModel;
import com.algawords.oswords.api.model.Input.OrdemServicoInput;
import com.algawords.oswords.domain.model.OrdemServico;
import com.algawords.oswords.domain.repository.OrdemServicoRepository;
import com.algawords.oswords.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService manager;
	
	@Autowired
	private OrdemServicoRepository repositoryOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return toModel(manager.criar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar(){	
		return toCollectionModel(repositoryOrdemServico.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {
		
		Optional<OrdemServico> ordemServico = repositoryOrdemServico.findById(ordemServicoId);
		
		if(ordemServico.isPresent()) {
			OrdemServicoModel ordemServicoModel = toModel(ordemServico.get());
			return ResponseEntity.ok(ordemServicoModel);
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) {
		manager.finalizarOrdemServico(ordemServicoId);
	}
	
	
	//Converte para uma represetação Model de ordem de serviço
	private OrdemServicoModel toModel(OrdemServico os) {
		return modelMapper.map(os, OrdemServicoModel.class);
	}
	
	//Converte para uma lista de representação model de ordem de servico
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(os -> toModel(os))
				.collect(Collectors.toList());
	}
	
	//Converte uma representação model para o modelo entidade objeto;
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
}

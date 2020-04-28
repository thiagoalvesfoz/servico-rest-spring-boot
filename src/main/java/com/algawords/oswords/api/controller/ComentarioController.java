package com.algawords.oswords.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algawords.oswords.api.model.ComentarioModel;
import com.algawords.oswords.api.model.Input.ComentarioInput;
import com.algawords.oswords.domain.model.Comentario;
import com.algawords.oswords.domain.model.OrdemServico;
import com.algawords.oswords.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoService manager;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
		
		var descricao = comentarioInput.getDescricao();
		Comentario comentario = manager.adicionarComentario(ordemServicoId, descricao);
		return toModel(comentario);
	}
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
		OrdemServico os = manager.buscarOrdemServico(ordemServicoId);
		return toCollectionModel(os.getComentarios());
	}
	
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> c){
		return c.stream().map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}
	
}

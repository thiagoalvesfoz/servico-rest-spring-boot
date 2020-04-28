package com.algawords.oswords.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algawords.oswords.domain.exception.DomainException;
import com.algawords.oswords.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> hadleEntityNotFound(EntityNotFoundException ex, WebRequest request){
		

		var status = HttpStatus.NOT_FOUND;	
		var body = new Resposta();
		
		body.setStatus(status.value());
		body.setTitulo(ex.getMessage());
		body.setDataHora(OffsetDateTime.now());
		
		return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> hadleDomain(DomainException ex, WebRequest request) {
		
		var status = HttpStatus.BAD_REQUEST;	 	
		var body = new Resposta();
		
		body.setStatus(status.value());
		body.setTitulo(ex.getMessage());
		body.setDataHora(OffsetDateTime.now());
		
		return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var codeStatus = status.value();
		var dataHora = OffsetDateTime.now();
		var titulo = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
		
		Resposta body = new Resposta(codeStatus, dataHora, titulo);
		var campos = new ArrayList<Resposta.Campo>();
		
		for (ObjectError erro :  ex.getBindingResult().getAllErrors()) {
			
			var nome = ((FieldError)erro).getField();
			var mensagem = erro.getDefaultMessage();
			
			campos.add(new Resposta.Campo(nome, mensagem));
		}
		
		body.setCampos(campos);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}

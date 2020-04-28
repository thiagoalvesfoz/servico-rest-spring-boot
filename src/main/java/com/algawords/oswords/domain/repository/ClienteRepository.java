package com.algawords.oswords.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algawords.oswords.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String Nome);
	List<Cliente> findByNomeContaining(String nome);
	Cliente findByEmail(String email);
}

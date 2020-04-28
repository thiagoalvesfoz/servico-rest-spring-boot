package com.algawords.oswords.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algawords.oswords.domain.model.OrdemServico;
import com.algawords.oswords.domain.model.enumeration.StatusOrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	List<OrdemServico> findByStatus(StatusOrdemServico status);
}

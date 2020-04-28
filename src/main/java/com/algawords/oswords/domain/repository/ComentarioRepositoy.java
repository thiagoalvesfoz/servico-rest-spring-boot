package com.algawords.oswords.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algawords.oswords.domain.model.Comentario;

@Repository
public interface ComentarioRepositoy extends JpaRepository<Comentario, Long> {

}

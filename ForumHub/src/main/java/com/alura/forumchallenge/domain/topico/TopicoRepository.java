package com.alura.forumchallenge.domain.topico;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotBlank;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

	Page<Topico> findByStatus(String status, Pageable pageable);

	Optional<Topico> getTopicById(Long id);

	@NotBlank
	String findByTitulo(@NotBlank String titulo);

	@NotBlank
	String findByMensagem(@NotBlank String mensagem);
	
}

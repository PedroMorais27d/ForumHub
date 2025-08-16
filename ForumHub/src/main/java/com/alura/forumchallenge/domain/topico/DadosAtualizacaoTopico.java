package com.alura.forumchallenge.domain.topico;

import com.alura.forumchallenge.domain.curso.Curso;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoTopico(
		@NotBlank
		Long id,
		String titulo,
		String mensagem,
		Curso nomeCurso) {
}

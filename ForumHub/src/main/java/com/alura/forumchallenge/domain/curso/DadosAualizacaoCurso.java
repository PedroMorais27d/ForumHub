package com.alura.forumchallenge.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DadosAualizacaoCurso(
		@NotNull
		Long id,
		String nome,
		String categoria) {
}

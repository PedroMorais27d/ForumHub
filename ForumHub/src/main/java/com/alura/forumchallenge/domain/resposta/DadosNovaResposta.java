package com.alura.forumchallenge.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosNovaResposta(
		@NotNull
		Long id,
		@NotBlank
		String mensagem) {
}

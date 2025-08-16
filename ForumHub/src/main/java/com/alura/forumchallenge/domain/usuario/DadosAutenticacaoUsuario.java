package com.alura.forumchallenge.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoUsuario(
		@NotBlank
		@Email
		String email,
		@NotBlank
		String senha) {

}

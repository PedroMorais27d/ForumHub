package com.alura.forumchallenge.domain.resposta;

import com.alura.forumchallenge.domain.usuario.Usuario;

public record DadosDetalheRespostaUsuario(
        Long id,
        String nome
) {

	public DadosDetalheRespostaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome());
    }
}
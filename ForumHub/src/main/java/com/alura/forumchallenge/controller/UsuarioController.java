package com.alura.forumchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forumchallenge.domain.usuario.DadosDetalhamentoUsuario;
import com.alura.forumchallenge.domain.usuario.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
    @GetMapping
    public ResponseEntity<DadosDetalhamentoUsuario> detalheUsuario(Authentication authentication) {
        String usuarioLogado = authentication.getName();
        DadosDetalhamentoUsuario dados = usuarioService.detalhaUsuario(usuarioLogado);
        return ResponseEntity.ok(dados);
    }
	

}

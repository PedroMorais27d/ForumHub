package com.alura.forumchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forumchallenge.domain.usuario.DadosAutenticacaoUsuario;
import com.alura.forumchallenge.domain.usuario.Usuario;
import com.alura.forumchallenge.infra.security.DadosTokenJWT;
import com.alura.forumchallenge.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> logar(@RequestBody @Valid DadosAutenticacaoUsuario dados, UriComponentsBuilder uriBuilder) {
    	
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);          
        var tokenJWT = tokenService.gerarToken((Usuario)authentication.getPrincipal());          
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        
    }
}
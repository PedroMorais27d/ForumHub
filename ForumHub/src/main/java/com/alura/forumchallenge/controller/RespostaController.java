package com.alura.forumchallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forumchallenge.domain.resposta.DadosNovaResposta;
import com.alura.forumchallenge.domain.resposta.RespostaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/resposta")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    private RespostaService respostaService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> novaResposta(@RequestBody @Valid DadosNovaResposta dados,
                                       Authentication authentication) {
        String usuarioLogado = authentication.getName();
        respostaService.cadastroResposta(dados, usuarioLogado);
        return ResponseEntity.ok().build();
    }
    
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarResposta(@RequestBody @Valid @PathVariable("id") Long id, DadosNovaResposta dados,
            								Authentication authentication) throws Exception {
    	String usuarioLogado = authentication.getName();
    	respostaService.atualizaResposta(dados, usuarioLogado);
		return ResponseEntity.ok("Atualizacao Feita");
    	
    }
    
}

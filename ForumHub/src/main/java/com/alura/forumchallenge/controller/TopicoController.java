package com.alura.forumchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import com.alura.forumchallenge.domain.topico.DadosAtualizacaoTopico;
import com.alura.forumchallenge.domain.topico.DadosDetalhamentoTopico;
import com.alura.forumchallenge.domain.topico.DadosNovoTopico;
import com.alura.forumchallenge.domain.topico.TopicoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
	
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> novoTopico(@RequestBody @Valid DadosNovoTopico dados,
    								UriComponentsBuilder uriBuilder,
    								Authentication authentication) throws Exception {
    	
        String usuarioLogado = authentication.getName();
        
        return ResponseEntity.ok(topicoService.cadastrar(dados, usuarioLogado));
    }
    
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listaTopico(
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        Page<DadosDetalhamentoTopico> detalhes = topicoService.listaTopicos(pageable);
        
        if (detalhes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(detalhes);
        }
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> Atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados,
    								Authentication authentication) {
    	
    	String usuarioLogado = authentication.getName();
    	
		return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoService.atualizarTopico(dados,usuarioLogado)));	
    }
    
    @GetMapping("/{id}")
    public List<DadosDetalhamentoTopico> listaTopicoId(@PathVariable("id") Long id) {
    	    	
    	return topicoService.detalheTopico(id);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> DesativarOuResolvido(@PathVariable("id") Long id) {
    	
    	return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoService.deletar(id)));
    }

}

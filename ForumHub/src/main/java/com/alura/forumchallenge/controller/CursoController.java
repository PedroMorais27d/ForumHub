package com.alura.forumchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forumchallenge.domain.curso.CursoService;
import com.alura.forumchallenge.domain.curso.DadosAualizacaoCurso;
import com.alura.forumchallenge.domain.curso.DadosDetalhamentoCurso;
import com.alura.forumchallenge.domain.curso.DadosNovoCurso;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCurso> cadastraCurso(@RequestBody @Valid DadosNovoCurso dados) {
		return ResponseEntity.ok(cursoService.cadasroCurso(dados));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoCurso> atualizaCurso(@RequestBody @Valid DadosAualizacaoCurso dados) {
		return ResponseEntity.ok(cursoService.atualizaCurso(dados));
	}
	
	@GetMapping
	public ResponseEntity<List<DadosDetalhamentoCurso>> listaCurso() {
		List<DadosDetalhamentoCurso> listaCurso = cursoService.listaCursos();
        return ResponseEntity.ok(listaCurso);
	}

}

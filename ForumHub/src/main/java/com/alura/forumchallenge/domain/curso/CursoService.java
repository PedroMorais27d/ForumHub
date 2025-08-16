package com.alura.forumchallenge.domain.curso;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class CursoService {
	
	@Autowired
	CursoRepository repository;
	
	public DadosDetalhamentoCurso cadasroCurso(DadosNovoCurso dados){
		Curso curso = new Curso(dados.nome(),dados.categoria());
		DadosDetalhamentoCurso detalhado = new DadosDetalhamentoCurso( curso.getId(),curso.getNome(),curso.getCategoria());
		repository.save(curso);
		return detalhado;
	}
	
    public DadosDetalhamentoCurso atualizaCurso(@Valid DadosAualizacaoCurso dados) {
        Optional<Curso> cursoOptional = repository.findById(dados.id());
        if (!cursoOptional.isPresent()) {
            throw new EntityNotFoundException("Curso nao enconrado nesse id: " + dados.id());
        }

        Curso curso = cursoOptional.get();

        if (dados.nome() != null) {
            curso.setNome(dados.nome());
        }
        if (dados.categoria() != null) {
            curso.setCategoria(dados.categoria());
        }

        repository.save(curso);
        
        return new DadosDetalhamentoCurso(curso);
    }
	
    public Curso buscarCurso(String nomeCurso){
    	var segura = repository.BuscaCurso(nomeCurso);
    	System.out.println(segura);
        return segura;
    }
    
    public List<DadosDetalhamentoCurso> listaCursos (){
    	List<Curso> cursos = repository.findAll();
    	if(cursos == null)throw new IllegalArgumentException("Nao a cursos");;
		return cursos.stream()
				.map(curso -> new DadosDetalhamentoCurso(curso))
				.collect(Collectors.toList());
    }

}

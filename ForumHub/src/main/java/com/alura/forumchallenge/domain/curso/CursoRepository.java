package com.alura.forumchallenge.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	@Query("SELECT c FROM Curso c WHERE LOWER(c.nome) = LOWER(:nomeCurso)")
    Curso BuscaCurso(@Param("nomeCurso") String nomeCurso);

}

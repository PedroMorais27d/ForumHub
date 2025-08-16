package com.alura.forumchallenge.domain.topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.forumchallenge.domain.curso.CursoService;
import com.alura.forumchallenge.domain.usuario.Usuario;
import com.alura.forumchallenge.domain.usuario.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class TopicoService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private TopicoRepository repository;

	@Transactional
	public DadosDetalhamentoTopico cadastrar(@Valid DadosNovoTopico dados, String usuarioLogado) {
		
		Usuario usuario = usuarioService.findByLogin(usuarioLogado);
				
        var curso = cursoService.buscarCurso(dados.nomeCurso());
        
		Topico topico = new Topico();
		
		if (repository.findByTitulo(dados.titulo()) != null || repository.findByMensagem(dados.mensagem()) != null) {
		    throw new IllegalArgumentException("Tópico ou mensagem já criado...");
		}
		
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(usuario);
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus("Ativo");
        
        repository.save(topico);

        return new DadosDetalhamentoTopico(topico);
	}
	
    public Page<DadosDetalhamentoTopico> listaTopicos(Pageable pageable) {
        Page<Topico> topicos = repository.findByStatus("Ativo", pageable);
        return topicos.map(topico -> new DadosDetalhamentoTopico(topico));
    }
	
	public List<DadosDetalhamentoTopico> detalheTopico(@Valid Long id) {
		
		Optional<Topico> busca = repository.findById(id);
			
		return busca.stream()
                .map(topico -> new DadosDetalhamentoTopico(topico))
                .collect(Collectors.toList());
		
	}
	
	public Topico atualizarTopico(@Valid DadosAtualizacaoTopico dados, String usuarioLogado) {
		
		Usuario usuario = usuarioService.findByLogin(usuarioLogado);
		
		if(usuario.getId() != dados.id()){
			throw new IllegalArgumentException("Nao pode completar a atualizacao...");
		}
		
		Topico topico = (Topico) detalheTopico(dados.id());
		
		if(dados.titulo() != null) {
			topico.setTitulo(dados.titulo());
		}	
		
		if(dados.mensagem() != null) {
			topico.setMensagem(dados.mensagem());
		}	
		
		if(dados.nomeCurso() != null) {
			topico.setCurso(dados.nomeCurso());
		}
		
		return repository.save(topico);
		
	}

	public Topico deletar(@Valid Long id) {
		Topico topico = repository.getReferenceById(id);
		topico.desativaTopico();
		return topico;
	}

}

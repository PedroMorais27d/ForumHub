package com.alura.forumchallenge.domain.resposta;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.forumchallenge.domain.topico.Topico;
import com.alura.forumchallenge.domain.topico.TopicoRepository;
import com.alura.forumchallenge.domain.usuario.Usuario;
import com.alura.forumchallenge.domain.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class RespostaService {
	
	@Autowired
	private RespostaRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
    @Transactional
    public void cadastroResposta(@Valid DadosNovaResposta dados, String usuarioLogado) {

        Usuario user = (Usuario) usuarioRepository.findByEmail(usuarioLogado);

        Topico topico = topicoRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        Resposta resposta = new Resposta();
        resposta.setDatacriacao(LocalDateTime.now());
        resposta.setMensagem(dados.mensagem());
        resposta.setAutor(user);
        resposta.setTopico(topico);

        repository.save(resposta);
    }
    
    @Transactional
    public void atualizaResposta(@Valid DadosNovaResposta dados, String usuarioLogado) throws Exception {
        Optional<Resposta> optionalResposta = repository.findById(dados.id());

        if (optionalResposta.isPresent()) {
            Resposta resposta = optionalResposta.get();
            if (dados.mensagem() != null) {
                resposta.setMensagem(dados.mensagem());
            }
            repository.save(resposta);
        } else {
            throw new Exception("Resposta with id " + dados.id() + " not found.");
        }
    }

}

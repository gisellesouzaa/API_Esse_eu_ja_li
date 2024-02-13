package br.com.esse_eu_ja_li.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.esse_eu_ja_li.model.Usuario;
import br.com.esse_eu_ja_li.model.dto.saida.UsuarioRankingDto;
import br.com.esse_eu_ja_li.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Usuario getById(Long id) {
		Optional<Usuario> usuario = repository.findById(id);

		if (!usuario.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado", null);
		}
		
		return usuario.get();
	}
	  
	
	  public List<UsuarioRankingDto> rankingUsuarios() {
	        Pageable topUsersPageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "pontuacao"));
	        
	        List<Usuario> usuarios = repository.findAll(topUsersPageable).getContent();
	        
	        List<UsuarioRankingDto> saidasDtos = mapper.map(usuarios, new TypeToken<List<UsuarioRankingDto>>() {
			}.getType());
	        
	        return saidasDtos; 
	   }
	  
	public Usuario post(Usuario usuario) {		
		return repository.save(usuario);
	}	
	
	public Usuario getByEmail(String email) {
		Usuario usuario = repository.findByEmail(email);

		return usuario;
	}
	  
}

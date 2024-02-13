package br.com.esse_eu_ja_li.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.esse_eu_ja_li.model.Livro;
import br.com.esse_eu_ja_li.model.dto.saida.LivroListaDto;
import br.com.esse_eu_ja_li.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<LivroListaDto> getAll() {
		List<Livro> livros = repository.findAll();
		
		List<LivroListaDto> saidasDtos = mapper.map(livros, new TypeToken<List<LivroListaDto>>() {
		}.getType());
		
		return saidasDtos;
	}
	
	
	public Livro getById(Long id) {
		Optional<Livro> livro = repository.findById(id);

		if (!livro.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado", null);
		}
		
		return livro.get();
	}
	
	public Livro post(Livro livro) {		
		return repository.save(livro);
	}
}

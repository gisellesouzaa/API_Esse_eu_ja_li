package br.com.esse_eu_ja_li.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esse_eu_ja_li.model.Livro;
import br.com.esse_eu_ja_li.model.dto.saida.LivroListaDto;
import br.com.esse_eu_ja_li.service.LivroService;
import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("livro")
@Log4j2
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@GetMapping
	public List<LivroListaDto> getAll() {
		log.info("listar");
		return service.getAll();
	}
	
	@GetMapping("id/{id}")
	public Livro getById(@PathVariable("id") Long id) {
		log.info("getById: {}", id);
		return service.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Livro post(@Valid @RequestBody Livro livro) {
		log.info("salvar: {}", livro);
		return service.post(livro);
	}
}

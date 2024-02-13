package br.com.esse_eu_ja_li.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esse_eu_ja_li.model.Leitura;
import br.com.esse_eu_ja_li.service.LeituraService;
import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("leitura")
@Log4j2
public class LeituraController {
	
	@Autowired
	private LeituraService service;
	
	@GetMapping
	public List<Leitura> getAll() {
		log.info("listar");
		return service.getAll();
	}
	
	@PostMapping("/idLivro/{idLivro}/idUsuario/{idUsuario}")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean marcarLivroLido(@PathVariable("idLivro") Long idLivro, @PathVariable("idUsuario") Long idUsuario) {
		log.info("marcarLivroLido");
		
		service.marcarLivroLido(idLivro, idUsuario);
		
		return true;
	}

}

package br.com.esse_eu_ja_li.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esse_eu_ja_li.model.Trofeu;
import br.com.esse_eu_ja_li.service.TrofeuService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("trofeu")
@Log4j2
public class TrofeuController {
	
	@Autowired
	private TrofeuService trofeuService;
	
	@GetMapping
	public List<Trofeu> getAll() {
		log.info("listar");
		return trofeuService.getAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Trofeu post(@Valid @RequestBody Trofeu trofeu) {
		log.info("salvar: {}", trofeu);
		return trofeuService.post(trofeu);
	}
}

package br.com.esse_eu_ja_li.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esse_eu_ja_li.model.Usuario;
import br.com.esse_eu_ja_li.model.UsuarioLogin;
import br.com.esse_eu_ja_li.model.dto.saida.UsuarioRankingDto;
import br.com.esse_eu_ja_li.service.UsuarioService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("usuario")
@Log4j2
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("id/{id}")
	public Usuario getById(@PathVariable("id") Long id) {
		log.info("getById: {}", id);
		return service.getById(id);
	}
	
	@GetMapping("/ranking")
	public List<UsuarioRankingDto> rankingUsuarios() {
		log.info("rankingUsuarios");
		return service.rankingUsuarios();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario post(@Valid @RequestBody Usuario usuario) {
		log.info("salvar: {}", usuario);
		return service.post(usuario);
	}

	//Login
   @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody UsuarioLogin usuarioLogin) { //receber email e senha
	   
	   Usuario usuario = service.getByEmail(usuarioLogin.getEmail()); 
	  
	   if (usuario != null && usuarioLogin.getEmail().equals(usuario.getEmail()) && usuarioLogin.getSenha().equals(usuario.getSenha())) {
		   return ResponseEntity.ok("Login bem-sucedido!");
       } else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Verifique seu email e senha.");
       }
   }
}

package br.com.esse_eu_ja_li.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esse_eu_ja_li.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);
}

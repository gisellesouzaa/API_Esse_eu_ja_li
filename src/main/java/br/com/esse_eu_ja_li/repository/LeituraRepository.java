package br.com.esse_eu_ja_li.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esse_eu_ja_li.model.Leitura;
import br.com.esse_eu_ja_li.model.Usuario;

public interface LeituraRepository extends JpaRepository<Leitura, Long>{

	List<Leitura> findByUsuario(Usuario usuario);
}

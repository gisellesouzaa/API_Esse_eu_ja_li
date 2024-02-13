package br.com.esse_eu_ja_li.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esse_eu_ja_li.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}

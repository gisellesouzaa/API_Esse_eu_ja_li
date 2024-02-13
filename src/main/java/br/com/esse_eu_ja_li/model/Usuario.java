package br.com.esse_eu_ja_li.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank(message = "Obrigatorio")
//	@Size(max = 255, message = "O atributo nome deve ter no maximo 255 caracteres")
	private String nome;
	
//	@Email(message = "Invalido")
//	@NotBlank(message = "Obrigatorio")
//	@Size(max = 255, message = "O atributo usuario deve ter no maximo 255 caracteres")
	private String email;
	
//	@NotBlank(message = "Obrigatorio")
//	@Size(min = 8, message = "O atributo senha deve ter no minimo 8 caracteres")
	private String senha;
	
	private Integer pontuacao;
	
	@OneToMany
	private List<Trofeu> trofeu;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Leitura> leitura;
	
	public void addTrofeuNaLista(Trofeu trofeu) {
		this.trofeu.add(trofeu); 
	}
}

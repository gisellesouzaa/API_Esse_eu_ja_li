package br.com.esse_eu_ja_li.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="leitura")
public class Leitura {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer pontuacaoPorLeitura;
	
 	@ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;
 	
	@ManyToOne
    @JoinColumn(name = "livro")
    private Livro livro;

	public Leitura(Integer pontuacaoPorLeitura, Usuario usuario, Livro livro) {
		this.pontuacaoPorLeitura = pontuacaoPorLeitura;
		this.usuario = usuario;
		this.livro = livro;
	}
}

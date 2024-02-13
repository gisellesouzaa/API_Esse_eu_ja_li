package br.com.esse_eu_ja_li.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.esse_eu_ja_li.model.Leitura;
import br.com.esse_eu_ja_li.model.Livro;
import br.com.esse_eu_ja_li.model.Trofeu;
import br.com.esse_eu_ja_li.model.Usuario;
import br.com.esse_eu_ja_li.repository.LeituraRepository;
import br.com.esse_eu_ja_li.repository.TrofeuRepository;

@Service
public class LeituraService {
	
	@Autowired
	private LeituraRepository leituraRepository;	
	
	@Autowired
	private TrofeuRepository trofeuRepository;	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LivroService livroService;
	
	public List<Leitura> getAll() {
		List<Leitura> leituras = leituraRepository.findAll();	
		return leituras;
	}
	
	public List<Leitura> buscaLeiturasDoUsuario(Usuario usuario) {
		List<Leitura> leiturasDoUsuario = leituraRepository.findByUsuario(usuario);	
		return leiturasDoUsuario;
	}
	
	public Boolean marcarLivroLido(Long idLivro, Long idUsuario) {
		
		Usuario usuario = usuarioService.getById(idUsuario);
		Livro livro = livroService.getById(idLivro);
		
		Integer pontuacaoAntigaDoUsuario = usuario.getPontuacao();
		Integer pontuacaoPorLeitura =  calcularPontos(livro.getQuantidadePaginas());	
				
		usuario.setPontuacao(pontuacaoAntigaDoUsuario + pontuacaoPorLeitura);
		
		Leitura leitura = new Leitura(pontuacaoPorLeitura, usuario, livro);
		leituraRepository.save(leitura);
		
		String generoParaAnalisar = livro.getEstilo().name();
		ReceberTrofeu(usuario, generoParaAnalisar);			
	return true;
	}
	
	public Integer calcularPontos(Integer numeroPaginas) {
		Integer pontosPorLivroLido = 1;
		Integer pontosPorCada100PaginasLidas = 1;
		Integer pontosAdicionaisPorEssaLeitura = 0;

		if (numeroPaginas > 100) {
			pontosAdicionaisPorEssaLeitura = (numeroPaginas / 100) * pontosPorCada100PaginasLidas;			
		}
		
        return pontosPorLivroLido + pontosAdicionaisPorEssaLeitura;
	}	
	
	 public Boolean ReceberTrofeu(Usuario usuario, String generoParaAnalisar) {
		 
	        List<Leitura> leituras = usuario.getLeitura();
	        Integer contadorDeLeituras = 0; 
	        
	        for (Leitura leitura : leituras) {
	        	String generoDaLeitura = leitura.getLivro().getEstilo().name();
	        	
	        	if (generoDaLeitura == generoParaAnalisar) {
	        		contadorDeLeituras++;
	        	}
	        }
	        
	        if (contadorDeLeituras % 5 == 0) {                
                Trofeu trofeu = new Trofeu("Leitor de " + generoParaAnalisar);
        		usuario.addTrofeuNaLista(trofeu); 
        		trofeuRepository.save(trofeu);
        		return true;
	        }
	        return false;
	    }
}

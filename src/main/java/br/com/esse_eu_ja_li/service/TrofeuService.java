package br.com.esse_eu_ja_li.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.esse_eu_ja_li.model.Trofeu;
import br.com.esse_eu_ja_li.repository.TrofeuRepository;

@Service
public class TrofeuService {
	
	@Autowired
	private TrofeuRepository trofeuRepository;
	
	public List<Trofeu> getAll() {
		List<Trofeu> trofeus = trofeuRepository.findAll();
		
		return trofeus;
	}

	public Trofeu post(Trofeu trofeu) {		
		return trofeuRepository.save(trofeu);
	}	
}

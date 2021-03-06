package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BuildPC;
import com.example.demo.model.Periferica;
import com.example.demo.repository.BuildPCRepository;
import com.example.demo.repository.PerifericaRepository;

@Service
public class PerifericaService {

	@Autowired
	private PerifericaRepository perifericaRepository; 
	
	@Autowired
	private BuildPCRepository buildRepository;

	@Transactional
	public Periferica inserisci(Periferica p) {
		return perifericaRepository.save(p);
	}

	@Transactional
	public List<Periferica> tutti() {
		return (List<Periferica>) perifericaRepository.findAll();
	}

	@Transactional
	public Periferica perifericaPerId(Long id) {
		Optional<Periferica> optional = perifericaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Periferica p) {
		List<Periferica> periferiche = this.perifericaRepository.findByNome(p.getNome());
		if (periferiche.size() > 0)
			return true;
		else 
			return false;
	}


	@Transactional
	public void deleteById(Long id) {
		perifericaRepository.deleteById(id);
	}
	
	
	@Transactional
	public List<Periferica> displayPeriferiche() {
		
		List<Periferica> listaPeriferica = this.tutti();
		
		List<Periferica> listaPeriferica2 = new ArrayList<Periferica>();
		
		for (Periferica p : listaPeriferica) {
			if (p.getTipologia().equals("display"))
				listaPeriferica2.add(p);
		}
		return listaPeriferica2;
	}
	
	
	@Transactional
	public List<Periferica> mousePeriferiche() {
		List<Periferica> listaPeriferica = this.tutti();
		
		List<Periferica> listaPeriferica2 = new ArrayList<Periferica>();


		for (Periferica p : listaPeriferica) {
			if (p.getTipologia().equals("mouse"))
				listaPeriferica2.add(p);
		}
		return listaPeriferica2;
	}
	
	
	@Transactional
	public List<Periferica> tastierePeriferiche() {
		
		List<Periferica> listaPeriferica = this.tutti();
		
		List<Periferica> listaPeriferica2 = new ArrayList<Periferica>();


		for (Periferica p : listaPeriferica) {
			if (p.getTipologia().equals("tastiera"))
				listaPeriferica2.add(p);
		}
		return listaPeriferica2;
	}
	
	
	@Transactional
	public List<Periferica> cuffiePeriferiche() {
		List<Periferica> listaPeriferica = this.tutti();
		
		List<Periferica> listaPeriferica2 = new ArrayList<Periferica>();


		for (Periferica p : listaPeriferica) {
			if (p.getTipologia().equals("cuffia"))
				listaPeriferica2.add(p);
		}
		return listaPeriferica2;
	}
	
	
	@Transactional
	public List<Periferica> extraPeriferiche() {
		List<Periferica> listaPeriferica = this.tutti();
		
		List<Periferica> listaPeriferica2 = new ArrayList<Periferica>();


		for (Periferica p : listaPeriferica) {
			if (p.getTipologia().equals("extra"))
				listaPeriferica2.add(p);
		}
		return listaPeriferica2;
	}

	public List<BuildPC> buildDiPeriferica(Periferica perif) {
		
		List<BuildPC> listaBuildXComponente = new ArrayList<BuildPC>();

		for (BuildPC b : buildRepository.findAll()) {
			for (Periferica p : b.getPeriferiche()) {
				if (p.getId() == perif.getId())
					listaBuildXComponente.add(b);
			}
		}
		return listaBuildXComponente;
	}

}
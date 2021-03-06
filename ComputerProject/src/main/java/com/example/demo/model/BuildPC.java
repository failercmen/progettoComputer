package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class BuildPC {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank // no spazi vuoti o bianchi
	private String nome;

	@NotBlank
	private String descrizione;

	// @NotBlank
	private float prezzoTotale;

	// @OneToMany
	// private List<Componente> componenti;

	@ManyToMany(mappedBy = "buildsComponenti")
	private List<Componente> componenti;

	@ManyToMany(mappedBy = "buildsPeriferiche")
	private List<Periferica> periferiche;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public List<Componente> getComponenti() {
		return componenti;
	}

	public void setComponenti(List<Componente> componenti) {
		this.componenti = componenti;
	}

	public List<Periferica> getPeriferiche() {
		return periferiche;
	}

	public void setPeriferiche(List<Periferica> periferiche) {
		this.periferiche = periferiche;
	}

}

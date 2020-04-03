package com.cinema.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.dto.RechercheDTO;
import com.cinema.models.Seance;
import com.cinema.services.SeanceService;

@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {
	
	@Autowired
	private SeanceService service;
	
	@PostMapping("")
	public Seance save(@RequestBody Seance s) {
		return this.service.save(s);
	}
	
	@GetMapping("")
	public List<Seance> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Seance> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@PutMapping("")
	public Seance update(@RequestBody Seance s) {
		return this.service.update(s);
	}

	@DeleteMapping("{id}")
	public void deleteById(@RequestBody @PathVariable String id) {
		this.service.deleteById(id);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Seance s) {
		this.service.delete(s);
	}
	
	@PutMapping("{sId}/assister/{aId}")
	public Seance addClient(@PathVariable String sId, @PathVariable String aId) {
		return this.service.addClient(sId, aId);
	}
	
	@PutMapping("{sId}/films/{fId}")
	public Seance addFilm(@PathVariable String sId, @PathVariable String fId) {
		return this.service.addFilm(sId, fId);
	}
	
	@PutMapping("{sId}/salles/{saId}")
	public Seance addSalle(@PathVariable String sId, @PathVariable String saId) {
		return this.service.addSalle(sId, saId);
	}
	
	@GetMapping("/titre/{titre}")
	public List<Seance> findSeanceByTitreFilm(@PathVariable String titre) {
		return this.service.findBySeanceByTitreFilmLike(titre);
	}
	
	@GetMapping("/{id}/recette")
	public float findRecetteSeance(@PathVariable String id) {
		return this.service.findRecetteSeance(id);
	}
	
	@GetMapping("/{id}/places")
	public int findPlacesRestantesSeance(@PathVariable String id) {
		return this.service.findPlacesRestantesSeance(id);
	}
	
	@GetMapping("/horaires/{min}/{max}")
	public List<Seance> findByDateBetween(@PathVariable LocalDateTime min, @PathVariable LocalDateTime max) {
		return this.service.findByDateBetween(min, max);
	}
	
	
	@GetMapping("/genre/{genre}")
	public List<Seance> findAllByFilmGenre(@PathVariable String genre) {
		return this.service.findAllByFilmGenre(genre);
	}
	
	@GetMapping("/age/{age}")
	public List<Seance> findAllByFilmAgeLimite(@PathVariable int age) {
		return this.service.findAllByFilmAgeLimite(age);
	}
	
	@GetMapping("/recherche")
	public List<Seance> rechercheByGenreFilmOrPlageHoraireOrAgeOrTypeSeance(@RequestBody RechercheDTO recherche) {
		return this.service.rechercheByGenreFilmOrPlageHoraireOrAgeOrTypeSeance(recherche);
	}
	
}

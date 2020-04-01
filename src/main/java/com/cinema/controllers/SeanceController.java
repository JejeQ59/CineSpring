package com.cinema.controllers;

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
	public void delete(@RequestBody @PathVariable String id) {
		this.service.delete(id);
	}
	
	@PutMapping("addClientSeance/{sId}/clients/{aId}")
	public Seance addClient(@PathVariable String sId, @PathVariable String aId) {
		return this.service.addClient(sId, aId);
	}
	
	@PutMapping("addfilm/{sId}/films/{fId}")
	public Seance addFilm(@PathVariable String sId, @PathVariable String fId) {
		return this.service.addFilm(sId, fId);
	}
	
	@PutMapping("addSalle/{sId}/salles/{saId}")
	public Seance addSalle(@PathVariable String sId, @PathVariable String saId) {
		return this.service.addSalle(sId, saId);
	}
	
}

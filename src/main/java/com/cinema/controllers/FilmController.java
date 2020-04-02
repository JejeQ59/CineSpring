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

import com.cinema.models.Film;
import com.cinema.services.FilmService;

@RestController
@CrossOrigin
@RequestMapping("films")
public class FilmController {
	
	@Autowired
	private FilmService service;
	
	@PostMapping("")
	public Film save(@RequestBody Film entity) {
		return this.service.save(entity);
	}
	
	@GetMapping("")
	public List<Film> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Film> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@PutMapping("")
	public Film update(@RequestBody Film f) {
		return this.service.update(f);
	}

	@DeleteMapping("{id}")
	public void deleteById(@RequestBody @PathVariable String id) {
		this.service.deleteById(id);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Film f) {
		this.service.delete(f);
	}
	
	
	@GetMapping("/{id}/recette")
	public float findRecette(@PathVariable String id) {
		return this.service.findRecette(id);
	}
	
}

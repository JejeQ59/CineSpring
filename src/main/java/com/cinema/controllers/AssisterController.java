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

import com.cinema.models.Assister;
import com.cinema.models.Seance;
import com.cinema.services.AssisterService;

@RestController
@CrossOrigin
@RequestMapping("assistes")
public class AssisterController {
	
	@Autowired
	private AssisterService service;
	
	@PostMapping("")
	public Assister save(@RequestBody Assister entity) {
		return this.service.save(entity);
	}
	
	@GetMapping("")
	public List<Assister> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Assister> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@PutMapping("")
	public Assister update(@RequestBody Assister a) {
		return this.service.update(a);
	}

	@DeleteMapping("{id}")
	public void deleteById(@RequestBody @PathVariable String id) {
		this.service.deleteById(id);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Assister a) {
		this.service.delete(a);
	}
	
	@PutMapping("{aId}/note/{note}")
	public Seance addNote(@PathVariable String aId, @PathVariable Integer note) {
		return this.service.addNote(aId, note);
	}
}

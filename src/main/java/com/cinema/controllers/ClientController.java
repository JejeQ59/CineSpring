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

import com.cinema.models.Client;
import com.cinema.services.ClientService;

@RestController
@CrossOrigin
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@PostMapping("")
	public Client save(@RequestBody Client entity) {
		return this.service.save(entity);
	}
	
	@GetMapping("")
	public List<Client> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Client> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@PutMapping("")
	public Client update(@RequestBody Client c) {
		return this.service.update(c);
	}

	@DeleteMapping("{id}")
	public void deleteById(@RequestBody @PathVariable String id) {
		this.service.deleteById(id);
	}
	
	@DeleteMapping("")
	public void delete(@RequestBody Client c) {
		this.service.delete(c);
	}
	
}

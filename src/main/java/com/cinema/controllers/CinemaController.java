package com.cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.dto.CinemaDTO;
import com.cinema.services.CinemaDTOService;

@RestController
@CrossOrigin
@RequestMapping("cinemas")
public class CinemaController {
	
	@Autowired
	private CinemaDTOService service;
	
	@PostMapping("")
	public CinemaDTO save(@RequestBody CinemaDTO cinema) {
		return this.service.save(cinema);
	}
	
}

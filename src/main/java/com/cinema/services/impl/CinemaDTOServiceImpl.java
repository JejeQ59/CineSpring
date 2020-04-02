package com.cinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.models.Salle;
import com.cinema.repositories.CinemaRepository;
import com.cinema.services.CinemaDTOService;
import com.cinema.services.SalleService;

@Service
public class CinemaDTOServiceImpl implements CinemaDTOService {

	@Autowired
	private CinemaRepository repo;
	
	@Autowired
	private SalleService serviceS;

	@Override
	public CinemaDTO save(CinemaDTO c) {
		Cinema cine = c.getCinema();
		this.repo.save(cine);
		for(Salle s : c.getSalles())
		{
			s.setCinema(cine);
			this.serviceS.save(s);
		}
		
		return c;
	}

}

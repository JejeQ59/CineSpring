package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Client;

public interface ClientService {

	public Client save(Client c);
	public List<Client> findAll();
	public Optional<Client> findById(String id);
	public Client update(Client c);
	public void deleteById(String id);
	public void delete(Client c);

}

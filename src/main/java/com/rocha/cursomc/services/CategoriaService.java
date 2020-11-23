package com.rocha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocha.cursomc.domain.Categoria;
import com.rocha.cursomc.repositories.CategoriaRepository;
import com.rocha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id: " + id + ", tipo: " + Categoria.class.getName()));

	}

	// Quando o id e null ele insere
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	// quando o id nao e null ele atualiza por isso o PUT
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}

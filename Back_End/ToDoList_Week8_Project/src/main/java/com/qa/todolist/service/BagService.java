package com.qa.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.dto.BagDto;
import com.qa.todolist.persistance.domain.Bag;
import com.qa.todolist.persistance.repo.BagRepo;
import com.qa.todolist.utils.SpringBeanUtil;

@Service
public class BagService {

	private BagRepo repo;

	private ModelMapper mapper;

	private BagDto mapToDTO(Bag bag) {
		return this.mapper.map(bag, BagDto.class);
	}

	@Autowired
	public BagService(BagRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public BagDto create(Bag bag) {
		return this.mapToDTO(this.repo.save(bag));
	}

	public List<BagDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

	}

	public BagDto readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}

	public BagDto update(BagDto bagDto, Long id) {

		Bag toUpdate = this.repo.findById(id).orElseThrow();

		toUpdate.setListName(bagDto.getListName());

		SpringBeanUtil.mergeNotNull(bagDto, toUpdate);

		return this.mapToDTO(this.repo.save(toUpdate));
	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}

package com.qa.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolist.dto.BagDto;
import com.qa.todolist.persistance.domain.Bag;
import com.qa.todolist.service.BagService;

@RestController
@CrossOrigin
@RequestMapping("/bag")
public class BagController {

	private BagService service;

	@Autowired
	public BagController(BagService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<BagDto> create(@RequestBody Bag bag) {
		BagDto created = this.service.create(bag);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<BagDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<BagDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<BagDto> update(@PathVariable Long id, @RequestBody BagDto bagDto) {

		return new ResponseEntity<>(this.service.update(bagDto, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BagDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

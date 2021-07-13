package com.example.demo.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.CategorieRepository;
import com.example.demo.Services.CategorieService;
import com.example.demo.models.Categorie;

@RestController
@RequestMapping ("/categorie")
@CrossOrigin(origins="http://localhost:4200")  
public class ControllerCategorie {
private CategorieService service ;
private CategorieRepository repository;

@Autowired
public ControllerCategorie(CategorieService service,CategorieRepository repository) {
	super();
	this.service = service;
	this.repository = repository;
}
@GetMapping
public List<Categorie> getall(){
    return repository.findAll();
}
@GetMapping("{id}")
public Categorie findById(@PathVariable("id") long id){
    return repository.findById(id).get();
}
@PostMapping
public Categorie create(@RequestBody Categorie categories){ 
	return service.createCategories(categories);
}
@PutMapping("/{id}")
public Categorie updateCategories(@PathVariable("id") long id , @RequestBody Categorie categories ){
    return service.updateCategories(id,categories);
 }

@DeleteMapping("/{id}")
public String deleteCat(@PathVariable("id") long id){
	return service.deleteCategories(id);
}
@ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
}
}





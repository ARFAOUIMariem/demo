package com.example.demo.Controller;
    import java.util.List;
	import java.util.NoSuchElementException;
	import java.util.Optional;
	import java.util.stream.Collectors;

	import org.modelmapper.ModelMapper;
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
	import com.example.demo.Dto.DtoProduit;
	import com.example.demo.Repositories.ProduitRepository;
	import com.example.demo.Services.ProduitService;
	import com.example.demo.models.Produit;

	@CrossOrigin(origins="http://localhost:9090")  
	@RestController
	@RequestMapping("/produits")
public class ControllerProduit {
		private ProduitRepository repositories;
		private ProduitService service;
		private ModelMapper mapper;
	
    @Autowired
	public ControllerProduit(ProduitRepository repositories, ProduitService service, ModelMapper mapper) {
			super();
			this.repositories = repositories;
			this.service = service;
			this.mapper = mapper;
		}
	@GetMapping
	public List<Produit> getall(){
	    return repositories.findAll();
	}
	@GetMapping("/{id}")
	public Produit findById(@PathVariable("id") long id){
		 Optional<Produit> produits = repositories.findById(id);
	     return produits.orElseThrow(()->new NoSuchElementException());
	  
	}
	@PostMapping("/{id}")
	public Produit createProduit(@RequestBody DtoProduit produit, @PathVariable("id") long id){
		
		return service.createProduits(mapper.map(produit,Produit.class),id);
		
	}
	@PutMapping("/{id}")
	public Produit updateProduit (@PathVariable("id") long id , @RequestBody Produit Produit){
		return service.updateProduits(id,mapper.map(Produit,Produit.class));  
	 
	}
	@GetMapping("/byCategorie/{id}")
	public List<Produit> getByCategorie(@PathVariable(value = "id") Long idCategorie)throws NoSuchElementException{
	    return repositories.findAll()
	            .stream()
	            .filter(p->p.getIdC().getId()==idCategorie)
	            .collect(Collectors.toList());

	}
	@DeleteMapping("/{id}")
	public String deleteproduit(@PathVariable(value = "id") Long produitId){
	    var p = repositories.findById(produitId);
	     repositories.delete(p.orElseThrow(()->new NoSuchElementException()));
	    return "Produit was deleted !";
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	}
		
		



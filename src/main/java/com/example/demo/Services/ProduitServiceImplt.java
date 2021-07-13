package com.example.demo.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repositories.CategorieRepository;
import com.example.demo.Repositories.ProduitRepository;
import com.example.demo.models.Categorie;
import com.example.demo.models.Produit;



@Service
public class ProduitServiceImplt implements ProduitService {
	
    private ProduitRepository repository;
    private CategorieRepository categorieRepositorie ; 
    
   @Autowired
    public ProduitServiceImplt(ProduitRepository repository ,CategorieRepository categorieRepositorie) {
this.repository = repository;
this.categorieRepositorie = categorieRepositorie ;
	}

	@Override
	public List<Produit> getAllProduit() {
		
		return repository.findAll();
	}

	@Override
	public Produit createProduits(Produit produits, long idCategorie) {
		 produits.setDate_creation(LocalDate.now());
	        Optional<Categorie> categorie =categorieRepositorie.findById(idCategorie);
	        produits.setIdC(categorie.orElseThrow(()-> new NoSuchElementException()));
	      return  repository.save(produits);
		
	}
	
	@Override
	public 	Produit deleteProduits(long id) {
		 var p = findProduitByID(id);
		 repository.deleteById(id);
	        return p;
	       }
	

	@Override
	public Produit updateProduits(long id, Produit produits) {
		var produitDb =findProduitByID(id);
		try {
			if (produits.getDisponible()!=produitDb.getDisponible())
				produitDb.setDisponible(produits.getDisponible());
			 if (produits.getNom()!=produitDb.getNom())
		            produitDb.setNom(produits.getNom());
		        if (produits.getQt()!=produitDb.getQt())
		            produitDb.setQt(produits.getQt());
		        if (produits.getIdC()!=produitDb.getIdC())
		            produitDb.setIdC(produits.getIdC());
		        produitDb.setDate_modif(LocalDate.now());
		        return repository.save(produitDb);
		}finally {
			
		}
			
	}

	@Override
	public Produit findProduitByID(long id) {
	
		 Optional<Produit> produit=repository.findById(id);
	        return produit.orElseThrow(()->new NoSuchElementException());
	    }

	
	
	
}

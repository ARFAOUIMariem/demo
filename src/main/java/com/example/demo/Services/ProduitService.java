package com.example.demo.Services;

import java.util.List;

import com.example.demo.models.Produit;

public interface ProduitService {
	
	List<Produit>  getAllProduit ();
	Produit createProduits(Produit produits, long idCategorie);
    Produit deleteProduits(long id);
    Produit updateProduits(long id,Produit produits);
    Produit findProduitByID(long id);
	
   
}

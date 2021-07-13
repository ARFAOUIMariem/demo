package com.example.demo.Services;



import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.CategorieRepository;
import com.example.demo.Repositories.ProduitRepository;
import com.example.demo.models.Categorie;
import com.example.demo.models.Produit;
@Service
public class CategorieServiceimpl implements CategorieService {
    private ProduitRepository produitrepository;
	private CategorieRepository repository ;
	@Autowired
	public CategorieServiceimpl(CategorieRepository repository,ProduitRepository produitrepository) {
		super();
		this.repository = repository;
		this.produitrepository = produitrepository;
	}
	

	@Override
	public List<Categorie> getAllCategories() {
		
		return repository.findAll();
	}

	@Override
	public Categorie createCategories(Categorie categorie) {
	categorie.setDate_creation(LocalDate.now());
		return repository.save(categorie);
	}

	@Override
	public String deleteCategories(long id) {
		List<Produit> produitsList = produitrepository.findAll();
        for(Produit p : produitsList)
            produitrepository.deleteById(p.getIdProduit());
        try{
            repository.deleteById(id);
        }catch (Exception e){
            return "Categorie Not found";
        }
        return "Categorie deleted";
		
	}
	 public List<Produit> setListProduits(List<Produit> listDb, List<Produit> listBody){
	        for (Produit p: listBody) {
	            if (!listDb.contains(p))
	                listDb.add(p);
	        }
	        return listDb;
	    }

	@Override
	public Categorie updateCategories(long id, Categorie categorie) {
        Categorie categoriesDb = findCategoriesById(id);
        if (categorie.getNom()!=categoriesDb.getNom())
            categoriesDb.setNom(categorie.getNom());
        if (categorie.getQt()!=categoriesDb.getQt())
            categoriesDb.setQt(categorie.getQt());
        if (categorie.getProduit()!=null)
        	categoriesDb.setProduit(setListProduits(categoriesDb.getProduit(),categorie.getProduit()));
        categoriesDb.setDate_modif(LocalDate.now());
        repository.save(categoriesDb);
        return categoriesDb;
		
	}

	@Override
	public Categorie findCategoriesById(long id) {
		if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
		else throw new NoSuchElementException("Categories n'existe pas !");
		
	}
	
	

}

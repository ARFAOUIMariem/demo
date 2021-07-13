package com.example.demo.Services;

import java.util.List;

import com.example.demo.models.Categorie;

public interface CategorieService {
	List<Categorie> getAllCategories();
    Categorie createCategories(Categorie categorie);
    String deleteCategories(long id);
    Categorie updateCategories(long id,Categorie categorie);
    Categorie findCategoriesById(long id);

}

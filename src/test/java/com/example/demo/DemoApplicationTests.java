package com.example.demo;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import javax.persistence.Id;

import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Services.ProduitService;

import com.example.demo.models.Categorie;



@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
	@Autowired 
	private ProduitService produitService;
	@Autowired
	private TestRestTemplate restTemplate;
	private String url="http://localhost:9090/";
	private String c="/categorie/";

	@Test
	 void createCategories() {
	    LocalDate date = LocalDate.now();
	    Categorie categorie = new Categorie(null, "testClass",20,date,date, null);
	    ResponseEntity<Categorie> postResponse = restTemplate.postForEntity(url + c, categorie, Categorie.class);
	    assertNotNull(postResponse);
	    assertNotNull(postResponse.getBody());
	}
	@Test
	void updateCategories(  ) 
	{ int id = 31;
	Categorie categorie = restTemplate.getForObject(url + c + id, Categorie.class);
	categorie.setNom("LG");
	categorie.setQt(9); restTemplate.put(url + c + id, categorie);
	Categorie categories1 = restTemplate.getForObject(url + c + id, Categorie.class); 
	assertNotNull(updateCategories()); 
	}
	

	@Test
	 void getAllCategorie() {
	    HttpHeaders headers = new HttpHeaders();
	    HttpEntity<String> entity = new HttpEntity<>(null, headers);
	    ResponseEntity<String> response = restTemplate.exchange(url + "/categories",
	            HttpMethod.GET, entity, String.class);
	    assertNotNull(response.getBody());
	}

	@Test
	 void deleteCategorie() {
	    int id = 20;
	    Categories categories = restTemplate.getForObject(url + c + id, Categories.class);
	    assertNotNull(categories);
	    restTemplate.delete(url + c+ id);
	}

	@Test
	 void testGetcategorieById() {

	    Categorie categorie = restTemplate.getForObject(url + "/categorie/14", Categorie.class);
	    assertNotNull(categorie);
	}
	@Test
    void createProduits(){
       LocalDate date = LocalDate.now();
       Produit produits = new Produit("testProduit",44,true,date,date);
       var p =produitService.createProduits(produits,10);
       assertNotNull(p);
   }

		 

}

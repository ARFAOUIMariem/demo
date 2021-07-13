package com.example.demo.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column (name ="nom")
    private String nom;
    @Column (name="qt")
    private Integer qt;
 
    private LocalDate date_creation;
   
    private LocalDate date_modif;
    @OneToMany(mappedBy = "idC", cascade = CascadeType.REMOVE)
    @JsonIgnore
    public List<Produit> produit;
	

}

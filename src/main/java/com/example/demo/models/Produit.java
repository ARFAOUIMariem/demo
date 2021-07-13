package com.example.demo.models;

import javax.persistence.*;


import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Entity
@Data @NoArgsConstructor 

public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long idProduit ;
    @Column (name="nom")
    private String nom ;
    @Column (name="qt")
    private int qt ;
    @Column (name="disponible")
    private  Boolean disponible;
    
    private LocalDate date_creation;
  
    private LocalDate date_modif;
   @ManyToOne
    @JoinColumn(name ="id", insertable =false , updatable = true)
    private Categorie idC;




}
  

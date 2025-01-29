package application.service;

import application.bo.Categorie;

import java.util.List;

public interface CategorieService {

    List<Categorie> getAllCategories();

    Categorie findById(int noCategorie);
}

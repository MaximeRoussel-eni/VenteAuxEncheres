package application.dal;

import application.bo.Categorie;

import java.util.List;

public interface CategorieDao {
    public Categorie getCategorieById(int id);
    public List<Categorie> getAllCategories();
}

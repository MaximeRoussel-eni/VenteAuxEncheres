package application.dal;

import application.bo.ArticleVendu;
import application.bo.Enchere;
import application.bo.Utilisateur;

import java.util.List;

public interface ArticleVenduDao {

   void create(ArticleVendu articleVendu, int noRetrait, int noCategorie);

   void update(ArticleVendu articleVendu);

   void delete(int noArticle);

   ArticleVendu read(int noArticle);

   List<ArticleVendu> readAll();

   List<ArticleVendu> readByNoCategorieAndNomArticle( String nomArticle, int noCategorie);

   List<ArticleVendu> readByNoCategorie(int noCategorie);

   List<ArticleVendu> readByNomArticle(String nomArticle);

}

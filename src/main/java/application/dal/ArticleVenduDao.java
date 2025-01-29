package application.dal;

import application.bo.ArticleVendu;
import application.bo.Enchere;
import application.bo.Utilisateur;

import java.util.List;

public interface ArticleVenduDao {

   void create(ArticleVendu articleVendu);

   void update(ArticleVendu articleVendu);

   void delete(int noArticle);

   ArticleVendu read(int noArticle);

   List<ArticleVendu> readAll();

}

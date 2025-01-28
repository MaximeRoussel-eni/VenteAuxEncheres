package dal;

import bo.ArticleVendu;
import bo.Utilisateur;

import java.util.List;

public interface ArticleVenduDao {

   void create(ArticleVendu articleVendu);

   void update(ArticleVendu articleVendu);

   void delete(int noArticle);

   ArticleVendu read(int noArticle);

   List<ArticleVendu> readAll();

}

package service;

import bo.ArticleVendu;
import bo.Utilisateur;

import java.util.List;

public interface ArticleVenduService {

        void addArticleVendu(ArticleVendu articleVendu);

        void removeArticleVendu(int noArticle);

        List<ArticleVendu> getAllArticleVendu();

        ArticleVendu getArticleVendu(int noArticle);

        void updateArticleVendu(ArticleVendu articleVendu);


}

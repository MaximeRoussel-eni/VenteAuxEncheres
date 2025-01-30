package application.service;

import application.bo.ArticleVendu;
import application.bo.Utilisateur;

import java.util.List;

public interface ArticleVenduService {

        void addArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateurEnSession);

        void removeArticleVendu(int noArticle);

        List<ArticleVendu> getAllArticleVendu();

        ArticleVendu getArticleVendu(int noArticle);

        void updateArticleVendu(ArticleVendu articleVendu);


}

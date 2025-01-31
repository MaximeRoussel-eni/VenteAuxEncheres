package application.service;

import application.bo.ArticleVendu;
import application.bo.Retrait;
import application.bo.Utilisateur;
import application.dal.RetraitDao;

import java.util.List;

public interface ArticleVenduService {

        void addArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateurEnSession, Retrait retrait);

        void removeArticleVendu(int noArticle);

        List<ArticleVendu> getAllArticleVendu();

        ArticleVendu getArticleVendu(int noArticle);

        void updateArticleVendu(ArticleVendu articleVendu);


}

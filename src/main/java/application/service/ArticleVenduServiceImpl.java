package application.service;

import application.bo.ArticleVendu;
import application.bo.Categorie;
import application.bo.Retrait;
import application.bo.Utilisateur;
import application.dal.ArticleVenduDao;
import application.dal.CategorieDao;
import application.dal.CategorieDao;
import application.dal.RetraitDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private UtilisateurService utilisateurService;
    private RetraitDao retraitDao;
    private ArticleVenduDao articleVenduDao;
    private CategorieDao categorieDao;

    public ArticleVenduServiceImpl(ArticleVenduDao articleVenduDao, UtilisateurService utilisateurService, RetraitDao retraitDao, CategorieDao categorieDao) {
        this.articleVenduDao = articleVenduDao;
        this.utilisateurService = utilisateurService;
        this.categorieDao = categorieDao;
        this.retraitDao = retraitDao;
    }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateurEnSession, Retrait retrait , int noCategorie) {
        articleVendu.setUtilisateurVendeur(utilisateurEnSession);
        int noRetrait = retraitDao.createRetrait(retrait);
        System.out.println(noCategorie);
        articleVenduDao.create(articleVendu,noCategorie,noRetrait);
    }


    @Override
    public void removeArticleVendu(int noArticle) {
        articleVenduDao.delete(noArticle);
    }

    @Override
    public List<ArticleVendu> getAllArticleVendu() {
        return articleVenduDao.readAll();
    }

    @Override
    public ArticleVendu getArticleVendu(int noArticle) {
        var article = articleVenduDao.read(noArticle);
        System.out.println(article);
        return article;
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        articleVenduDao.update(articleVendu);
    }
}

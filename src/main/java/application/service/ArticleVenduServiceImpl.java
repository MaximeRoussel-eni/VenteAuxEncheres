package application.service;

import application.bo.*;
import application.dal.ArticleVenduDao;
import application.dal.CategorieDao;
import application.dal.CategorieDao;
import application.dal.RetraitDao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (article.getDateFinEncheres().isBefore(LocalDate.now())) {
            article.setEtatVente(EtatVente.TERMINE);
            return article;
        }

        if (article.getDateDebutEncheres().isAfter(LocalDate.now())) {
            article.setEtatVente(EtatVente.NON_COMMENCE);
            return article;
        }
        article.setEtatVente(EtatVente.EN_COURS);
        return article;
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        retraitDao.updateRetrait(articleVendu.getRetrait());
        articleVenduDao.update(articleVendu);
    }

    @Override
    public List<ArticleVendu> getArticlesFiltres(String nomArticle, Integer noCategorie) {
        if ((nomArticle == null || nomArticle.isEmpty()) && noCategorie == null) {
            // Aucun filtre
            return articleVenduDao.readAll();
        } else if (nomArticle != null && !nomArticle.isEmpty() && noCategorie != null) {
            // Filtre par nom + catégorie
            return articleVenduDao.readByNoCategorieAndNomArticle(nomArticle, noCategorie);
        }else if (nomArticle != null && !nomArticle.isEmpty()) {
            // Filtre par nom uniquement
            return articleVenduDao.readByNomArticle(nomArticle);
        } else {
            // Filtre par catégorie uniquement
            return articleVenduDao.readByNoCategorie(noCategorie);
        }

    }
}

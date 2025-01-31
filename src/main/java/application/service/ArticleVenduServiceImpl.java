package application.service;

import application.bo.ArticleVendu;
import application.bo.Retrait;
import application.bo.Utilisateur;
import application.dal.ArticleVenduDao;
import application.dal.RetraitDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private UtilisateurService utilisateurService;
    private RetraitDao retraitDao;
    private ArticleVenduDao articleVenduDao;

    public ArticleVenduServiceImpl(ArticleVenduDao articleVenduDao, UtilisateurService utilisateurService, RetraitDao retraitDao){
        this.articleVenduDao = articleVenduDao;
        this.utilisateurService = utilisateurService;
        this.retraitDao = retraitDao;
    }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateurEnSession, Retrait retrait) {
        articleVendu.setUtilisateurVendeur(utilisateurEnSession);
        int noRetrait = retraitDao.createRetrait(retrait);
        articleVenduDao.create(articleVendu,noRetrait);
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
        return articleVenduDao.read(noArticle);
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        articleVenduDao.update(articleVendu);
    }
}

package application.service;

import application.bo.ArticleVendu;
import application.bo.Utilisateur;
import application.dal.ArticleVenduDao;
import application.dal.CategorieDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private final UtilisateurService utilisateurService;
    private ArticleVenduDao articleVenduDao;
    private CategorieDao categorieDao;

    public ArticleVenduServiceImpl(ArticleVenduDao articleVenduDao, UtilisateurService utilisateurService, CategorieDao categorieDao) {
        this.articleVenduDao = articleVenduDao;
        this.utilisateurService = utilisateurService;
        this.categorieDao = categorieDao;
    }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateurEnSession) {
        articleVendu.setUtilisateurVendeur(utilisateurEnSession);
        System.out.println(articleVendu.getUtilisateurVendeur().getNoUtilisateur());
        articleVenduDao.create(articleVendu);
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

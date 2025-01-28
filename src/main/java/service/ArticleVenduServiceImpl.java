package service;

import bo.ArticleVendu;
import dal.ArticleVenduDao;

import java.util.List;

public class ArticleVenduServiceImpl implements ArticleVenduService {

    private ArticleVenduDao articleVenduDao;

    public ArticleVenduServiceImpl( ArticleVenduDao articleVendudao){
        this.articleVenduDao = articleVendudao;
    }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu) {
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
        return articleVenduDao.read(noArticle);
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        articleVenduDao.update(articleVendu);

    }
}

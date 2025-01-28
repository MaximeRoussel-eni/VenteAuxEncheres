package application.service;

import application.bo.ArticleVendu;
import application.dal.ArticleVenduDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private ArticleVenduDao articleVenduDao;

    public ArticleVenduServiceImpl(ArticleVenduDao articleVenduDao){ this.articleVenduDao = articleVenduDao; }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu) { articleVenduDao.create(articleVendu);

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

package service;

import bo.ArticleVendu;

import java.util.List;

public class ArticleVenduServiceImpl implements ArticleVenduService {

    private DaoArticleVendu daoArticleVendu;

    public ArticleVenduServiceImpl(DaoArticleVendu articleVendu){ this.daoArticleVendu = articleVendu; }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu) { daoArticleVendu.create(articleVendu);

    }

    @Override
    public void removeArticleVendu(int noArticle) { daoArticleVendu.delete(noArticle);

    }

    @Override
    public List<ArticleVendu> getAllArticleVendu() {
        return daoArticleVendu.read();
    }

    @Override
    public ArticleVendu getArticleVendu(int noArticle) {
        return daoArticleVendu.read(noArticle);
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        daoArticleVendu.update(articleVendu);

    }
}

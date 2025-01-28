package service;

import bo.ArticleVendu;

import java.util.List;

public class ArticleVenduServiceImpl implements ArticleVenduService {

    private ArticleVenduDao articleVenduDaorticleVendu;

    public ArticleVenduServiceImpl(DaoArticleVendu articleVendudao){ this.ArticleVendudao = articleVendudao; }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu) { articleVenduDao.create(articleVendu);

    }

    @Override
    public void removeArticleVendu(int noArticle) { articleVenduDao.delete(noArticle);

    }

    @Override
    public List<ArticleVendu> getAllArticleVendu() {
        return articleVendudao.read();
    }

    @Override
    public ArticleVendu getArticleVendu(int noArticle) {
        return articleVendudao.read(noArticle);
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {
        articleVendudao.update(articleVendu);

    }
}

package service;

import bo.ArticleVendu;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private ArticleVenduDao articleVenduDao;

    public ArticleVenduServiceImpl(ArticleVenduDao articleVenduDao){ this.ArticleVenduDao = articleVenduDao; }

    @Override
    public void addArticleVendu(ArticleVendu articleVendu) { articleVenduDao.create(articleVendu);

    }

    @Override
    public void removeArticleVendu(int noArticle) { articleVenduDao.delete(noArticle);

    }

    @Override
    public List<ArticleVendu> getAllArticleVendu() {
        return articleVenduDao.read();
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

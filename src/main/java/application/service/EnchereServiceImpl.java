package application.service;

import application.bo.Enchere;
import application.dal.EnchereDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {


    private final ArticleVenduService articleVenduService;
    private EnchereDao enchereDao;

    EnchereServiceImpl(EnchereDao enchereDao, ArticleVenduService articleVenduService) {
        this.enchereDao = enchereDao;
        this.articleVenduService = articleVenduService;
    }

    @Override
    public void addEnchere(Enchere enchere) {




        if (enchereDao.read(enchere.getUtilisateur().getNoUtilisateur(),enchere.getArticleVendu().getNoArticle())!=null){
            enchereDao.update(enchere);
        }
        else {
            enchereDao.create(enchere);
        }
        var article = articleVenduService.getArticleVendu(enchere.getArticleVendu().getNoArticle());
        article.setPrixVente(enchere.getMontantEnchere());
        articleVenduService.updateArticleVendu(article);
    }

    @Override
    public void updateEnchere(Enchere enchere) {
        enchereDao.update(enchere);

    }

    @Override
    public void removeEnchere(int noUtilisateur, int noArticle) {
        enchereDao.delete(noUtilisateur, noArticle);
    }

    @Override
    public Enchere getMaxEnchereByArticleVendu(int noArticle) {
        return enchereDao.readMaxByArticle(noArticle);
    }

    @Override
    public Enchere getEnchere(int noUtilisateur, int noArticle) {
        return enchereDao.read(noUtilisateur, noArticle);
    }

    @Override
    public List<Enchere> getAllByUtilisateur(int noUtilisateur) {
        return enchereDao.readAllByUtilisateur(noUtilisateur);
    }

    @Override
    public List<Enchere> getAllByArticleVendu(int noArticleVendu) {
        return enchereDao.readAllByArticleVendu(noArticleVendu);
    }
}

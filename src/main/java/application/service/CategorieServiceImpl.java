package application.service;

import application.bo.Categorie;
import application.dal.CategorieDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService{
    private CategorieDao categorieDao;

    CategorieServiceImpl(CategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieDao.getAllCategories();
    }

    @Override
    public Categorie findById(int noCategorie) {
        return categorieDao.getCategorieById(noCategorie);
    }
}

package application.service;

import application.bo.Enchere;

import java.util.List;

public interface EnchereService {
    void addEnchere(Enchere enchere);

    void updateEnchere(Enchere enchere);

    void removeEnchere(int noUtilisateur, int noArticle);

    Enchere getEnchere(int noUtilisateur, int noArticle);

    List<Enchere> getAllByUtilisateur(int noUtilisateur);

    List<Enchere> getAllByArticleVendu(int noArticleVendu);
}

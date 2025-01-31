package application.dal;


import application.bo.Enchere;

import java.util.List;

public interface EnchereDao {

    void createEnchere(Enchere enchere);

    void update(Enchere enchere);

    void delete(int noUtilisateur, int noArticle);

    Enchere read(int noUtilisateur, int noArticle);

    List<Enchere> readAllByUtilisateur(int noUtilisateur);

    List<Enchere> readAllByArticleVendu(int noArticleVendu);
}

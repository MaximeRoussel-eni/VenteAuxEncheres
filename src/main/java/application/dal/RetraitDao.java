package application.dal;

import application.bo.Retrait;

public interface RetraitDao {

    void createRetrait(String noArticle, Retrait retrait);

}

package application.dal;

import application.bo.Retrait;

import java.util.List;

public interface RetraitDao {

    int createRetrait(Retrait retrait);

    void updateRetrait(Retrait retrait);

    void deleteRetrait(int noRetrait);

    Retrait read (int noRetrait);

    List<Retrait> readAllRetrait();
}

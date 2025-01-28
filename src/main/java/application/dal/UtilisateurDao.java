package application.dal;

import application.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDao {

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    void delete(int noUtilisateur);

    void deleteByPseudo(Utilisateur utilisateur);

    Utilisateur read(int id);

    List<Utilisateur> readAll();


}

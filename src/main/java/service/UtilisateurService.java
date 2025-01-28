package service;

import bo.Utilisateur;

public interface UtilisateurService {

    void addUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateur(int noUtilisateur);

    void updateUtilisateur(Utilisateur utilisateur);

    void deleteUtilisateur(int noUtilisateur);

}

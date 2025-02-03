package application.service;

import application.bo.Utilisateur;

public interface UtilisateurService {

    void addUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateur(int noUtilisateur);

    Utilisateur getUtilisateurByPseudo(String pseudo);

    void updateUtilisateur(Utilisateur utilisateur);

    void deleteUtilisateur(int noUtilisateur);



}

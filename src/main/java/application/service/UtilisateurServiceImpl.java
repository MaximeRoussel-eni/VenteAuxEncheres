package application.service;

import application.bo.Utilisateur;
import application.dal.UtilisateurDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {


    private UtilisateurDao utilisateurDao ;
    private PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao, PasswordEncoder passwordEncoder) {
        this.utilisateurDao = utilisateurDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurDao.create(utilisateur);
        System.out.println(utilisateur);
    }

    @Override
    public void deleteUtilisateur( int noUtilisateur) {
        utilisateurDao.delete(noUtilisateur);
    }

    @Override
    public Utilisateur getUtilisateur(int noUtilisateur) {
        return utilisateurDao.read(noUtilisateur);
    }

    @Override
    public Utilisateur getUtilisateurByPseudo(String pseudo) {
        return utilisateurDao.readByPseudo(pseudo);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.update(utilisateur);
    }

}

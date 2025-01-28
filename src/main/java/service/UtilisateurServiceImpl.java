package service;

import bo.Utilisateur;
import dal.UtilisateurDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDao utilisateurDAo ;

    public UtilisateurServiceImpl(UtilisateurDao daoUtilisateur) {
        this.utilisateurDAo = utilisateurDAo;
    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurDAo.create(utilisateur);
    }

    @Override
    public void deleteUtilisateur(int noUtilisateur) {
        utilisateurDAo.delete(noUtilisateur);
    }

    @Override
    public Utilisateur getUtilisateur(int noUtilisateur) {
        return utilisateurDAo.read(noUtilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDAo.update(utilisateur);
    }
}

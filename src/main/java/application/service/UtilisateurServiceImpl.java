package application.service;

import application.bo.Utilisateur;
import application.dal.UtilisateurDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDao utilisateurDao ;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDAo) { this.utilisateurDao = utilisateurDao; }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) { utilisateurDao.create(utilisateur);

    }

    @Override
    public void deleteUtilisateur( int noUtilisateur) { utilisateurDao.delete(noUtilisateur);

    }

    @Override
    public Utilisateur getUtilisateur(int noUtilisateur) {
        return utilisateurDao.read(noUtilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.update(utilisateur);
    }
}

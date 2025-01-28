package service;

import bo.Utilisateur;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {

    private DaoUtilisateur daoUtilisateur ;

    public UtilisateurServiceImpl(DaoUtilisateur daoUtilisateur) { this.daoUtilisateur = daoUtilisateur; }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) { daoUtilisateur.create(Utilisateur);

    }

    @Override
    public void deleteUtilisateur( int noUtilisateur) { daoUtilisateur.delete();

    }

    @Override
    public Utilisateur getUtilisateur(int noUtilisateur) {
        return daoUtilisateur.read(noUtilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        daoUtilisateur.update(utilisateur);
    }
}

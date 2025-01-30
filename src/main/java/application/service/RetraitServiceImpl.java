package application.service;

import application.bo.Retrait;
import application.dal.RetraitDao;
import org.springframework.stereotype.Service;

@Service
public class RetraitServiceImpl implements RetraitService {

    private RetraitDao retraitDao;

    @Override
    public Retrait getRetrait(int noUtilisateur) {
        return null;
    }
}

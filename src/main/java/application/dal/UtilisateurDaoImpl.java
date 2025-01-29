package application.dal;

import application.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_UTILISATEUR ="INSERT INTO UTILISATEUR (pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse)";
    private final String UPDATE_UTILISATEUR = "UPDATE UTILISATEUR SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email= :email, " +
            "telephone=:telephone, rue=:rue, codePostal=:codePostal, ville=:ville, motDePasse= :motDePasse WHERE noUtilisateur = :noUtilisateur";
    private final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEUR WHERE pseudo = :pseudo";
    private final String READ_UTILISATEUR_BY_NOUTILISATEUR = "SELECT * FROM UTILISATEUR WHERE noUtilisateur = :noUtilisateur";
    private final String READ_ALL_UTILISATEURS = "SELECT * FROM UTILISATEUR";


    @Override
    public void create(Utilisateur utilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("codePostal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("motDePasse", utilisateur.getMotDePasse());
        namedParameterJdbcTemplate.update(INSERT_UTILISATEUR, namedParameters);
    }

    @Override
    public void update(Utilisateur utilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("codePostal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("motDePasse", utilisateur.getMotDePasse());
        namedParameterJdbcTemplate.update(UPDATE_UTILISATEUR, namedParameters);
    }

    @Override
    public void delete(int noUtilisateur) {
        jdbcTemplate.update(DELETE_UTILISATEUR, noUtilisateur);

    }

    @Override
    public void deleteByPseudo(Utilisateur utilisateur) {
        jdbcTemplate.update(DELETE_UTILISATEUR, utilisateur.getPseudo());

    }

    @Override
    public Utilisateur read(int noUtilisateur) {
        return jdbcTemplate.queryForObject(READ_UTILISATEUR_BY_NOUTILISATEUR, BeanPropertyRowMapper.newInstance(Utilisateur.class), noUtilisateur);
    }

    @Override
    public List<Utilisateur> readAll() {
        return jdbcTemplate.query(READ_ALL_UTILISATEURS, BeanPropertyRowMapper.newInstance(Utilisateur.class));
    }




}

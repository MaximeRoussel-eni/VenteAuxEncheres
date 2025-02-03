package application.dal;

import application.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final String INSERT_UTILISATEUR ="INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, enabled) " +
            "VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe,:credit, :administrateur, :enabled)";

    private final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email= :email, " +
            "telephone=:telephone, rue=:rue, code_postal=:code_postal, ville=:ville, motDePasse= :motDePasse WHERE no_utilisateur = :no_utilisateur";

    private final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE pseudo = :pseudo";

    private final String READ_UTILISATEUR_BY_NOUTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = :no_utilisateur";

    private final String READ_ALL_UTILISATEURS = "SELECT * FROM UTILISATEURS";

    private final String READ_UTILISATEUR_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = :pseudo";




    @Override
    public void create(Utilisateur utilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("code_postal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("mot_de_passe", utilisateur.getMotDePasse());
        namedParameters.addValue("administrateur", false);
        namedParameters.addValue("credit", 0);
        namedParameters.addValue("enabled", true);
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
        namedParameters.addValue("code_postal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("mot_de_passe", utilisateur.getMotDePasse());
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
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", noUtilisateur);
        return namedParameterJdbcTemplate.queryForObject(READ_UTILISATEUR_BY_NOUTILISATEUR, namedParameters, new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public Utilisateur readByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        return namedParameterJdbcTemplate.queryForObject(READ_UTILISATEUR_BY_PSEUDO, namedParameters, new BeanPropertyRowMapper<>(Utilisateur.class));
    }


    @Override
    public List<Utilisateur> readAll() {
        return jdbcTemplate.query(READ_ALL_UTILISATEURS, BeanPropertyRowMapper.newInstance(Utilisateur.class));
    }




}

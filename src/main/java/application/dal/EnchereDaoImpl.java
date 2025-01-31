package application.dal;

import application.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDaoImpl implements EnchereDao {

    private final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)" +
            "VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";

    private final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere=:date_enchere,montant_enchere=:montant_enchere " +
            "WHERE no_utilisateur = :no_utilisateur AND no_article =:no_article";

    private final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_utilisateur = :no_utilisateur and no_article = :no_article";

    private final String READ_ENCHERE_BY_NO = "SELECT * FROM ENCHERES WHERE no_utilisateur = :no_utilisateur and no_article = :no_article";

    private final String READ_ALL_ENCHERE_BY_UTILISATEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur = :no_utilisateur";

    private final String READ_ALL_ENCHERE_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = :no_article";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void createEnchere(Enchere enchere) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", enchere.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("no_article", enchere.getArticleVendu().getNoArticle());
        namedParameters.addValue("date_enchere", enchere.getDateEnchere());
        namedParameters.addValue("montant_enchere", enchere.getMontantEnchere());
        namedParameterJdbcTemplate.update(INSERT_ENCHERE, namedParameters);
    }

    @Override
    public void update(Enchere enchere) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", enchere.getUtilisateur().getNoUtilisateur());
        namedParameters.addValue("no_article", enchere.getArticleVendu().getNoArticle());
        namedParameters.addValue("date_enchere", enchere.getDateEnchere());
        namedParameters.addValue("montant_enchere", enchere.getMontantEnchere());
        namedParameterJdbcTemplate.update(UPDATE_ENCHERE, namedParameters);
    }

    @Override
    public void delete(int noUtilisateur, int noArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", noUtilisateur);
        namedParameters.addValue("no_article", noArticle);
        namedParameterJdbcTemplate.update(DELETE_ENCHERE, namedParameters);
    }

    @Override
    public Enchere read(int noUtilisateur, int noArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", noUtilisateur);
        namedParameters.addValue("no_article", noArticle);
        return namedParameterJdbcTemplate.queryForObject(READ_ENCHERE_BY_NO, namedParameters, BeanPropertyRowMapper.newInstance(Enchere.class));
    }

    @Override
    public List<Enchere> readAllByUtilisateur(int noUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", noUtilisateur);
        return namedParameterJdbcTemplate.query(READ_ALL_ENCHERE_BY_UTILISATEUR,namedParameters, new BeanPropertyRowMapper<>(Enchere.class));
    }

    @Override
    public List<Enchere> readAllByArticleVendu(int noArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        return namedParameterJdbcTemplate.query(READ_ALL_ENCHERE_BY_ARTICLE,namedParameters, new BeanPropertyRowMapper<>(Enchere.class));
    }
}

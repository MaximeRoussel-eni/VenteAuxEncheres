package application.dal;

import application.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnchereDaoImpl {

    private final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)" +
            "VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;




    /*@Override
    public void createEnchere(String noArticle, Enchere enchere) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur",
        namedParameters.addValue("no_article", noArticle;
        namedParameters.addValue("date_enchere", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("montant_enchere", articleVendu.getMiseAPrix());
        namedParameterJdbcTemplate.update(INSERT_ENCHERE, namedParameters);
    };*/

}

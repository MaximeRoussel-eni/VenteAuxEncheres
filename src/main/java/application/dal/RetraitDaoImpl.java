package application.dal;

import application.bo.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RetraitDaoImpl implements RetraitDao {

    private final String INSERT_RETRAIT ="INSERT INTO RETRAIT (no_article, rue, code_postal, ville) " +
            "VALUES (:no_article, :rue, :code_postal, :ville)";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createRetrait(String noArticle, Retrait retrait) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        namedParameters.addValue("rue", retrait.getRue());
        namedParameters.addValue("code_postal", retrait.getCodePostal());
        namedParameters.addValue("ville", retrait.getVille());
    }
}

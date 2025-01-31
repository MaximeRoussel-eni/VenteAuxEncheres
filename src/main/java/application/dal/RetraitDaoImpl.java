package application.dal;

import application.bo.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RetraitDaoImpl implements RetraitDao {

    private final String INSERT_RETRAIT ="INSERT INTO RETRAITS (rue, code_postal, ville) " +
            "VALUES (:rue, :code_postal, :ville)";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createRetrait(Retrait retrait) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("rue", retrait.getRue());
        namedParameters.addValue("code_postal", retrait.getCodePostal());
        namedParameters.addValue("ville", retrait.getVille());
        var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_RETRAIT, namedParameters, keyHolder);
        return keyHolder.getKey().intValue();
    }
}

package application.dal;

import application.bo.ArticleVendu;
import application.bo.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RetraitDaoImpl implements RetraitDao {

    private final String INSERT_RETRAIT ="INSERT INTO RETRAITS (rue, code_postal, ville) " +
            "VALUES (:rue, :code_postal, :ville)";
    private final String UPDATE_RETRAIT = " UPDATE RETRAITS SET rue=:rue, code_postal=:code_postal, ville=:ville WHERE no_retrait=:no_retrait";
    private final String DELETE_RETRAIT = "DELETE FROM RETRAIT WHERE no_article = :no_article";
    private final String READ_RETRAIT_BY_NO="SELECT * FROM RETRAITS WHERE no_retrait = :no_retrait";
    private final String READ_ALL_RETRAITS="SELECT * FROM RETRAITS";

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
        System.out.println(retrait);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateRetrait(Retrait retrait) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_retrait", retrait.getNoRetrait());
        namedParameters.addValue("rue", retrait.getRue());
        namedParameters.addValue("code_postal", retrait.getCodePostal());
        namedParameters.addValue("ville", retrait.getVille());
        namedParameterJdbcTemplate.update(UPDATE_RETRAIT, namedParameters);
    }

    @Override
    public void deleteRetrait(int noRetrait) {
        jdbcTemplate.update(DELETE_RETRAIT, noRetrait);
    }

    @Override
    public Retrait read(int noRetrait) {
        return jdbcTemplate.queryForObject(READ_RETRAIT_BY_NO, BeanPropertyRowMapper.newInstance(Retrait.class), noRetrait);
    }

    @Override
    public List<Retrait> readAllRetrait() {
        return  jdbcTemplate.query(READ_ALL_RETRAITS, BeanPropertyRowMapper.newInstance(Retrait.class));
    }

}

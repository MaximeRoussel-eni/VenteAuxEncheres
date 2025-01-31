package application.dal;

import application.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategorieDaoImpl implements CategorieDao {

    private final String READ_CATEGORY_BY_NO_CATEGORIE = "SELECT * FROM CATEGORIES WHERE NO_CATEGORIE = :noCategorie";
    private final String READ_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Categorie getCategorieById(int noCategorie) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noCategorie", noCategorie);
        return namedParameterJdbcTemplate.queryForObject(READ_CATEGORY_BY_NO_CATEGORIE,namedParameters, new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public List<Categorie> getAllCategories() {
        return  namedParameterJdbcTemplate.query(READ_ALL_CATEGORIES, BeanPropertyRowMapper.newInstance(Categorie.class));
    }
}

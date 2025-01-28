package dal;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleVenduDaoImpl implements ArticleVenduDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final String INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_categorie) " +
            "VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_categorie)";
    private final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = :nom_article, description =:description, " +
            "date_debut_encheres=:date_debut_encheres, date_fin_encheres=:date_fin_encheres, prix_initial=:prix_initial WHERE no_article = :no_article";
    private final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = :no_article";
    private final String READ_ARTICLE_BY_NOARTICLE = "SELECT * FROM ARTICLES WHERE no_article = :no_article";
    private final String READ_ALL_ARTICLES ="SELECT * FROM ARTICLES";


    @Override
    public void create(ArticleVendu articleVendu) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("prix_initial", articleVendu.getMiseAPrix());
        namedParameters.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
        namedParameterJdbcTemplate.update(INSERT_ARTICLE, namedParameters);
    }

    @Override
    public void update(ArticleVendu articleVendu) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("prix_initial", articleVendu.getMiseAPrix());
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE, namedParameters);
    }

    @Override
    public void delete(int noArticle) {
        jdbcTemplate.update(DELETE_ARTICLE, noArticle);

    }

    @Override
    public ArticleVendu read(int noArticle) {
        return jdbcTemplate.queryForObject(READ_ARTICLE_BY_NOARTICLE, BeanPropertyRowMapper.newInstance(ArticleVendu.class), noArticle);
    }

    @Override
    public List<ArticleVendu> readAll() {
        return  jdbcTemplate.query(READ_ALL_ARTICLES, BeanPropertyRowMapper.newInstance(ArticleVendu.class));
    }
}

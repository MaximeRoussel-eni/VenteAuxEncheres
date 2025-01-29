package application.dal;

import application.bo.*;
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


    private final String INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) " +
            "VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial,:prix_vente, :no_utilisateur, :no_categorie)";
    private final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = :nom_article, description =:description, " +
            "date_debut_encheres=:date_debut_encheres, date_fin_encheres=:date_fin_encheres, prix_initial=:prix_initial, no_categorie=:no_categorie WHERE no_article = :no_article";
    private final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = :no_article";
    private final String READ_ARTICLE_BY_NOARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = :no_article";
    private final String READ_ALL_ARTICLES ="SELECT * FROM ARTICLES_VENDUS";

    private final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)" +
            "VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";



    @Override
    public void create(ArticleVendu articleVendu) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("prix_initial", articleVendu.getMiseAPrix());
        namedParameters.addValue("prix_vente", articleVendu.getMiseAPrix());
        namedParameters.addValue("no_utilisateur",articleVendu.getUtilisateurVendeur().getNoUtilisateur());
        namedParameters.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
        namedParameterJdbcTemplate.update(INSERT_ARTICLE, namedParameters);
        MapSqlParameterSource namedParameters1 = new MapSqlParameterSource();
        namedParameters1.addValue("no_utilisateur", articleVendu.getUtilisateurVendeur().getNoUtilisateur());
        namedParameters1.addValue("no_article", articleVendu.getNoArticle());
        namedParameters1.addValue("date_enchere", articleVendu.getDateDebutEncheres());
        namedParameters1.addValue("montant_enchere", articleVendu.getMiseAPrix());
        namedParameterJdbcTemplate.update(INSERT_ENCHERE, namedParameters1);
    }

    @Override
    public void update(ArticleVendu articleVendu) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("prix_initial", articleVendu.getMiseAPrix());
        namedParameters.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
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

package application.dal;

import application.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class ArticleVenduDaoImpl implements ArticleVenduDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final String INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_retrait, no_utilisateur) " +
            "VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial,:prix_vente, :no_categorie, :no_retrait, :no_utilisateur)";
    private final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = :nom_article, description =:description, " +
            "date_debut_encheres=:date_debut_encheres, date_fin_encheres=:date_fin_encheres, prix_initial=:prix_initial, no_categorie=:no_categorie WHERE no_article = :no_article";
    private final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = :no_article";
    private final String READ_ARTICLE_BY_NOARTICLE = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN RETRAITS r ON r.no_retrait = a.no_retrait INNER JOIN CATEGORIES c " +
            "ON c.no_categorie = a.no_categorie WHERE no_article = :no_article";
    private final String READ_ALL_ARTICLES ="SELECT * FROM ARTICLES_VENDUS";



    private final String INSERT_RETRAIT = "INSERT INTO RETRAIT (no_article, rue, code_postal, ville)" +
            "VALUES (:no_article, :rue, :code_postal, :ville)";
    private final String DELETE_RETRAIT = "DELETE FROM RETRAIT WHERE no_article = :no_article";


    private RowMapper<ArticleVendu> articleRowMapper = (ResultSet rs, int rowNum) -> {
        int noArticle = rs.getInt("no_article");
        String nomArticle = rs.getString("nom_article");
        String description = rs.getString("description");
        LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
        LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
        int prixInitial = rs.getInt("prix_initial");
        int prixVente = rs.getInt("prix_vente");

        // Récupérer les informations de la catégorie
        int noCategorie = rs.getInt("no_categorie");
        String libelle = rs.getString("libelle");

        // Récupérer les informations du retrait
        int noRetrait = rs.getInt("no_retrait");
        String rue = rs.getString("rue");
        String codePostal = rs.getString("code_postal");
        String ville = rs.getString("ville");

        // Récupérer les informations de la catégorie

        //Créer l'objet Catégorie
        Categorie categorie = new Categorie(noCategorie, libelle);

        // Créer l'objet Retrait
        Retrait retrait = new Retrait(noRetrait, rue, codePostal, ville);

        // Créer l'objet Article avec la catégorie et le retrait
        return new ArticleVendu(noArticle,nomArticle, description,dateDebutEncheres,dateFinEncheres, prixInitial, prixVente, null,categorie, retrait,null,null,null);
    };


    @Override
    public void create(ArticleVendu articleVendu, int noCategorie, int noRetrait) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("prix_initial", articleVendu.getPrixInitial());
        namedParameters.addValue("prix_vente", articleVendu.getPrixVente());
        namedParameters.addValue("no_utilisateur", articleVendu.getUtilisateurVendeur().getNoUtilisateur());
        namedParameters.addValue("no_categorie",noCategorie);
        namedParameters.addValue("no_retrait",noRetrait);
        namedParameterJdbcTemplate.update(INSERT_ARTICLE, namedParameters);

    }

    @Override
    public void update(ArticleVendu articleVendu) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom_article", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("prix_initial", articleVendu.getPrixInitial());
        namedParameters.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE, namedParameters);

    }

    @Override
    public void delete(int noArticle) {
        jdbcTemplate.update(DELETE_RETRAIT, noArticle);
        jdbcTemplate.update(DELETE_ARTICLE, noArticle);
    }

    @Override
    public ArticleVendu read(int noArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        return namedParameterJdbcTemplate.queryForObject(READ_ARTICLE_BY_NOARTICLE, namedParameters, articleRowMapper);
    }


    @Override
    public List<ArticleVendu> readAll() {
        return  jdbcTemplate.query(READ_ALL_ARTICLES, BeanPropertyRowMapper.newInstance(ArticleVendu.class));
    }
}

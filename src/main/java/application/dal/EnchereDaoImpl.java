package application.dal;

import application.bo.ArticleVendu;
import application.bo.Enchere;
import application.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EnchereDaoImpl implements EnchereDao {

    private final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)" +
            "VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";

    private final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere=:date_enchere,montant_enchere=:montant_enchere " +
            "WHERE no_utilisateur = :no_utilisateur AND no_article =:no_article";

    private final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_utilisateur = :no_utilisateur and no_article = :no_article";

    private final String READ_ENCHERE_BY_NO = "SELECT * FROM ENCHERES WHERE no_utilisateur = :no_utilisateur and no_article = :no_article";

    private final String READ_MAX_BY_ARTICLE = "SELECT TOP 1 * FROM ENCHERES as e INNER JOIN UTILISATEURS as u " +
            "on e.no_utilisateur = u.no_utilisateur WHERE no_article = :no_article ORDER BY montant_enchere DESC";

    private final String READ_ALL_ENCHERE_BY_UTILISATEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur = :no_utilisateur";

    private final String READ_ALL_ENCHERE_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = :no_article";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Enchere> enchereRowMapper = (ResultSet rs, int rowNum) -> {
        int noUtilisateur = rs.getInt("no_utilisateur");
        String pseudo = rs.getString("pseudo");
        LocalDate date = rs.getDate("date_enchere").toLocalDate();
        int montant_enchere = rs.getInt("montant_enchere");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNoUtilisateur(noUtilisateur);
        utilisateur.setPseudo(pseudo);

        System.out.println("enchere" + new Enchere(utilisateur,null,date,montant_enchere));
        return new Enchere(utilisateur,null,date,montant_enchere);
    };


    @Override
    public void create(Enchere enchere) {
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

        // Vérifier l'existence d'une enchère pour un utilisateur donné sur un article donné
        try {
            return namedParameterJdbcTemplate.queryForObject(READ_ENCHERE_BY_NO, namedParameters, BeanPropertyRowMapper.newInstance(Enchere.class));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Enchere readMaxByArticle(int noArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        try {
            return namedParameterJdbcTemplate.queryForObject(READ_MAX_BY_ARTICLE, namedParameters, enchereRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Enchere> readAllByUtilisateur(int noUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_utilisateur", noUtilisateur);
        try{
            return namedParameterJdbcTemplate.query(READ_ALL_ENCHERE_BY_UTILISATEUR,namedParameters, new BeanPropertyRowMapper<>(Enchere.class));
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Enchere> readAllByArticleVendu(int noArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        try{
            return namedParameterJdbcTemplate.query(READ_ALL_ENCHERE_BY_ARTICLE,namedParameters, new BeanPropertyRowMapper<>(Enchere.class));
        } catch (Exception e) {
            return null;
        }

    }
}

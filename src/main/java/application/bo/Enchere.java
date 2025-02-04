package application.bo;

import java.time.LocalDate;

public class Enchere {
    private Utilisateur utilisateur;
    ArticleVendu articleVendu;
    private LocalDate dateEnchere;
    private int montantEnchere;


    //constructor


    public Enchere() {
    }

    public Enchere(Utilisateur utilisateur, ArticleVendu articleVendu, LocalDate dateEnchere,int montantEnchere) {
        this.utilisateur = utilisateur;
        this.articleVendu=articleVendu;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;


    }


    //getter setter


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    public LocalDate getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDate dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "utilisateur=" + utilisateur +
                ", articleVendu=" + articleVendu +
                ", dateEnchere=" + dateEnchere +
                ", montantEnchere=" + montantEnchere +
                '}';
    }
}

package application.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int miseAPrix;
    private int prixVente;
    private EtatVente etatVente;
    private Categorie categorie;
    private Retrait newRetrait;
    private Utilisateur utilisateurVendeur;
    private Utilisateur utilisateurAcheteur;
    private List<Enchere> encheres = new ArrayList<Enchere>();


    //constructor


    public ArticleVendu() {}

    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente, EtatVente etatVente, Categorie categorie, Retrait newRetrait, Utilisateur utilisateurVendeur, Utilisateur utilisateurAcheteur, List<Enchere> encheres) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.categorie = categorie;
        this.newRetrait = newRetrait;
        this.utilisateurVendeur = utilisateurVendeur;
        this.utilisateurAcheteur = utilisateurAcheteur;
        this.encheres = encheres;
    }



    // getter setter


    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }


    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public EtatVente getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(EtatVente etatVente) {
        this.etatVente = etatVente;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Retrait getNewRetrait() {
        return newRetrait;
    }

    public void setNewRetrait(Retrait newRetrait) {
        this.newRetrait = newRetrait;
    }

    public Utilisateur getUtilisateurVendeur() {
        return utilisateurVendeur;
    }

    public void setUtilisateurVendeur(Utilisateur utilisateurVendeur) {
        this.utilisateurVendeur = utilisateurVendeur;
    }

    public Utilisateur getUtilisateurAcheteur() {
        return utilisateurAcheteur;
    }

    public void setUtilisateurAcheteur(Utilisateur utilisateurAcheteur) {
        this.utilisateurAcheteur = utilisateurAcheteur;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }



    @Override
    public String toString() {
        return "ArticleVendu{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", miseAPrix=" + miseAPrix +
                ", prixVente=" + prixVente +
                ", etatVente=" + etatVente +
                ", categorie=" + categorie +
                ", newRetrait=" + newRetrait +
                ", utilisateurVendeur=" + utilisateurVendeur +
                ", utilisateurAcheteur=" + utilisateurAcheteur +
                ", encheres=" + encheres +
                '}';
    }
}

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
    private int prixInitial;
    private int prixVente;
    private Utilisateur utilisateurVendeur;
    private Categorie categorie;
    private Retrait retrait;
    private Utilisateur utilisateurAcheteur;
    private List<Enchere> encheres = new ArrayList<Enchere>();
    private EtatVente etatVente;


    //constructor


    public ArticleVendu() {}

    public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateurVendeur, Categorie categorie, Retrait retrait, Utilisateur utilisateurAcheteur, List<Enchere> encheres, EtatVente etatVente) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateurVendeur = utilisateurVendeur;
        this.categorie = categorie;
        this.retrait = retrait;
        this.utilisateurAcheteur = utilisateurAcheteur;
        this.encheres = encheres;
        this.etatVente = etatVente;
    }

    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateurVendeur, Categorie categorie, Retrait retrait, Utilisateur utilisateurAcheteur, List<Enchere> encheres, EtatVente etatVente) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateurVendeur = utilisateurVendeur;
        this.categorie = categorie;
        this.retrait = retrait;
        this.utilisateurAcheteur = utilisateurAcheteur;
        this.encheres = encheres;
        this.etatVente = etatVente;
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

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
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

    public Retrait getRetrait() {
        return retrait;
    }

    public void setRetrait(Retrait newRetrait) {
        this.retrait = newRetrait;
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
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", utilisateurVendeur=" + utilisateurVendeur +
                ", categorie=" + categorie +
                ", retrait=" + retrait +
                ", utilisateurAcheteur=" + utilisateurAcheteur +
                ", encheres=" + encheres +
                ", etatVente=" + etatVente +
                '}';
    }
}

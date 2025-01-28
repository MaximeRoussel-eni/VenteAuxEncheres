package application.bo;

import java.time.LocalDate;

public class Enchere {
    private LocalDate dateEnchère;
    private int montant_enchere;
    private Utilisateur utilisateur;

    //constructor


    public Enchere() {
    }

    public Enchere(LocalDate dateEnchère, int montant_enchere, Utilisateur utilisateur) {
        this.dateEnchère = dateEnchère;
        this.montant_enchere = montant_enchere;
        this.utilisateur = utilisateur;
    }


    //getter setter


    public LocalDate getDateEnchère() {
        return dateEnchère;
    }

    public void setDateEnchère(LocalDate dateEnchère) {
        this.dateEnchère = dateEnchère;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "dateEnchère=" + dateEnchère +
                ", montant_enchere=" + montant_enchere +
                ", utilisateur=" + utilisateur +
                '}';
    }
}

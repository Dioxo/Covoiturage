package me.dioxo.covoiturage;

public class Trajet {
    private String depart;
    private String arrive;
    private String nomConducteur;
    private String telephone;
    private String heure;
    private String prix;
    private String marque;

    public Trajet(String depart, String arrive, String nomConducteur, String telephone, String heure, String prix, String marque) {
        this.depart = depart;
        this.arrive = arrive;
        this.nomConducteur = nomConducteur;
        this.telephone = telephone;
        this.heure = heure;
        this.prix = prix;
        this.marque = marque;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrive() {
        return arrive;
    }

    public String getNomConducteur() {
        return nomConducteur;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getHeure() {
        return heure;
    }

    public String getPrix() {
        return prix;
    }

    public String getMarque() {
        return marque;
    }
}

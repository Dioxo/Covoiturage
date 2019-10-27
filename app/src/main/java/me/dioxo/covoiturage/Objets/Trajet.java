package me.dioxo.covoiturage.Objets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Trajet {
    @SerializedName("depart")
    @Expose
    private String depart;
    @SerializedName("arrive")
    @Expose
    private String arrive;
    @SerializedName("nomConducteur")
    @Expose
    private String nomConducteur;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("heure")
    @Expose
    private String heure;
    @SerializedName("prix")
    @Expose
    private String prix;
    @SerializedName("marque")
    @Expose
    private String marque;
    @SerializedName("status")
    @Expose
    private String status;
    private int places;

    public Trajet(String depart, String arrive, String heure, String prix, int places) {
        this.depart = depart;
        this.arrive = arrive;
        this.heure = heure;
        this.prix = prix;
        this.places = places;
    }

    public Trajet(String depart, String arrive, String heure, String prix) {
        this.depart = depart;
        this.arrive = arrive;
        this.heure = heure;
        this.prix = prix;
    }

    public Trajet(String depart, String arrive, String nomConducteur, String telephone, String heure, String prix, String marque, String status) {
        this.depart = depart;
        this.arrive = arrive;
        this.nomConducteur = nomConducteur;
        this.telephone = telephone;
        this.heure = heure;
        this.prix = prix;
        this.marque = marque;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public int getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return "Trajet{" +
                "depart='" + depart + '\'' +
                ", arrive='" + arrive + '\'' +
                ", nomConducteur='" + nomConducteur + '\'' +
                ", telephone='" + telephone + '\'' +
                ", heure='" + heure + '\'' +
                ", prix='" + prix + '\'' +
                ", marque='" + marque + '\'' +
                '}';
    }
}

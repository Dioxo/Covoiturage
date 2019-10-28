package me.dioxo.covoiturage.Objets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passager {
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("status")
    @Expose
    private String status;

    public Passager(String nom, String status) {
        this.nom = nom;
        this.status = status;
    }

    public Passager(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getStatus() {
        return status;
    }
}

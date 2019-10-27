package me.dioxo.covoiturage.Model;

import me.dioxo.covoiturage.Objets.Trajet;

public interface AllerModel {
    void chercherTrajets();

    void cancelerTrajet(Trajet trajet);
}

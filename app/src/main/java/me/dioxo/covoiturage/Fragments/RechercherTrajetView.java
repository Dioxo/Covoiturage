package me.dioxo.covoiturage.Fragments;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;

public interface RechercherTrajetView {
    // Users inputs
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();

    void afficherTrajets(ArrayList<Trajet> trajets);
    void chercher(Trajet trajet);
    void showError(String message);
}

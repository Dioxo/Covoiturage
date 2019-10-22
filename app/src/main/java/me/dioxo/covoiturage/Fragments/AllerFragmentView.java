package me.dioxo.covoiturage.Fragments;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;

public interface AllerFragmentView {
    void afficherTrajets(ArrayList<Trajet> trajets);
    void cancelerTrajet(Trajet trajet);
    void showError(String message);

}

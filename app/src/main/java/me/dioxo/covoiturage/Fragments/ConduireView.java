package me.dioxo.covoiturage.Fragments;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;

public interface ConduireView {
    void afficherTrajets(ArrayList<Trajet> trajets);
    void showInfo(Trajet trajet);
    void showError(String message);
}

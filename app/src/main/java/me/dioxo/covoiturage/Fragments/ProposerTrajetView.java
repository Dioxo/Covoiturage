package me.dioxo.covoiturage.Fragments;

import me.dioxo.covoiturage.Objets.Trajet;

public interface ProposerTrajetView {
    void enableInputs();
    void disableInputs();
    void showProgressbar();
    void hideProgressbar();

    void proposerTrajet(Trajet trajet);
    void showMessage(String message);
}

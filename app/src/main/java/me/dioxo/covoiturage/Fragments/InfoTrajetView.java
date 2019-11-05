package me.dioxo.covoiturage.Fragments;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Passager;
public interface InfoTrajetView {
    void afficherPassagers(ArrayList<Passager> passagers);
    void showError(String message);

}

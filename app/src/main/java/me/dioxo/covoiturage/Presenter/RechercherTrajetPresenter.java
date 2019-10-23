package me.dioxo.covoiturage.Presenter;

import java.util.ArrayList;

import me.dioxo.covoiturage.Events.EventRechercher;
import me.dioxo.covoiturage.Objets.Trajet;

public interface RechercherTrajetPresenter {
    void onCreate();
    void onDestroy();
    void onMainThread(EventRechercher event);
    void chercher(Trajet trajet);
    void afficherTrajets(ArrayList<Trajet> trajets);
    void showError(String error);


}

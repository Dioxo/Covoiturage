package me.dioxo.covoiturage.Presenter;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;

public interface AllerPresenter {
    void onCreate();
    void onDestroy();
    void onMainThread(EventAller event);
    void afficherTrajets(ArrayList<Trajet> trajets);
    void showError(String error);
    void chercherTrajets();
}

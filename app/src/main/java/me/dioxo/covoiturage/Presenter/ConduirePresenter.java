package me.dioxo.covoiturage.Presenter;

import java.util.ArrayList;

import me.dioxo.covoiturage.Events.EventAller;
import me.dioxo.covoiturage.Events.EventConduire;
import me.dioxo.covoiturage.Objets.Trajet;

public interface ConduirePresenter {
    void onCreate();
    void onDestroy();
    void onMainThread(EventConduire event);
    void afficherTrajets(ArrayList<Trajet> trajets);
    void showError(String error);
    void chercherTrajets();
}

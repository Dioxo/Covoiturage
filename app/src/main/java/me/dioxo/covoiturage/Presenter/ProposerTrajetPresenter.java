package me.dioxo.covoiturage.Presenter;

import me.dioxo.covoiturage.Events.ProposerEvent;
import me.dioxo.covoiturage.Objets.Trajet;

public interface ProposerTrajetPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ProposerEvent event);
    void proposerTrajet(Trajet trajet);
    void showMessage(String message);
}

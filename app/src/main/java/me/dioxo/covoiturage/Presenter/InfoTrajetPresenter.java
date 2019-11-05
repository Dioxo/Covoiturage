package me.dioxo.covoiturage.Presenter;


import java.util.ArrayList;

import me.dioxo.covoiturage.Events.InfoTrajetEvent;
import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Trajet;

public interface InfoTrajetPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(InfoTrajetEvent event);

    void modifyStatus(Passager passager, int status);
    void cherecherPassagers(Trajet trajet);

    void afficherPassagers(ArrayList<Passager> passagers);
}

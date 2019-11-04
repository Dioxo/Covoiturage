package me.dioxo.covoiturage.Presenter;

import me.dioxo.covoiturage.Events.InfoTrajetEvent;
import me.dioxo.covoiturage.Fragments.InfoTrajetView;
import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class InfoTrajetPresenterImpl implements InfoTrajetPresenter {

    InfoTrajetView view;
    EventBus eventBus;
    public InfoTrajetPresenterImpl(InfoTrajetView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void onEventMainThread(InfoTrajetEvent event) {

    }

    @Override
    public void modifyStatus(Passager passager, int status) {

    }

    @Override
    public void cherecherPassagers(Trajet trajet) {

    }
}

package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.covoiturage.Events.InfoTrajetEvent;
import me.dioxo.covoiturage.Fragments.InfoTrajetView;
import me.dioxo.covoiturage.Model.InfoTrajetModel;
import me.dioxo.covoiturage.Model.InfoTrajetModelImpl;
import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class InfoTrajetPresenterImpl implements InfoTrajetPresenter {

    InfoTrajetView view;
    EventBus eventBus;
    InfoTrajetModel model;
    public InfoTrajetPresenterImpl(InfoTrajetView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new InfoTrajetModelImpl();
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
    @Subscribe
    public void onEventMainThread(InfoTrajetEvent event) {
        switch (event.getEventType()){
            case InfoTrajetEvent.CHERCHER_SUCCESS:
                afficherPassagers(event.getPassagers());
                break;

            case InfoTrajetEvent.CHERCHER_ERROR:
            case InfoTrajetEvent.CHANGE_STATUS_ERROR:
            case InfoTrajetEvent.CHANGE_STATUS_SUCCESS:
                if (view != null){
                    view.showError(event.getError());
                }
                break;


        }
    }

    @Override
    public void modifyStatus(Trajet trajet, Passager passager, int status) {
        model.modifyStatus(trajet, passager, status);
    }

    @Override
    public void cherecherPassagers(Trajet trajet) {
        model.cherecherPassagers(trajet);
    }

    @Override
    public void afficherPassagers(ArrayList<Passager> passagers) {
        if (view != null){
            view.afficherPassagers(passagers);
        }
    }
}

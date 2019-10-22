package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.covoiturage.Fragments.RechercherTrajetView;
import me.dioxo.covoiturage.Model.RechercherTrajetModel;
import me.dioxo.covoiturage.Model.RechercherTrajetModelImpl;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;


public class RechercherTrajetPresenterImpl implements  RechercherTrajetPresenter{
    RechercherTrajetView view;
    EventBus eventBus;
    RechercherTrajetModel model;


    public RechercherTrajetPresenterImpl(RechercherTrajetView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new RechercherTrajetModelImpl();
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
    public void onMainThread(EventRechercher event) {
        switch (event.getEventType()){
            case EventRechercher.CHERCHER_SUCCESS:
                afficherTrajets(event.getTrajets());
                break;

            case EventRechercher.CHERCHER_ERROR:
                showError(event.getError());
                break;

        }
    }

    @Override
    public void chercher(Trajet trajet) {
        model.chercher(trajet);
    }

    @Override
    public void afficherTrajets(ArrayList<Trajet> trajets) {
        if(view != null){
            view.afficherTrajets(trajets);
        }
    }

    @Override
    public void showError(String error) {
        if(view != null){
            view.showError(error);
        }
    }

    @Override
    public void enableInputs() {
        if(view != null){
            view.enableInputs();
        }
    }

    @Override
    public void disableInputs() {
        if(view != null){
            view.disableInputs();
        }
    }

    @Override
    public void showProgressBar() {
        if(view != null){
            view.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if(view != null){
            view.hideProgressBar();
        }
    }
}

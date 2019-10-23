package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.covoiturage.Events.EventRechercher;
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
        if(view != null){
            view.disableInputs();
            view.showProgressBar();
        }
        model.chercher(trajet);
    }

    @Override
    public void afficherTrajets(ArrayList<Trajet> trajets) {
        if(view != null){
            view.enableInputs();
            view.hideProgressBar();
            view.afficherTrajets(trajets);
        }
    }

    @Override
    public void showError(String error) {
        if(view != null){
            view.enableInputs();
            view.hideProgressBar();
            view.showError(error);
        }
    }

}

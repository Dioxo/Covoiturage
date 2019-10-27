package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.covoiturage.Events.EventAller;
import me.dioxo.covoiturage.Fragments.AllerFragmentView;
import me.dioxo.covoiturage.Model.AllerModel;
import me.dioxo.covoiturage.Model.AllerModelImpl;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class AllerPresenterImpl implements AllerPresenter {
    EventBus eventBus;
    AllerFragmentView view;
    AllerModel model;

    public AllerPresenterImpl(AllerFragmentView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new AllerModelImpl();
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
    public void onMainThread(EventAller event) {
        switch (event.getEventType()){
            case EventAller.CHERCHER_SUCCESS:
                afficherTrajets(event.getTrajets());
                break;
            case EventAller.CANCELER_SUCCESS:
                showError(event.getError());
                chercherTrajets();
                break;

            case EventAller.CHERCHER_ERROR:
            case EventAller.CANCELER_ERROR:
                showError(event.getError());
                break;



        }
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
    public void chercherTrajets() {
        model.chercherTrajets();
    }

    @Override
    public void cancelerTrajet(Trajet trajet) {
        model.cancelerTrajet(trajet);
    }
}

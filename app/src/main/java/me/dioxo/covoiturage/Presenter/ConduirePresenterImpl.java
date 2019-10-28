package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.covoiturage.Events.EventConduire;
import me.dioxo.covoiturage.Fragments.ConduireView;
import me.dioxo.covoiturage.Model.ConduireModel;
import me.dioxo.covoiturage.Model.ConduireModelImpl;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class ConduirePresenterImpl implements ConduirePresenter {
    EventBus eventBus;
    ConduireView view;
    ConduireModel model;
    public ConduirePresenterImpl(ConduireView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new ConduireModelImpl();
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
    public void onMainThread(EventConduire event) {
        switch (event.getEventType()){
            case EventConduire.CHERCHER_SUCCESS:
                afficherTrajets(event.getTrajets());
                break;
            case EventConduire.CHERCHER_ERROR:
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


}

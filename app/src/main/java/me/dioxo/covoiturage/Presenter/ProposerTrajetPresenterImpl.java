package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import me.dioxo.covoiturage.Events.ProposerEvent;
import me.dioxo.covoiturage.Fragments.ProposerTrajetView;
import me.dioxo.covoiturage.Model.ProposerTrajetModel;
import me.dioxo.covoiturage.Model.ProposerTrajetModelImpl;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class ProposerTrajetPresenterImpl implements ProposerTrajetPresenter {

    ProposerTrajetView view;
    EventBus eventBus;
    ProposerTrajetModel model;

    public ProposerTrajetPresenterImpl(ProposerTrajetView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new ProposerTrajetModelImpl();
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
    public void onEventMainThread(ProposerEvent event) {
        switch (event.getEventType()){
            case ProposerEvent.ADD_SUCCESS:
                showMessage(event.getMessage());
                break;

            case ProposerEvent.ADD_ERROR:
                showMessage(event.getMessage());
                break;
        }
    }

    @Override
    public void proposerTrajet(Trajet trajet) {
        if (view != null){
            view.showProgressbar();
            view.disableInputs();
        }
        model.proposerTrajet(trajet);
    }

    @Override
    public void showMessage(String message) {
        if (view != null){
            view.enableInputs();
            view.hideProgressbar();
            view.showMessage(message);
        }
    }
}

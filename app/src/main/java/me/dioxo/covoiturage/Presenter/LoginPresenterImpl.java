package me.dioxo.covoiturage.Presenter;

import org.greenrobot.eventbus.Subscribe;

import me.dioxo.covoiturage.Events.LoginEvent;
import me.dioxo.covoiturage.Activity.LoginView;
import me.dioxo.covoiturage.Model.LoginModel;
import me.dioxo.covoiturage.Model.LoginModelImpl;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class LoginPresenterImpl implements LoginPresenter {
    LoginView view;
    EventBus eventBus;
    LoginModel model;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new LoginModelImpl();
    }

    @Override
    public void loginUser(String userId, String password) {
        model.loginUser(userId, password);
        if(view != null){
            view.disableInputs();
            view.showProgressbar();
        }
    }

    @Override
    public void showError(String message) {
        if (view != null) {
            view.enableInputs();
            view.hideProgressbar();
            view.showError(message);
        }
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
    public void onEventMainThread(LoginEvent loginEvent) {
        switch(loginEvent.getEventType()){
            case LoginEvent.LOGIN_SUCCESS:
                if(view != null){
                    view.goToMainScreen();
                }
                break;

            case LoginEvent.LOGIN_ERROR:
                showError(loginEvent.getError());
                break;
        }
    }
}

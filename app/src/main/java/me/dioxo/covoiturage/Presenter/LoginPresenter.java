package me.dioxo.covoiturage.Presenter;

import me.dioxo.covoiturage.Events.LoginEvent;

public interface LoginPresenter {
    void loginUser(String userId, String password);
    void showError(String message);
    void chercherIfAlreadyConected();

    void onCreate();
    void onDestroy();
    void onEventMainThread(LoginEvent loginEvent);

}

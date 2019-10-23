package me.dioxo.covoiturage.Activity;

public interface LoginPresenter {
    void loginUser(String userId, String password);
    void showError(String message);


    void onCreate();
    void onDestroy();
    void onEventMainThread(LoginEvent loginEvent);

}

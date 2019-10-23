package me.dioxo.covoiturage.Activity;

public interface LoginView {
    void loginUser();
    void showError(String message);
    void goToMainScreen();

    void enableInputs();
    void disableInputs();
    void showProgressbar();
    void hideProgressbar();
}

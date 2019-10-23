package me.dioxo.covoiturage.Activity;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;

class LoginEvent {
    public static final int LOGIN_ERROR = 0;
    public static final int LOGIN_SUCCESS = 1;

    private int eventType;
    private String error;


    //constructor quand CHERCHER_SUCCESS et list de trajets resultats
    public LoginEvent(int eventType) {
        this.eventType = eventType;
    }

    //constructor quand CHERCHER_ERROR et le message d'erreur
    public LoginEvent(int eventType, String error) {
        this.eventType = eventType;
        this.error = error;
    }


    public int getEventType() {
        return eventType;
    }


    public String getError() {
        return error;
    }
}
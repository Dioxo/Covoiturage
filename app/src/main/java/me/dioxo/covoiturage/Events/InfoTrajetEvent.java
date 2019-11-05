package me.dioxo.covoiturage.Events;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Passager;

public class InfoTrajetEvent {
    public static final int CHERCHER_ERROR = 0;
    public static final int CHERCHER_SUCCESS = 1;
    public static final int CANCELER_ERROR = 2;
    public static final int CANCELER_SUCCESS = 3;

    private int eventType;
    private ArrayList<Passager> passagers;
    private String error;


    //constructor quand CHERCHER_SUCCESS et list de trajets resultats
    public InfoTrajetEvent(int eventType, ArrayList<Passager> passagers) {
        this.eventType = eventType;
        this.passagers = passagers;
    }

    //constructor quand CHERCHER_ERROR et le message d'erreur
    public InfoTrajetEvent(int eventType, String error) {
        this.eventType = eventType;
        this.error = error;
    }


    public int getEventType() {
        return eventType;
    }

    public ArrayList<Passager> getPassagers() {
        return passagers;
    }

    public String getError() {
        return error;
    }
}

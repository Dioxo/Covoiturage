package me.dioxo.covoiturage.Events;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;

public class EventConduire {
    public static final int CHERCHER_ERROR = 0;
    public static final int CHERCHER_SUCCESS = 1;


    private int eventType;
    private ArrayList<Trajet> trajets;
    private String error;


    //constructor quand CHERCHER_SUCCESS et list de trajets resultats
    public EventConduire(int eventType, ArrayList<Trajet> trajets) {
        this.eventType = eventType;
        this.trajets = trajets;
    }

    //constructor quand CHERCHER_ERROR et le message d'erreur
    public EventConduire(int eventType, String error) {
        this.eventType = eventType;
        this.error = error;
    }


    public int getEventType() {
        return eventType;
    }

    public ArrayList<Trajet> getTrajets() {
        return trajets;
    }

    public String getError() {
        return error;
    }
}
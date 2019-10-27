package me.dioxo.covoiturage.Events;

public class ProposerEvent {
    public static final int ADD_SUCCESS = 0;
    public static final int ADD_ERROR = 1;

    private int eventType;
    private String message;

    //constructor quand CHERCHER_ERROR et le message d'erreur
    public ProposerEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }


    public int getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }
}
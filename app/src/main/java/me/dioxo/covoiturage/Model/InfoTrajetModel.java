package me.dioxo.covoiturage.Model;

import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Trajet;

public interface InfoTrajetModel {
    void modifyStatus(Trajet trajet, Passager passager, int status);
    void cherecherPassagers(Trajet trajet);
}

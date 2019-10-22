package me.dioxo.covoiturage.Model;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.Presenter.EventRechercher;
import me.dioxo.covoiturage.Request.ChercherTrajet;
import me.dioxo.covoiturage.libs.ApplicationContextProvider;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class RechercherTrajetModelImpl implements RechercherTrajetModel {
    EventBus eventBus;

    public RechercherTrajetModelImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void chercher(Trajet trajet) {
        Response.Listener<String> success = response -> {
            EventRechercher event;
            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Log.i("Response", response);
            Type type = new TypeToken<List<Trajet>>(){}.getType();

            ArrayList<Trajet> trajets = gson.fromJson(response, type);

            if(trajets != null){
                event = new EventRechercher(EventRechercher.CHERCHER_SUCCESS, trajets);
            }else{
                event = new EventRechercher(EventRechercher.CHERCHER_ERROR, "Nous n'avons pas reussi à trouver les resultats pour votre recherche");
            }

            eventBus.post(event);

        };

        Response.ErrorListener  errorListener = error -> {
            Log.e("Error", error.toString());
            EventRechercher event = new EventRechercher(EventRechercher.CHERCHER_ERROR,
                    error.toString());
            eventBus.post(event);
        };


        ChercherTrajet chercherTrajet = new ChercherTrajet(success, errorListener, trajet);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(chercherTrajet);
    }
}

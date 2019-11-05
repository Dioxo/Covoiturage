package me.dioxo.covoiturage.Model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.dioxo.covoiturage.Events.InfoTrajetEvent;
import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Routes;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.ApplicationContextProvider;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class InfoTrajetModelImpl implements InfoTrajetModel {
    EventBus eventBus;


    public InfoTrajetModelImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void modifyStatus(Passager passager, int status) {
        Response.Listener listener = response -> {

        };
    }

    @Override
    public void cherecherPassagers(Trajet trajet) {
        Response.Listener<String> success = response -> {
            InfoTrajetEvent event;
            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Log.i("Response", response);
            Type type = new TypeToken<List<Passager>>(){}.getType();

            ArrayList<Passager> passagers = gson.fromJson(response, type);

            if(passagers != null){
                event = new InfoTrajetEvent(InfoTrajetEvent.CHERCHER_SUCCESS, passagers);
            }else{
                event = new InfoTrajetEvent(InfoTrajetEvent.CHERCHER_ERROR, "Nous n'avons pas reussi à trouver les resultats pour votre recherche");
            }

            eventBus.post(event);

        };

        Response.ErrorListener  errorListener = error -> {
            Log.e("Error", error.toString());
            InfoTrajetEvent event = new InfoTrajetEvent(InfoTrajetEvent.CHERCHER_ERROR,
                    error.toString());
            eventBus.post(event);
        };


        String url = Routes.SERVER_ROUTE + "/passager"+
                "?"+ Constantes.depart.toString()+"="+trajet.getDepart()+
                "&"+Constantes.arrive.toString()+"="+trajet.getArrive()+
                "&"+Constantes.heure.toString()+"="+trajet.getHeure()+
                "&"+Constantes.nomConducteur.toString()+"="+trajet.getNomConducteur();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, success, errorListener );

        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(stringRequest);
    }
}

package me.dioxo.covoiturage.Model;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.dioxo.covoiturage.Events.InfoTrajetEvent;
import me.dioxo.covoiturage.Events.ProposerEvent;
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
    public void modifyStatus(Trajet trajet, Passager passager, int status) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                InfoTrajetEvent event;

                if(jsonObject.getBoolean("result")){
                    event = new InfoTrajetEvent(InfoTrajetEvent.CHANGE_STATUS_SUCCESS, "Les modifications ont été bien pris en compte");
                }else{
                    event = new InfoTrajetEvent(InfoTrajetEvent.CHANGE_STATUS_ERROR, "Nous avons pas reussi à traiter les modifications, essayez ultariement");
                }

                eventBus.post(event);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            InfoTrajetEvent  event = new InfoTrajetEvent(InfoTrajetEvent.CHANGE_STATUS_ERROR, error.toString());

            eventBus.post(event);
        };


        String url = Routes.SERVER_ROUTE +"/changeStatus?"+
                "&"+Constantes.depart.toString()+"="+trajet.getDepart()+
                "&"+Constantes.arrive.toString()+"="+trajet.getArrive()+
                "&"+Constantes.heure.toString()+"="+trajet.getHeure()+
                "&"+Constantes.nomConducteur.toString()+"="+trajet.getNomConducteur() +
                "&"+Constantes.nomPassager.toString()+"="+passager.getNom() +
                "&"+Constantes.status.toString()+"="+status;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, success, errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(stringRequest);
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

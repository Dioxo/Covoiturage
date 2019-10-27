package me.dioxo.covoiturage.Model;

import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import me.dioxo.covoiturage.Events.LoginEvent;
import me.dioxo.covoiturage.Events.ProposerEvent;
import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.Objets.Routes;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.libs.ApplicationContextProvider;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class ProposerTrajetModelImpl implements ProposerTrajetModel {
    EventBus eventBus;

    public ProposerTrajetModelImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void proposerTrajet(Trajet trajet) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                ProposerEvent event;

                if(jsonObject.getBoolean("result")){
                    event = new ProposerEvent(ProposerEvent.ADD_SUCCESS, "Le trajet à bien été proposé");
                }else{
                    event = new ProposerEvent(ProposerEvent.ADD_ERROR, "Le trajet n'a pas pu être proposé");
                }

                eventBus.post(event);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            ProposerEvent  event = new ProposerEvent(ProposerEvent.ADD_ERROR, error.toString());

            eventBus.post(event);
        };

        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences("ID_USER", 0);
        String id_user = settings.getString("ID_USER",null);


        String url = Routes.SERVER_ROUTE +"?"+ Constantes.id.toString()+"=" + id_user+
                "&"+Constantes.depart.toString()+"="+trajet.getDepart()+
                "&"+Constantes.arrive.toString()+"="+trajet.getArrive()+
                "&"+Constantes.heure.toString()+"="+trajet.getHeure()+
                "&"+Constantes.prix.toString()+"="+trajet.getPrix()+
                "&"+Constantes.places.toString()+"="+trajet.getPlaces();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, success, errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(stringRequest);
    }
}

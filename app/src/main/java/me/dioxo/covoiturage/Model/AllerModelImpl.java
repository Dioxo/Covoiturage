package me.dioxo.covoiturage.Model;

import android.content.Context;
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

import me.dioxo.covoiturage.Events.LoginEvent;
import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.Objets.Routes;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.Events.EventAller;
import me.dioxo.covoiturage.Events.EventRechercher;
import me.dioxo.covoiturage.libs.ApplicationContextProvider;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

import static android.content.Context.MODE_PRIVATE;

public class AllerModelImpl implements AllerModel {
    EventBus eventBus;

    public AllerModelImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void chercherTrajets() {
        Response.Listener<String> success = response -> {
            EventAller event;
            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Log.i("Response", response);
            Type type = new TypeToken<List<Trajet>>(){}.getType();

            ArrayList<Trajet> trajets = gson.fromJson(response, type);

            if(trajets != null){
                event = new EventAller(EventAller.CHERCHER_SUCCESS, trajets);
            }else{
                event = new EventAller(EventAller.CHERCHER_ERROR, "Nous n'avons pas reussi à trouver les resultats pour votre recherche");
            }

            eventBus.post(event);

        };

        Response.ErrorListener  errorListener = error -> {
            Log.e("Error", error.toString());
            EventAller event = new EventAller(EventRechercher.CHERCHER_ERROR,
                                                error.toString());
            eventBus.post(event);
        };

        /*
        SharedPreferences sharedPref = ApplicationContextProvider.getContext().getSharedPreferences(
                Constantes.user.toString(), Context.MODE_PRIVATE);
        String id = sharedPref.getString(Constantes.id.toString(), null);


        Log.i("ID", id);
        */
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences("ID_USER", 0);
        String id_user = settings.getString("ID_USER",null);

        if(id_user  != null) {
            Log.i("Session LOl" , "Already Connected" );
            Log.i("Session LOl" , id_user);
        }

        String url = Routes.SERVER_ROUTE +"?"+ Constantes.id.toString()+"=" + id_user;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, success, errorListener );

        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(stringRequest);
    }

    @Override
    public void cancelerTrajet(Trajet trajet) {
        Response.Listener<String> success = response -> {

            try {

                JSONObject jsonObject = new JSONObject(response);
                EventAller eventAller;

                // si l'actualisation est effectuée

                if(jsonObject.getBoolean("result")){
                    eventAller = new EventAller(EventAller.CANCELER_SUCCESS, "Cancelado pues");
                }else{
                    eventAller = new EventAller(EventAller.CHERCHER_ERROR, "Nous n'avons pas reussi à canceler votre trajet");
                }

                eventBus.post(eventAller);

            } catch (JSONException e) {
                e.printStackTrace();
            }



        };

        Response.ErrorListener  errorListener = error -> {
            Log.e("Error", error.toString());
            EventAller event = new EventAller(EventAller.CANCELER_ERROR,
                    error.toString());
            eventBus.post(event);
        };

        /*
        SharedPreferences sharedPref = ApplicationContextProvider.getContext().getSharedPreferences(
                Constantes.user.toString(), Context.MODE_PRIVATE);
        String id = sharedPref.getString(Constantes.id.toString(), null);


        Log.i("ID", id);
        */
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences("ID_USER", 0);
        String id_user = settings.getString("ID_USER",null);

        if(id_user  != null) {
            Log.i("Session LOl" , "Already Connected" );
            Log.i("Session LOl" , id_user);
        }

        String url = Routes.SERVER_ROUTE +"/aller?"+ Constantes.id.toString()+"=" + id_user+
                "&"+Constantes.depart.toString()+"="+trajet.getDepart()+
                "&"+Constantes.arrive.toString()+"="+trajet.getArrive()+
                "&"+Constantes.heure.toString()+"="+trajet.getHeure();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, success, errorListener );

        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(stringRequest);
    }
}

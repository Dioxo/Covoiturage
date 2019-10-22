package me.dioxo.covoiturage.Request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.Objets.Routes;
import me.dioxo.covoiturage.Objets.Trajet;

public class ChercherTrajet  extends StringRequest {

    private Map<String, String > parametres;

    public ChercherTrajet(Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener, @NonNull Trajet trajet) {
        super(Method.GET, Routes.SERVER_ROUTE,listener,errorListener);

        parametres = new HashMap<>();
        parametres.put(Constantes.depart.toString(), trajet.getDepart());
        parametres.put(Constantes.arrive.toString(), trajet.getArrive());
        parametres.put(Constantes.heure.toString(), trajet.getHeure());
        parametres.put(Constantes.prix.toString(), trajet.getPrix());

    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}

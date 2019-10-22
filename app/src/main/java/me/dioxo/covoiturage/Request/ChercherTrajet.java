package me.dioxo.covoiturage.Request;

import android.util.Log;

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

    public ChercherTrajet(Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener, @NonNull Trajet trajet,String url) {
        super(Method.POST, url,listener,errorListener);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}

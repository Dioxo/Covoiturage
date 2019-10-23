package me.dioxo.covoiturage.Model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import me.dioxo.covoiturage.Events.LoginEvent;
import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.Objets.Routes;
import me.dioxo.covoiturage.libs.ApplicationContextProvider;
import me.dioxo.covoiturage.libs.EventBus;
import me.dioxo.covoiturage.libs.GreenRobotEventBus;

public class LoginModelImpl implements LoginModel {
    EventBus eventBus;

    public LoginModelImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void loginUser(String userId, String password) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                LoginEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    noteEvent = new LoginEvent(LoginEvent.LOGIN_SUCCESS);
                }else{
                    noteEvent = new LoginEvent(LoginEvent.LOGIN_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            LoginEvent  noteEvent = new LoginEvent(LoginEvent.LOGIN_ERROR, error.toString());
            eventBus.post(noteEvent);
        };

        String url = Routes.SERVER_ROUTE +"?"+ Constantes.id.toString()+"=" + userId+
                                            "&"+Constantes.password.toString()+"="+password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, success, errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(stringRequest);
    }
}

package me.dioxo.covoiturage.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.covoiturage.MainActivity;
import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter presenter;
    @BindView(R.id.edtIdUser)
    EditText edtIdUser;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this);
        presenter.onCreate();
        chercherIfAlreadyConected();
    }

    private void chercherIfAlreadyConected() {
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(String.valueOf(Constantes.user), MODE_PRIVATE);

        if(sharedPreferences.contains(String.valueOf(Constantes.id))){
            String id = sharedPreferences.getString(String.valueOf(Constantes.id) , null);
            if(id != null){
                Log.i("ID", id);
                goToMainScreen();
            }
        }

    }

    @Override
    public void loginUser() {
        presenter.loginUser(edtIdUser.getText().toString(), edtPassword.getText().toString());
    }

    @Override
    public void showError(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void goToMainScreen() {
        saveIdUser();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void saveIdUser() {
        getBaseContext().getSharedPreferences(String.valueOf(Constantes.user), MODE_PRIVATE)
                .edit()
                .putString(String.valueOf(Constantes.id), edtIdUser.getText().toString())
                .apply();
    }

    @Override
    public void enableInputs() {
        edtIdUser.setEnabled(true);
        edtPassword.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        edtIdUser.setEnabled(false);
        edtPassword.setEnabled(false);
    }

    @Override
    public void showProgressbar() {
        progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar2.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnEntrer)
    public void onViewClicked() {
        loginUser();
    }
}

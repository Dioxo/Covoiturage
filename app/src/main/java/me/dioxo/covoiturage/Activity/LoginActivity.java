package me.dioxo.covoiturage.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.covoiturage.MainActivity;
import me.dioxo.covoiturage.Objets.Constantes;
import me.dioxo.covoiturage.Presenter.LoginPresenter;
import me.dioxo.covoiturage.Presenter.LoginPresenterImpl;
import me.dioxo.covoiturage.R;
import me.dioxo.covoiturage.libs.ApplicationContextProvider;

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
        presenter.chercherIfAlreadyConected();
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
        startActivity(new Intent(this, MainActivity.class));
    }

    public void saveIdUser() {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences("ID_USER", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("ID_USER", edtIdUser.getText().toString());

        // Commit the edits!
        editor.apply();

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

package me.dioxo.covoiturage;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

import me.dioxo.covoiturage.Fragments.HomeFragment;
import me.dioxo.covoiturage.Fragments.ProposerTrajetFragment;
import me.dioxo.covoiturage.Fragments.RechercherTrajetFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
                                        RechercherTrajetFragment.OnFragmentInteractionListener,
                                        ProposerTrajetFragment.OnFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_rechercher_trajet:
                    selectedFragment = new RechercherTrajetFragment();
                    break;
                case R.id.navigation_proposer_trajet:
                    selectedFragment = new ProposerTrajetFragment();
                    break;
                /*case R.id.navigation_mes_trajets_passager:

                    break;
                case R.id.navigation_mes_trajets_conducteur:

                    break;*/
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                                    selectedFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottom_nav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        chargeFragmentParDefaut();
    }

    private void chargeFragmentParDefaut() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

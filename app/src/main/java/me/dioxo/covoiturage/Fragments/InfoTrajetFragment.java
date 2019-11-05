package me.dioxo.covoiturage.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dioxo.covoiturage.Adapter.AdapterPassager;
import me.dioxo.covoiturage.Adapter.AdapterVoitures;
import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.Presenter.InfoTrajetPresenter;
import me.dioxo.covoiturage.Presenter.InfoTrajetPresenterImpl;
import me.dioxo.covoiturage.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoTrajetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoTrajetFragment extends Fragment implements InfoTrajetView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.btn_cancel)
    ImageButton btnCancel;
    @BindView(R.id.txt_route)
    TextView txtRoute;
    @BindView(R.id.txt_conducteur)
    TextView txtConducteur;
    @BindView(R.id.txt_telephone)
    TextView txtTelephone;
    @BindView(R.id.txt_heure)
    TextView txtHeure;
    @BindView(R.id.txt_prix)
    TextView txtPrix;
    @BindView(R.id.txt_marque)
    TextView txtMarque;
    @BindView(R.id.btn_options)
    Button btnOptions;
    @BindView(R.id.recycler_view_passagers)
    RecyclerView recyclerViewPassagers;
    @BindView(R.id.container)
    LinearLayout container;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Trajet trajet;

    private InfoTrajetPresenter presenter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InfoTrajetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoTrajetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoTrajetFragment newInstance(String param1, String param2) {
        InfoTrajetFragment fragment = new InfoTrajetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_trajet, container, false);
        ButterKnife.bind(this, view);



        remplirInfoTrajet();
        presenter = new InfoTrajetPresenterImpl(this);
        presenter.onCreate();
        presenter.cherecherPassagers(trajet);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void changeStatus(Passager passager, int status) {
        Snackbar.make(container, passager.getNom() + " " + status , Snackbar.LENGTH_LONG).show();
    }

    private void remplirInfoTrajet() {
        assert trajet != null;
        btnCancel.setVisibility(View.GONE);
        String tmp = trajet.getDepart() +
                " - " +
                trajet.getArrive();
        txtRoute.setText(tmp);
        txtConducteur.setText(trajet.getNomConducteur());
        txtTelephone.setText(trajet.getTelephone());
        txtHeure.setText(trajet.getHeure());
        txtPrix.setText(trajet.getPrix());
        txtMarque.setText(trajet.getMarque());

        btnOptions.setVisibility(View.GONE);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void afficherPassagers(ArrayList<Passager> passagers) {
        if (passagers.size() == 0) {
            //Il n'y a pas de passagers
            showError("Vous n'avez pas encore de passagers");
        } else {
            recyclerViewPassagers.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerViewPassagers.setLayoutManager(layoutManager);

            mAdapter = new AdapterPassager(passagers, this::changeStatus);
            recyclerViewPassagers.setAdapter(mAdapter);

        }
    }

    @Override
    public void showError(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}

package me.dioxo.covoiturage.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dioxo.covoiturage.Adapter.AdapterVoitures;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.Presenter.ConduirePresenter;
import me.dioxo.covoiturage.Presenter.ConduirePresenterImpl;
import me.dioxo.covoiturage.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConduireFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConduireFragment extends Fragment implements ConduireView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view_voitures)
    RecyclerView recyclerViewVoitures;
    @BindView(R.id.container)
    FrameLayout container;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ConduirePresenter presenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ConduireFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConduireFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConduireFragment newInstance(String param1, String param2) {
        ConduireFragment fragment = new ConduireFragment();
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
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_conduire, container, false);
        ButterKnife.bind(this, view);
        presenter = new ConduirePresenterImpl(this);
        presenter.onCreate();
        presenter.chercherTrajets();

        return  view;
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
    public void afficherTrajets(ArrayList<Trajet> trajets) {
        if (trajets.size() == 0) {
            //Il n'y a pas de trajets
            showError("Vous avez pas de trajets à faire");
        } else {
            recyclerViewVoitures.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerViewVoitures.setLayoutManager(layoutManager);

            mAdapter = new AdapterVoitures(trajets, 2, this::showInfo);
            recyclerViewVoitures.setAdapter(mAdapter);
        }
    }

    @Override
    public void showInfo(Trajet trajet) {
        Snackbar.make(container, "Trajet à conduire " + trajet, Snackbar.LENGTH_SHORT)
                .show();

        InfoTrajetFragment info = new InfoTrajetFragment();
        info.setTrajet(trajet);
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                info).commit();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_SHORT)
                .show();
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
}

package me.dioxo.covoiturage.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.Presenter.ProposerTrajetPresenter;
import me.dioxo.covoiturage.Presenter.ProposerTrajetPresenterImpl;
import me.dioxo.covoiturage.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProposerTrajetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProposerTrajetFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, ProposerTrajetView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.spinner_depart)
    Spinner spinnerDepart;
    @BindView(R.id.spinner_arrive)
    Spinner spinnerArrive;
    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.places)
    EditText places;
    @BindView(R.id.prix)
    EditText prix;
    @BindView(R.id.add)
    ImageButton add;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;
    @BindView(R.id.container)
    ConstraintLayout container;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Calendar calendar;
    private OnFragmentInteractionListener mListener;
    private int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    private ProposerTrajetPresenter presenter;

    public ProposerTrajetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProposerTrajetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProposerTrajetFragment newInstance(String param1, String param2) {
        ProposerTrajetFragment fragment = new ProposerTrajetFragment();
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
        View view = inflater.inflate(R.layout.fragment_proposer_trajet, container, false);
        ButterKnife.bind(this, view);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.students_locations, android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDepart.setAdapter(arrayAdapter);
        spinnerArrive.setAdapter(arrayAdapter);

        //Eviter introduire d'input
        date.setInputType(InputType.TYPE_NULL);


        //chaque fois qu'on click, on va selectionner la date
        date.setOnClickListener(view1 -> selectDate());
        date.setOnFocusChangeListener((view1, hasFocus) -> {
            if (hasFocus) {
                selectDate();
            }
        });

        presenter = new ProposerTrajetPresenterImpl(this);
        presenter.onCreate();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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

    public void selectDate() {
        calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = day;

        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), this, hour, minute, true);

        timePickerDialog.show();
    }


    @OnClick(R.id.add)
    public void onViewClicked() {
        Trajet trajet = new Trajet(spinnerDepart.getSelectedItem().toString(),
                                    spinnerArrive.getSelectedItem().toString(),
                                    date.getText().toString(),
                                    prix.getText().toString(),
                                    Integer.parseInt(places.getText().toString()));
        proposerTrajet(trajet);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;


        //Formato AAAA-MM-DD HH:MM:SS
        StringBuilder fecha = formatoFecha();

        date.setText(fecha);
    }

    private StringBuilder formatoFecha() {
        //Formato AAAA-MM-DD HH:MM:SS

        StringBuilder fecha = new StringBuilder();

        //first concat year
        fecha.append(yearFinal).append("-");

        if (monthFinal >= 10) {
            fecha.append(monthFinal).append("-");
        } else {
            fecha.append(0).append(monthFinal).append("-");
        }

        if (dayFinal >= 10) {
            fecha.append(dayFinal).append(" ");
        } else {
            fecha.append(0).append(dayFinal).append(" ");
        }

        if (hourFinal >= 10) {
            fecha.append(hourFinal).append(":");
        } else {
            fecha.append(0).append(hourFinal).append(":");
        }

        if (minuteFinal >= 10) {
            fecha.append(minuteFinal);
        } else {
            fecha.append(0).append(minuteFinal);
        }

        return fecha;
    }

    @Override
    public void enableInputs() {
        spinnerDepart.setEnabled(true);
        spinnerArrive.setEnabled(true);
        date.setEnabled(true);
        places.setEnabled(true);
        prix.setEnabled(true);
        add.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        spinnerDepart.setEnabled(false);
        spinnerArrive.setEnabled(false);
        date.setEnabled(false);
        places.setEnabled(false);
        prix.setEnabled(false);
        add.setEnabled(false);
    }

    @Override
    public void showProgressbar() {
        progressBar3.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar3.setVisibility(View.GONE);
    }

    @Override
    public void proposerTrajet(Trajet trajet) {
        presenter.proposerTrajet(trajet);
    }

    @Override
    public void showMessage(String message) {
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
}

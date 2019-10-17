package me.dioxo.covoiturage.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.covoiturage.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RechercherTrajetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RechercherTrajetFragment extends Fragment implements  DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.spinner_depart)
    Spinner spinnerDepart;
    @BindView(R.id.spinner_arrive)
    Spinner spinnerArrive;
    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.prix)
    EditText prix;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Calendar calendar;
    private OnFragmentInteractionListener mListener;
    private int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    public RechercherTrajetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RechercherTrajetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RechercherTrajetFragment newInstance(String param1, String param2) {
        RechercherTrajetFragment fragment = new RechercherTrajetFragment();
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
        View view = inflater.inflate(R.layout.fragment_rechercher_trajet, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> locations = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.students_locations)));


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext()
                , android.R.layout.simple_spinner_item,
                locations);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Eviter introduire d'input
        date.setInputType(InputType.TYPE_NULL);


        //chaque fois qu'on click, on va selectionner la date
        date.setOnClickListener(view1 -> selectDate() );
        date.setOnFocusChangeListener((view1, hasFocus) -> {
            if(hasFocus) {
                selectDate();
            }
        });

        spinnerArrive.setAdapter(arrayAdapter);
        spinnerDepart.setAdapter(arrayAdapter);

        return view;
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

package me.dioxo.covoiturage.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.R;

public class AdapterVoitures extends RecyclerView.Adapter<AdapterVoitures.MyViewHolder> {
    private ArrayList<Trajet> trajets;
    //si type = 0, rechercher voitures
    //si type = 1, aller
    //si type = 2, conduire
    private int type;

    public interface OnItemClickListener {
        void onBtnClicked( Trajet trajet);
    }

    private final OnItemClickListener listener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterVoitures(ArrayList<Trajet> trajets, int type,OnItemClickListener listener) {
        this.trajets = trajets;
        this.type = type;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtRoute;
        public TextView txtConducteur;
        public TextView txtTelephone;
        public TextView txtHeure;
        public TextView txtPrix;
        public TextView txtMarque;
        public ImageView carPic;
        public Button btnOptions;
        public ImageButton btnCancel;

        public MyViewHolder(CardView v) {
            super(v);
            txtConducteur = v.findViewById(R.id.txt_conducteur);
            txtRoute = v.findViewById(R.id.txt_route);
            txtTelephone = v.findViewById(R.id.txt_telephone);
            txtHeure = v.findViewById(R.id.txt_heure);
            txtPrix = v.findViewById(R.id.txt_prix);
            txtMarque = v.findViewById(R.id.txt_marque);
            carPic = v.findViewById(R.id.img_voitures);
            btnOptions = v.findViewById(R.id.btn_options);
            btnCancel = v.findViewById(R.id.btn_cancel);
        }
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_voiture, parent, false);



        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String trajet = trajets.get(position).getDepart() +
                                                " - " +
                                trajets.get(position).getArrive();
        holder.txtRoute.setText(trajet);
        holder.txtConducteur.setText(trajets.get(position).getNomConducteur());
        holder.txtTelephone.setText(trajets.get(position).getTelephone());
        holder.txtHeure.setText(trajets.get(position).getHeure());
        holder.txtPrix.setText(trajets.get(position).getPrix());
        holder.txtMarque.setText(trajets.get(position).getMarque());

        int min = 1, max = 3;
        int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));

        switch (nombreAleatoire){
            case 1:
                holder.carPic.setImageResource(R.drawable.red_car_face);
                break;
            case 2:
                holder.carPic.setImageResource(R.drawable.blue_car_face);
                break;
            case 3:
                holder.carPic.setImageResource(R.drawable.yellow_car);
                break;
        }

        switch (type){
            case 0: afficherRechercher(holder, position);
                break;

            case 1: afficherAller(holder, position);
                break;
        }



    }

    private void afficherAller(MyViewHolder holder, int position) {
        holder.btnOptions.setText("Status : " + trajets.get(position).getStatus());
        holder.btnCancel.setVisibility(View.VISIBLE);

        holder.btnCancel.setOnClickListener(view -> listener.onBtnClicked(trajets.get(position)));
    }

    private void afficherRechercher(MyViewHolder holder, int position) {
        holder.btnCancel.setVisibility(View.GONE);
        holder.btnOptions.setText("Choisir");
        holder.btnOptions.setOnClickListener(view -> listener.onBtnClicked(trajets.get(position)));

    }

    @Override
    public int getItemCount() {
        return trajets.size();
    }


}

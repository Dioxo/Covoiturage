package me.dioxo.covoiturage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterVoitures extends RecyclerView.Adapter<AdapterVoitures.MyViewHolder> {
    private ArrayList<Trajet> trajets;


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
        public ImageButton cancel;

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
            cancel = v.findViewById(R.id.btn_cancel);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterVoitures(ArrayList<Trajet> trajets) {
        this.trajets = trajets;
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

        holder.btnOptions.setText("Choisir");

        if(holder.btnOptions.getText().equals("Choisir")){
            holder.cancel.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return trajets.size();
    }
}

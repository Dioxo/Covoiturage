package me.dioxo.covoiturage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterVoitures extends RecyclerView.Adapter<AdapterVoitures.MyViewHolder> {
    private String[] mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtRoute;
        public TextView txtConducteur;
        public TextView txtTelephone;
        public TextView txtHeure;
        public TextView txtPrix;
        public TextView txtMarque;
        public ImageView imageView;
        public Button btnOptions;

        public MyViewHolder(CardView v) {
            super(v);
            txtConducteur = v.findViewById(R.id.txt_conducteur);
            txtRoute = v.findViewById(R.id.txt_route);
            txtTelephone = v.findViewById(R.id.txt_telephone);
            txtHeure = v.findViewById(R.id.txt_heure);
            txtPrix = v.findViewById(R.id.txt_prix);
            txtMarque = v.findViewById(R.id.txt_marque);
            imageView = v.findViewById(R.id.img_voitures);
            btnOptions = v.findViewById(R.id.btn_options);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterVoitures(String[] myDataset) {
        mDataset = myDataset;
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
        holder.txtRoute.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

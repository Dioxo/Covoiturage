package me.dioxo.covoiturage.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.dioxo.covoiturage.Objets.Passager;
import me.dioxo.covoiturage.Objets.Trajet;
import me.dioxo.covoiturage.R;

public class AdapterPassager extends RecyclerView.Adapter<AdapterPassager.MyViewHolder>  {

    private ArrayList<Passager> passagers;
    private final OnItemClickListener listener;

    public AdapterPassager(ArrayList<Passager> passagers, OnItemClickListener listener) {
        this.passagers = passagers;
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onBtnClicked( Passager passager, int status);
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView txtNomPassager;
        public TextView status;
        public Button btnAccepter;
        public Button btnAnnuler;

        public MyViewHolder(CardView cardView) {
            super(cardView);
            txtNomPassager = cardView.findViewById(R.id.txtNomPassager);
            status = cardView.findViewById(R.id.txtStatus);
            btnAccepter = cardView.findViewById(R.id.btnAccepter);
            btnAnnuler = cardView.findViewById(R.id.btnAnnuler);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_passager, parent, false);



        AdapterPassager.MyViewHolder vh = new AdapterPassager.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNomPassager.setText(passagers.get(position).getNom());

        if(passagers.get(position).getStatus() != null){
            holder.status.setText(passagers.get(position).getStatus());
            holder.btnAccepter.setVisibility(View.GONE);
            holder.btnAnnuler.setVisibility(View.GONE);
        }else{
            holder.status.setVisibility(View.GONE);
            holder.btnAccepter.setOnClickListener(view -> {
                listener.onBtnClicked(passagers.get(position), 0);
                holder.status.setVisibility(View.VISIBLE);
                holder.status.setText("Accepté(e)");
                holder.btnAccepter.setVisibility(View.GONE);
                holder.btnAnnuler.setVisibility(View.GONE);
            });
            holder.btnAnnuler.setOnClickListener(view -> {
                listener.onBtnClicked(passagers.get(position), 1);
                holder.status.setVisibility(View.VISIBLE);
                holder.status.setText("Annulé(e)");
                holder.btnAccepter.setVisibility(View.GONE);
                holder.btnAnnuler.setVisibility(View.GONE);
            });


        }
    }

    @Override
    public int getItemCount() {
        return passagers.size();
    }

}

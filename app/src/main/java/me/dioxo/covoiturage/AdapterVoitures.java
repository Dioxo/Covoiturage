package me.dioxo.covoiturage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterVoitures extends RecyclerView.Adapter<AdapterVoitures.MyViewHolder> {
    private String[] mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(CardView v) {
            super(v);
            textView = v.findViewById(R.id.text_test);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterVoitures(String[] myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public AdapterVoitures.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_voiture, parent, false);



        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVoitures.MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

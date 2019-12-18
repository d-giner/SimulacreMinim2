package com.learning.SimulMinim2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.SimulMinim2.Retrofit.Models.Element;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolderData> {
    private List<Element> elementsMuseu; /** Creem una llista de la classe elements */
    private Context context;

    public DataAdapter(List<Element> elements) { /** Constructor */
        this.elementsMuseu = elements;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { /** Enllaçarà aquest adaptador amb el layout item_list a través de la funció ViewHolderData() */
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);    /** ja que a aquesta funció li pasarem el view que hem creat aquí */
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.data.setText(elementsMuseu.get(position).getAdrecaNom());

        Picasso.get().load(elementsMuseu.get(position).getImatge().get(0)).into(holder.image);
        //Picasso.get().load(elementsMuseu.get(position).getImage().get(0)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return elementsMuseu.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView data;
        ImageView image;

        public ViewHolderData(@NonNull View itemView) { /** Creem una referència per modificar el idData (Del layout item_list) */
            super(itemView);
            data = itemView.findViewById(R.id.idData);
            image = itemView.findViewById(R.id.idImage);
        }
    }
}

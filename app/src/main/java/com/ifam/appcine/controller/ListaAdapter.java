package com.ifam.appcine.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ifam.appcine.model.CinemaBean;
import com.ifam.appcine.view.Semana;

import java.util.List;

/**
 * Created by fernando on 24/10/16.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.viewHolder> implements OnClickListener{

    private final Context context;
    private AdapterListener listener;
    private List<CinemaBean> lista;


    public ListaAdapter(Context context, List<CinemaBean> lista) {
        this.context = context;
        this.lista = lista;
    }

    public AdapterListener getListener(){
        return listener;
    }

    @Override
    public ListaAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListaAdapter.viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    class viewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        public viewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

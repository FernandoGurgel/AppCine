package com.ifam.appcine.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifam.appcine.R;
import com.ifam.appcine.model.CinemaBean;
import com.ifam.appcine.view.DetalheActivity;

import java.util.ArrayList;

/**
 * Created by fernando on 26/10/16.
 */

public class AdapterDetalhe extends RecyclerView.Adapter<AdapterDetalhe.ViewHolder> {


    private int tipo;
    private Context context;
    private ArrayList<String> cinema;
    private ArrayList<String> horario;

    public AdapterDetalhe(Context context , ArrayList<String> bean, ArrayList<String> a) {
        this.context =context;
        this.cinema = bean;
        this.horario = a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sinopse_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = cinema.get(position);
        String hor = horario.get(position);

        holder.setCine(text).setSessao(hor);

    }

    @Override
    public int getItemCount() {
        return cinema.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cine;
        private TextView sessao;

        public ViewHolder(View itemView) {
            super(itemView);

            cine = (TextView) itemView.findViewById(R.id.cinema_nome);
            sessao = (TextView) itemView.findViewById(R.id.cinema_horario);

        }

        public ViewHolder setCine(String cine) {
            if(!cine.isEmpty()){
                this.cine.setText(cine);
                return this;
            }else{
                return this;
            }

        }

        public ViewHolder setSessao(String sessao) {
            if(!sessao.isEmpty()){
                this.sessao.setText(sessao);
                return this;
            }else{
                return this;
            }
        }
    }
}

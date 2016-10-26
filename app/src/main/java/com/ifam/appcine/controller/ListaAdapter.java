package com.ifam.appcine.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ifam.appcine.R;
import com.ifam.appcine.model.CinemaBean;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 24/10/16.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder>{

    private Context context;
    private int layout;
    private AdapterListener listener;
    private List<CinemaBean> mList;
    private RecyclerViewOnClickListenerHack mrecyclerViewOnClickListenerHack;

    public ListaAdapter(Context context, List<CinemaBean> lista) {
        this.context = context;
        mList = lista;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CinemaBean bean = mList.get(position);
        holder.setCapa(bean.getCapa())
                .setFilme(bean.getFilme());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public AdapterListener getListener(){
        return listener;
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    public void setMrecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack m){
        mrecyclerViewOnClickListenerHack = m;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView capa;
        private TextView filme;

        public ViewHolder(View itemView) {
            super(itemView);

            capa = (ImageView) itemView.findViewById(R.id.capa_filme_item);
            filme = (TextView) itemView.findViewById(R.id.nome_filme_item);

            itemView.setOnClickListener(this);
        }

        public ViewHolder setCapa(String capa) {
            if (capa.isEmpty() || capa == null){
                return this;
            }else{
                Ocean.glide(context)
                        .load(capa)
                        .build(GlideRequest.BITMAP)
                        .resize(200,200)
                        .into(this.capa);
            }
            return this;
        }

        public ViewHolder setFilme(String filme) {
            if(filme.isEmpty() || filme == null){
                return this;
            }else{
                this.filme.setText(filme);
            }
            return this;
        }

        @Override
        public void onClick(View view) {

            if(mrecyclerViewOnClickListenerHack != null){
                mrecyclerViewOnClickListenerHack.onClickLister(view,getPosition());
            }

        }
    }
}

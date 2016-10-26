package com.ifam.appcine.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.ifam.appcine.R;
import com.ifam.appcine.controller.ListaAdapter;
import com.ifam.appcine.controller.RecyclerViewOnClickListenerHack;
import com.ifam.appcine.model.CinemaBean;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.http.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class Semana extends Fragment implements Request.RequestListener,RecyclerViewOnClickListenerHack {

    private List<CinemaBean> list;
    private RecyclerView recyclerView;
    private Context viewContext;
    private ProgressBar progressBar;
    private  ListaAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semana,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<CinemaBean>();

        Ocean.newRequest("https://gist.githubusercontent.com/FernandoGurgel/dfe45d84aaf7ead246a0871e80b6d3d5/raw/32d9c5e35bd68e21d569984fb7e0834cc6bdb693/filmes", this).get().send();

        progressBar = (ProgressBar) view.findViewById(R.id.progress_semana);

        recyclerView = (RecyclerView) view.findViewById(R.id.item_row_recyclerview);

        hideLoad(list);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layout = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layout);
        adapter = new ListaAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setMrecyclerViewOnClickListenerHack(this);
    }

    private void hideLoad(List<CinemaBean> list) {
        if (list.size() > 0){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRequestOk(String s, JSONObject jsonObject, int i) {
         if(i == Request.NENHUM_ERROR){

             if(s != null){
                 try {
                     ArrayList<String> cinemas;
                     ArrayList<String> semana;
                     ArrayList<String> fimSemana;
                     JSONObject object = new JSONObject(s);
                     JSONArray filmes = object.getJSONArray("filmes");

                     for(int a = 0; a < filmes.length(); a++){
                         JSONObject cinema = filmes.getJSONObject(a);
                         JSONArray cine = cinema.getJSONArray("lugares");
                         JSONArray sem = cinema.getJSONArray("semana");
                         JSONArray fim = cinema.getJSONArray("fimsemana");
                         cinemas = new ArrayList<>();
                         semana = new ArrayList<>();
                         fimSemana = new ArrayList<>();

                         for(int b =0; b < cine.length(); b++){
                             cinemas.add(String.valueOf(cine.get(b)));
                         }

                         for(int b =0; b < sem.length(); b++){
                             semana.add(String.valueOf(sem.get(b)));
                         }

                         for(int b =0; b < fim.length(); b++){
                             fimSemana.add(String.valueOf(fim.get(b)));
                         }

                         CinemaBean bean = new CinemaBean(cinemas,
                                 cinema.getString("filme"),
                                 cinema.getString("capa"),
                                 cinema.getString("duração"),
                                 cinema.getString("sinopse"),
                                 cinema.getInt("classificacao"),
                                 cinema.getString("genero"),
                                 cinema.getString("elenco"),
                                 cinema.getString("diretor"),
                                 semana,
                                 fimSemana);

                         list.add(bean);
                     }
                     adapter.notifyDataSetChanged();
                     hideLoad(list);
                 }catch (JSONException e){
                     Log.d("fer","error JSON \n"+e.toString());
                 }
             }
        }
    }


    @Override
    public void onClickLister(View view, int posicao) {
        CinemaBean bean = list.get(posicao);

        Intent intent = new Intent(getActivity(), DetalheActivity.class);
        intent.putExtra("cinema",bean);
        intent.putExtra("tipo",100);
        startActivity(intent);
    }
}
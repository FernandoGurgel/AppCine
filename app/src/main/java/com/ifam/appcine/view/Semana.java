package com.ifam.appcine.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifam.appcine.R;
import com.ifam.appcine.controller.ListaAdapter;
import com.ifam.appcine.model.CinemaBean;
import com.ifam.appcine.model.CinemaDao;

import java.util.List;


public class Semana extends Fragment {

    //private List<CinemaBean> beanList;

    public Semana() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CinemaDao dao = new CinemaDao();

        //beanList = dao.carregaFilmes();
        criaAdapter(dao.carregaFilmes());
    }

    private void criaAdapter(List<CinemaBean> cinemaBeen) {
        ListaAdapter adapter = new ListaAdapter(getContext(),cinemaBeen);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_semana,container,false);
    }
}
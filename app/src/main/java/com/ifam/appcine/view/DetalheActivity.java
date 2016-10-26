package com.ifam.appcine.view;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.ifam.appcine.R;
import com.ifam.appcine.controller.AdapterDetalhe;
import com.ifam.appcine.model.CinemaBean;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;
import java.util.ArrayList;

public class DetalheActivity extends AppCompatActivity {

    CinemaBean bean;
    int a = 0;
    private Toolbar toolbar;
    private TextView duracao,diretor, descricao;
    private ImageView capa;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        bean = (CinemaBean) getIntent().getSerializableExtra("cinema");

        a = getIntent().getIntExtra("tipo",1);
        Log.d("fere",a+"");

        toolbar = (Toolbar) findViewById(R.id.toobar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        duracao = (TextView) findViewById(R.id.txtduracao);
        diretor = (TextView) findViewById(R.id.txtdiretor);
        descricao = (TextView) findViewById(R.id.txtdescricao);
        capa = (ImageView) findViewById(R.id.treiler);
        recyclerView = (RecyclerView) findViewById(R.id.horario);

        recuperaLista();
        
    }

    private void recuperaLista() {




        duracao.setText(bean.getDuracao());
        diretor.setText(bean.getDiretor());
        descricao.setText(bean.getSinopse());

        Ocean.glide(this)
                .load(bean.getCapa())
                .build(GlideRequest.BITMAP)
                .into(capa);
        
        criaAdapter(bean,a);
    }

    private void criaAdapter(CinemaBean bean, int a) {

        ArrayList<String> cinema = new ArrayList<>();
        ArrayList<String> horario = new ArrayList<>();

        for(int b = 0; b < bean.getCinema().size(); b++){
            cinema.add(bean.getCinemaIndex(b));
            if(a == 100){
                horario.add(bean.getSemanaIndex(b));
            }else{
                horario.add(bean.getFimIndex(b));
            }

        }

        AdapterDetalhe adapterDetalhe = new AdapterDetalhe(this,cinema,horario);
        recyclerView.setAdapter(adapterDetalhe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}

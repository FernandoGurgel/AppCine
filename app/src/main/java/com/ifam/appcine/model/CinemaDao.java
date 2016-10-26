package com.ifam.appcine.model;

import android.util.Log;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.http.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 23/10/16.
 */

public class CinemaDao implements Request.RequestListener {

    public final static int SEMANA = 1;
    public final static int FIMSEMANA = 0;
    private List<CinemaBean> listaCinema;


    public List<CinemaBean> carregaFilmes(){
        Ocean.newRequest("https://gist.githubusercontent.com/FernandoGurgel/56f009a61aa9978210481df011f5588b/raw/656a855ee35c125530e1a7569789c4889745b13b/AppCinema", this).get().send();
        return getListaCinema();
    }


    public List<CinemaBean> getListaCinema() {
        return listaCinema;
    }

    public void setListaCinema(List<CinemaBean> listaCinema) {
        this.listaCinema = listaCinema;
    }

    @Override
    public void onRequestOk(String s, JSONObject jsonObject, int i) {

        ArrayList<String> semana;
        ArrayList<String> fimSemana;
        List<CinemaBean> lista = new ArrayList<CinemaBean>();

        if(i == Request.NENHUM_ERROR){

            if(s != null){
                try {

                    JSONObject object = new JSONObject(s);
                    JSONArray cine = object.getJSONArray("cinema");

                    for(int a = 0; a < cine.length(); a++){
                        semana = new ArrayList<>();
                        fimSemana = new ArrayList<String>();
                        JSONObject cinema = cine.getJSONObject(a);
                        JSONArray filmes = cinema.getJSONArray("filmes");

                        for(int b = 0; b < filmes.length(); b++){
                           JSONObject filme = filmes.getJSONObject(b);
                           JSONArray horarios = filme.getJSONArray("horarios");

                            for(int c = 0; c < horarios.length(); c++){
                                JSONObject horario = horarios.getJSONObject(c);
                                JSONArray semHoras = horario.getJSONArray("semana");

                                for(int d = 0; d < semHoras.length(); d++){
                                    semana.add(semHoras.getString(d));
                                }

                                JSONObject fimSemanas = horarios.getJSONObject(c);
                                JSONArray fimHoras = fimSemanas.getJSONArray("fimsemana");

                                for(int d = 0; d < fimHoras.length(); d++){
                                    fimSemana.add(fimHoras.getString(d));
                                }

//                                CinemaBean bean = new CinemaBean(cinema.getString("site"),
//                                       cinema.getString("nome"),filme.getString("filme"),
//                                        filme.getString("capa"),filme.getString("duração"),
//                                        filme.getString("sinopse"),filme.getInt("classificacao"),
//                                        filme.getString("genero"),filme.getString("elenco"),
//                                        filme.getString("diretor"),semana,fimSemana);

//                                lista.add(bean);
                            }
                        }
                    }
                    setListaCinema(lista);
                }catch (JSONException e){
                    Log.d("fer","error JSON \n"+e.toString());
                }
            }
        }

    }
}

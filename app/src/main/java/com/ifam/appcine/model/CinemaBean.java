package com.ifam.appcine.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fernando on 23/10/16.
 */

public class CinemaBean implements Serializable {

    private String site;
    private String cinema;
    private String filme;
    private String capa;
    private String duracao;
    private String sinopse;
    private String genero;
    private String elenco;
    private String diretor;
    private int classificao;
    private ArrayList<String> semana;
    private ArrayList<String> fim;


    public CinemaBean(String site, String cinema, String filme, String capa, String duracao,
                      String sinopse, int classificao, String genero, String elenco, String diretor,
                      ArrayList<String> semana, ArrayList<String> fim) {

        this.site = site;
        this.classificao = classificao;
        this.cinema = cinema;
        this.filme = filme;
        this.capa = capa;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.genero = genero;
        this.elenco = elenco;
        this.diretor = diretor;
        this.semana = semana;
        this.fim = fim;
    }

    public ArrayList<String> getSemana() {
        return semana;
    }

    public void setSemana(ArrayList<String> semana) {
        this.semana = semana;
    }

    public ArrayList<String> getFim() {
        return fim;
    }

    public void setFim(ArrayList<String> fim) {
        this.fim = fim;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Filipe
 */
public class Campeonato {
    int id;
    String nome;
    Date datInicio;
    Date datFim;
    Blob regulamento;

    public Campeonato() {
    }

    public Campeonato(int id, String nome, Date datInicio, Date datFim, Blob regulamento) {
        this.id = id;
        this.nome = nome;
        this.datInicio = datInicio;
        this.datFim = datFim;
        this.regulamento = regulamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatInicio() {
        return datInicio;
    }

    public void setDatInicio(Date datInicio) {
        this.datInicio = datInicio;
    }

    public Date getDatFim() {
        return datFim;
    }

    public void setDatFim(Date datFim) {
        this.datFim = datFim;
    }

    public Blob getRegulamento() {
        return regulamento;
    }

    public void setRegulamento(Blob regulamento) {
        this.regulamento = regulamento;
    }
    
    
}

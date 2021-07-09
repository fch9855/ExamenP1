package com.example.heroesapp;

import java.io.Serializable;
import java.util.HashMap;

public class SuperHeroe implements Serializable {
    String id;
    String nombre;
    String alterego;
    HashMap habilidades=new HashMap();

    public SuperHeroe(String id,String alterego,String nombre,HashMap habilidades) {
        this.id = id;
        this.alterego=alterego;
        this.nombre=nombre;
        this.habilidades=habilidades;
    }

    public SuperHeroe() {
    }

    @Override
    public String toString() {
        return "\nNombre del SuperHeroe: "+nombre + " AlterEgo: "+alterego;
    }
}
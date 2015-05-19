package com.project2.taes.farmacia.classes;

import java.util.ArrayList;

/**
 * Created by FranciscoJavier on 13/05/2015.
 */
public class Producto {
    private String Titulo="";
    private String Objetos="";
    private String Cantidad="";

    //Getters
    public String getTitulo(){
        return Titulo;
    }

    public String getObjetos(){
        return Objetos;
    }

    public String getCantidad(){
        return Cantidad;
    }


    //Setters
    public void setTitulo(String titulo){
        Titulo= titulo;
    }

    public void setObjetos(String objetos){
        Objetos=objetos;
    }

    public void setCantidad(String cantidad){
        Cantidad=cantidad;
    }

    public static ArrayList<Producto> getItems(){
        ArrayList<Producto> MiLista= new ArrayList<Producto>();


        //Creamos productos
        Producto producto1= new Producto();
        producto1.setTitulo("Aspirinas");
        producto1.setObjetos("Lapiz, Boligrafo y bloc");
        producto1.setCantidad("3");

        Producto producto2= new Producto();
        producto2.setTitulo("Paracetamol");
        producto2.setObjetos("Rotuladores, Taza y bloc");
        producto2.setCantidad("6");

        Producto producto3= new Producto();
        producto3.setTitulo("AfterSun Deliplus");
        producto3.setObjetos("Pelota, colchoneta y bloc");
        producto3.setCantidad("4");

        //Anyadimos productos
        MiLista.add(producto1);
        MiLista.add(producto2);
        MiLista.add(producto3);

        return MiLista;
    }
}
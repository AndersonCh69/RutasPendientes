/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MATEO
 */
public class Ruta {

    //Atributos
    public String nombre;
    public String origen;
    public String destino;
    public List<Coordenada> puntos;
    public List<Segmento> segmentos;
    public Coordenada coordenadaInicial;
    public Coordenada coordenadaFinal;
    public double distancia;
    public double elevacion;  

    //Contructor de Ruta
    public Ruta(String nombre, String origen, String destino) {
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.puntos = new ArrayList<>();
        this.segmentos = new ArrayList<>();
    }
    //Constructor
    public Ruta(Coordenada coordenadaInicial, Coordenada coordenadaFinal, 
            double distancia, double elevacion) {
    this.coordenadaInicial = coordenadaInicial;
    this.coordenadaFinal = coordenadaFinal;
    this.distancia = distancia;
    this.elevacion = elevacion;
     this.nombre = "Ruta CSV";
    this.origen = String.format("(%.2f, %.2f)", 
            coordenadaInicial.latitud, coordenadaInicial.longitud);
    this.destino = String.format("(%.2f, %.2f)", 
            coordenadaFinal.latitud, coordenadaFinal.longitud);
    this.puntos = new ArrayList<>();
    this.segmentos = new ArrayList<>();
    
    this.puntos.add(coordenadaInicial);
    this.puntos.add(coordenadaFinal);
}

    //Metodos
    //Agregar un punto a la ruta
    public void agregarPunto(Coordenada punto) {
        if (punto != null) {
            puntos.add(punto);
        }
    }

    //Limpiar todos los puntos y segmentos de la ruta
    public void limpiar() {
        puntos.clear();
        segmentos.clear();
    }

    //Obtener el numero de puntos
    public int getNumPuntos() {
        return puntos.size();
    }

    //Obtener el número de segmentos
    public int getNumSegmentos() {
        return segmentos.size();
    }
    
    //Obtiene la distancia total de la ruta
    
    public double getDistanciaTotal() {
        double total = 0;
        for (Segmento seg : segmentos) {
            total += seg.distancia;
        }
        return total;
    }
    
    //Muestra la informacion de la ruta
    
    public String mostrarRuta() {
        return origen + " → " + destino + " (" + getDistanciaTotal() + " m, " + 
               puntos.size() + " puntos)";
    }
    
    @Override
    public String toString() {
        return String.format("Ruta: %s (%s→%s, %d puntos, %d segmentos)", 
            nombre, origen, destino, puntos.size(), segmentos.size());
    }
    public double calcularPendiente() {
    if (distancia == 0) {
        return 0;
    }
    // Pendiente = (elevación en metros / distancia en metros) * 100
    double distanciaMetros = distancia * 1000;
    return (elevacion / distanciaMetros) * 100;
    }
}

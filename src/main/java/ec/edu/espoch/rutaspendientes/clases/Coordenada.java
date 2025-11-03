/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.clases;

/**
 *
 * @author MATEO
 */
public class Coordenada 
{
    //Atributos
    public double latitud;
    public double longitud;
    public double elevacion;

    //Constructor de Coordenada
    public Coordenada(double latitud, double longitud, double elevacion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.elevacion = elevacion;
    }
    //Constructor
    public Coordenada(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.elevacion = 0; // elevación por defecto
    }
    
    
    //Metodos
    
    //Mostrar coordenada formateada
    
    public String mostrarCoordenada() {
        return String.format("Coordenada(lat=%.4f, lon=%.4f, elev=%.1f)", 
            latitud, longitud, elevacion);
    }
    
  
    @Override
    public String toString() {
        return mostrarCoordenada();
    }
    
}

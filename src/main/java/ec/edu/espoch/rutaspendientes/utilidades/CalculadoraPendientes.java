/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.utilidades;

import ec.edu.espoch.rutaspendientes.clases.Coordenada;
import ec.edu.espoch.rutaspendientes.clases.Segmento;
//import ec.edu.espoch.rutaspendientes.enumeraciones.ClasificacionPendiente;
/**
 *
 * @author MATEO
 */
public class CalculadoraPendientes 
{
    //Atributos
    
    public static final double RADIO_TIERRA = 6371.0;
    
    //Metodos
    
    //calcula la distancia entre dos coordenadas
    public static double calcularDistancia(Coordenada c1, Coordenada c2) {
        double lat1 = Math.toRadians(c1.latitud);
        double lat2 = Math.toRadians(c2.latitud);
        double deltaLat = Math.toRadians(c2.latitud - c1.latitud);
        double deltaLon = Math.toRadians(c2.longitud - c1.longitud);
        
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA * c * 1000; // Retorna en metros
    }
    
    //Calcular la pendiente en porcentaje
    
    public static double calcularPendientePortcentaje(double deltaElevacion, double distancia) {
        if (distancia == 0) return 0.0;
        return (deltaElevacion / distancia) * 100.0;
    }
    //Calcular pendiente en grados
    
    public static double calcularPendienteGrados(double deltaElevacion, double distancia) {
        if (distancia == 0) return 0.0;
        return Math.toDegrees(Math.atan(deltaElevacion / distancia));
    }
    
    //Clasificacion de una pendiente usando la enumeracion 
    public static String clasificarPendiente(double pendientePortcentaje) {
        double pendienteAbs = Math.abs(pendientePortcentaje);
        
        if (pendienteAbs < 2) {
            return "MINIMA";
        } else if (pendienteAbs < 10) {
            return "NORMAL";
        } else {
            return "MAXIMA";
        }
    }
    //Calcular la pendiente de un segmento 
     public static Segmento calcularSegmento(Coordenada inicio, Coordenada fin) {
        Segmento seg = new Segmento(inicio, fin);
        
        double distancia = calcularDistancia(inicio, fin);
        double deltaZ = fin.elevacion - inicio.elevacion;
        
        double pendienteP = calcularPendientePortcentaje(deltaZ, distancia);
        double pendienteG = calcularPendienteGrados(deltaZ, distancia);
        String clasificacion = clasificarPendiente(pendienteP);
        
        seg.distancia = distancia;
        seg.pendientePortcentaje = pendienteP;
        seg.pendienteGrados = pendienteG;
        seg.clasificacion = clasificacion;
        
        return seg;
    }
}

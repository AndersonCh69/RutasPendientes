/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.clases;

/**
 *
 * @author MATEO
 */
public class Segmento {
    
    //Atributos de la clase
    public Coordenada inicio;
    public Coordenada fin;
    public double pendientePortcentaje;
    public double pendienteGrados;
    public double distancia;
    public String clasificacion;
    
    //Contructor de Segmento
    
    public Segmento(Coordenada inicio, Coordenada fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.pendientePortcentaje = 0.0;
        this.pendienteGrados = 0.0;
        this.distancia = 0.0;
        this.clasificacion = "NORMAL";
    }
    
    //Metodos de la clase
    
    //mostrar informacion del segmento
    
    public String mostrarSegmento() {
        return String.format("Segmento: dist=%.2f m, pend=%.2f%%, clase=%s", 
            distancia, pendientePortcentaje, clasificacion);
    }
    
    @Override
    public String toString() {
        return mostrarSegmento();
    }
    
    
}

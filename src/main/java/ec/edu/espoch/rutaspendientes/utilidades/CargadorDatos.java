/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.utilidades;
import ec.edu.espoch.rutaspendientes.clases.Coordenada;
import ec.edu.espoch.rutaspendientes.clases.Ruta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author MATEO
 */
public class CargadorDatos 
{
    //Metodos
    //cargar un ruta desde archivo CSV
    
     public static Ruta cargarDesdeCSV(String rutaArchivo, String nombre, String origen, String destino) throws IOException {
        
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            throw new IOException("Archivo no encontrado: " + rutaArchivo);
        }
        
        Ruta ruta = new Ruta(nombre, origen, destino);
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numLinea = 0;
            
            while ((linea = br.readLine()) != null) {
                numLinea++;
                
                if (numLinea == 1 && linea.toLowerCase().contains("latitud")) {
                    continue;
                }
                
                try {
                    String[] partes = linea.split(",");
                    if (partes.length < 3) {
                        System.err.println("Línea " + numLinea + " incompleta");
                        continue;
                    }
                    
                    double latitud = Double.parseDouble(partes[0].trim());
                    double longitud = Double.parseDouble(partes[1].trim());
                    double elevacion = Double.parseDouble(partes[2].trim());
                    
                    if (validarCoordenada(latitud, longitud, elevacion)) {
                        Coordenada coord = new Coordenada(latitud, longitud, elevacion);
                        ruta.agregarPunto(coord);
                    } else {
                        System.err.println("Coordenada inválida en línea " + numLinea);
                    }
                    
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear línea " + numLinea + ": " + linea);
                }
            }
        }
        
        return ruta;
    }
     // Validar que una coordenada tenga valores correctos
     public static boolean validarCoordenada(double latitud, double longitud, double elevacion) {
        return latitud >= -90 && latitud <= 90 &&
               longitud >= -180 && longitud <= 180 &&
               elevacion >= 0;
    }
     
}

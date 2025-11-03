/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.controlador;

import ec.edu.espoch.rutaspendientes.clases.Coordenada;
import ec.edu.espoch.rutaspendientes.clases.Ruta;
import ec.edu.espoch.rutaspendientes.clases.Segmento;
import ec.edu.espoch.rutaspendientes.utilidades.CalculadoraPendientes;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MATEO
 */
public class RutaControlador {
    //Atributos

    private Ruta rutaActual;

    //Constructor 
    public RutaControlador() {
        this.rutaActual = null;
    }
    //Metodos
    //establece ruta actual

    public void setRutaActual(Ruta ruta) {
        this.rutaActual = ruta;
    }
    //obtiene la ruta actual 

    public Ruta getRutaActual() {
        return rutaActual;
    }
    //Procesar todos los segmentos de la ruta actual

    public void procesarPendientes() throws Exception {
        if (rutaActual == null || rutaActual.puntos.isEmpty()) {
            throw new Exception("No hay ruta cargada");
        }

        rutaActual.segmentos.clear();

        for (int i = 0; i < rutaActual.puntos.size() - 1; i++) {
            Coordenada inicio = rutaActual.puntos.get(i);
            Coordenada fin = rutaActual.puntos.get(i + 1);

            Segmento seg = CalculadoraPendientes.calcularSegmento(inicio, fin);
            rutaActual.segmentos.add(seg);
        }
    }
    //Obtener informacion ruta 

    public String obtenerInfoRuta() {
        if (rutaActual == null) {
            return "No hay ruta cargada";
        }
        return rutaActual.mostrarRuta();
    }

    public List<Ruta> cargarRutasDesdeArchivo(String rutaArchivo) throws IOException {
    List<Ruta> rutas = new ArrayList<>();
    
    System.out.println("Cargando archivo: " + rutaArchivo);
    
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        int numeroLinea = 0;
        boolean primerLinea = true;
        String separador = ",";
        
        while ((linea = br.readLine()) != null) {
            numeroLinea++;
            linea = linea.trim();
            
            // Saltar líneas vacías
            if (linea.isEmpty() || linea.matches("^[,;\\s\"]*$")) {
                continue;
            }
            
            if (primerLinea) {
                primerLinea = false;
                // Detectar el separador
                if (linea.contains(";")) {
                    separador = ";";
                    System.out.println("Detectado separador: punto y coma (;)");
                } else {
                    separador = ",";
                    System.out.println("Detectado separador: coma (,)");
                }
                
                // Saltar si es encabezado
                if (!linea.matches(".*\\d.*")) {
                    System.out.println("Saltando encabezado: " + linea);
                    continue;
                }
            }
            
            // Dividir por el separador
            String[] datos = linea.split(separador);
            
            // Limpiar comillas y espacios de cada dato
            for (int i = 0; i < datos.length; i++) {
                datos[i] = datos[i].trim().replace("\"", "");
            }
            
            // Verificar que tengamos al menos 6 columnas con datos
            if (datos.length < 6) {
                System.err.println("Linea " + numeroLinea + " formato incorrecto (se esperan 6 columnas)");
                continue;
            }
            
            // Verificar que las primeras 6 columnas no estén vacías
            boolean datosValidos = true;
            for (int i = 0; i < 6; i++) {
                if (datos[i].isEmpty()) {
                    datosValidos = false;
                    break;
                }
            }
            
            if (!datosValidos) {
                System.err.println("Linea " + numeroLinea + " contiene datos vacíos");
                continue;
            }
            
            try {
                // Reemplazar comas por puntos en los números
                double latInicial = Double.parseDouble(datos[0].replace(",", "."));
                double lonInicial = Double.parseDouble(datos[1].replace(",", "."));
                double latFinal = Double.parseDouble(datos[2].replace(",", "."));
                double lonFinal = Double.parseDouble(datos[3].replace(",", "."));
                double distancia = Double.parseDouble(datos[4].replace(",", "."));
                double elevacion = Double.parseDouble(datos[5].replace(",", "."));
                
                Coordenada inicial = new Coordenada(latInicial, lonInicial);
                Coordenada finalCoord = new Coordenada(latFinal, lonFinal);
                
                Ruta ruta = new Ruta(inicial, finalCoord, distancia, elevacion);
                rutas.add(ruta);
                
                System.out.println("✓ Ruta " + rutas.size() + " cargada correctamente");
                
            } catch (NumberFormatException e) {
                System.err.println("Error en linea " + numeroLinea + ": " + e.getMessage());
                System.err.println("Contenido: " + linea);
            }
        }
    }
    
    System.out.println("========================================");
    System.out.println("Total de rutas cargadas: " + rutas.size());
    System.out.println("========================================");
    return rutas;
    }
}

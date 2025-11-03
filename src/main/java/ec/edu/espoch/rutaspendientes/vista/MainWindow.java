/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.rutaspendientes.vista;
import ec.edu.espoch.rutaspendientes.controlador.RutaControlador;
import ec.edu.espoch.rutaspendientes.clases.Ruta;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;

/**
 *
 * @author MATEO
 */
public class MainWindow extends Application 
{
    //Atributos 
    private TextArea areaResultados;
    private Label labelArchivo;
    private RutaControlador controlador;
    private File archivoSeleccionado;
    
    @Override
    //Metodos
    
    public void start(Stage primaryStage) {
        controlador = new RutaControlador();
        //Configurar ventana
        primaryStage.setTitle("RutasPendientes - Analizador v0.3");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        // Layout principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        //Titulo
        Label titulo = new Label("📊 Analizador de Pendientes de Rutas");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
         // Información del archivo
         labelArchivo = new Label("Ningun archivo seleccionado");
        labelArchivo.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");
        //Boton seleccionar archivo
        
        Button btnSeleccionar = new Button("📁 Seleccionar CSV");
        btnSeleccionar.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        btnSeleccionar.setOnAction(e -> seleccionarArchivo());
        
        // Botón para cargar y analizar
        Button btnCargar = new Button(" Cargar y Analizar");
        btnCargar.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                          "-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnCargar.setOnAction(e -> cargarYAnalizar());

        // Área de resultados
        areaResultados = new TextArea();
        areaResultados.setEditable(false);
        areaResultados.setWrapText(true);
        areaResultados.setPrefHeight(250);
        areaResultados.setText(
            "INSTRUCCIONES:\n\n" +
            "1. Haz clic en 'Seleccionar CSV'\n" +
            "2. Elige tu archivo de rutas\n" +
            "3. Haz clic en 'Cargar y Analizar'\n\n" +
            "Formato esperado del CSV:\n" +
            "lat_inicial, lon_inicial, lat_final, lon_final, distancia_km, elevacion_m\n\n" +
            "Ejemplo:\n" +
            "-0.9500, -78.6167, -0.9450, -78.6100, 1.5, 85.0"
        );
        // Agregar componentes
        root.getChildren().addAll(
            titulo,
            new Separator(),
            labelArchivo,
            btnSeleccionar,
            btnCargar,
            new Label("Resultados:"),
            areaResultados
        );
         // Crear escena y mostrar
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        System.out.println("Aplicacion iniciada correctamente");
        }
    private void seleccionarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo CSV");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos CSV", "*.csv")
        );
         archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            labelArchivo.setText("✅ " + archivoSeleccionado.getName());
            labelArchivo.setStyle("-fx-font-size: 12px; -fx-text-fill: #2ecc71;");
            areaResultados.setText(
                "Archivo seleccionado:\n" +
                archivoSeleccionado.getAbsolutePath() + "\n\n" +
                "Tamaño: " + archivoSeleccionado.length() + " bytes\n\n" +
                "Presiona 'Cargar y Analizar' para continuar."
            );
        }
    
        }
    //Cargar el archivo y realizar el análisis
    
    private void cargarYAnalizar() {
        if (archivoSeleccionado == null) {
            mostrarAlerta("Error", "Por favor selecciona un archivo primero");
            return;
        }

        try {
            areaResultados.setText("⏳ Cargando archivo...\n\n");

            // Cargar rutas
            List<Ruta> rutas = controlador.cargarRutasDesdeArchivo(
                archivoSeleccionado.getAbsolutePath()
            );

            if (rutas.isEmpty()) {
                areaResultados.setText("No se encontraron rutas válidas en el archivo");
                return;
            }

            // Analizar y mostrar resultados
            StringBuilder resultado = new StringBuilder();
            resultado.append("✅ ANÁLISIS COMPLETADO\n");
            resultado.append("═══════════════════════════════════\n\n");
            resultado.append("📊 ESTADÍSTICAS:\n");
            resultado.append("Total de rutas: ").append(rutas.size()).append("\n\n");

            // Calcular estadísticas
            double pendienteMax = Double.MIN_VALUE;
            double pendienteMin = Double.MAX_VALUE;
            double pendienteSum = 0;

            for (Ruta ruta : rutas) {
                double pendiente = ruta.calcularPendiente();
                pendienteMax = Math.max(pendienteMax, pendiente);
                pendienteMin = Math.min(pendienteMin, pendiente);
                pendienteSum += pendiente;
            }

            double pendientePromedio = pendienteSum / rutas.size();

            resultado.append(String.format("Pendiente máxima: %.2f%%\n", pendienteMax));
            resultado.append(String.format("Pendiente mínima: %.2f%%\n", pendienteMin));
            resultado.append(String.format("Pendiente promedio: %.2f%%\n\n", pendientePromedio));

            // Mostrar primeras 5 rutas como ejemplo
            resultado.append("📋 MUESTRA (primeras 5 rutas):\n");
            resultado.append("───────────────────────────────────\n");
            
            int limite = Math.min(5, rutas.size());
            for (int i = 0; i < limite; i++) {
                Ruta ruta = rutas.get(i);
                resultado.append(String.format(
                    "%d. Pendiente: %.2f%% | Distancia: %.2f km\n",
                    i + 1,
                    ruta.calcularPendiente(),
                    ruta.distancia
                ));
            }

            areaResultados.setText(resultado.toString());
            
            System.out.println("Análisis completado: " + rutas.size() + " rutas");

        } catch (Exception e) {
            areaResultados.setText(
                "❌ ERROR AL CARGAR ARCHIVO:\n\n" +
                e.getMessage() + "\n\n" +
                "Verifica que el formato del CSV sea correcto."
            );
            e.printStackTrace();
        }
    }

    
    //Muestra una alerta al usuario
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
    


}
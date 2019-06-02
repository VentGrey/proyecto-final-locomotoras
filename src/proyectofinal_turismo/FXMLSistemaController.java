package proyectofinal_turismo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import misclases.Maquinistas;
import misclases.Trenes;
import misclases.Viajes;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLSistemaController implements Initializable {

    ArrayList<Maquinistas> Guias = new ArrayList<Maquinistas>();

    ArrayList<Trenes> Recorridos = new ArrayList<Trenes>();

    ArrayList<Viajes> Reservaciones = new ArrayList<Viajes>();

    @FXML
    private MenuItem btnAltaGuia;
    @FXML
    private MenuItem btnAltaRecorrido;
    @FXML
    private MenuItem btnModGuia;
    @FXML
    private MenuItem btnModRecorrido;
    @FXML
    private MenuItem btnBuscarGuia;
    @FXML
    private MenuItem btnBuscarRecorrido;
    @FXML
    private MenuItem btnGuardarRecorrido;
    @FXML
    private BorderPane Border;
    @FXML
    private MenuItem btn_GuardarGuias;
    @FXML
    private Menu btnReservacion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //GUIAS
    @FXML
    private void Alta_Guia() {
        this.Border.setCenter(null);
        FXMLLoader lectorAlta = new FXMLLoader(getClass().getResource("FXMLAlta_Guia.fxml"));
        Pane paneAltaGuia = null;
        try {
            paneAltaGuia = (Pane) lectorAlta.load();
        } catch (IOException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No se pudo abrir");
            msgE.show();
        }
        FXMLAlta_MaquinistaController altaControl = lectorAlta.getController();
        altaControl.setMaquinistas(Guias);
        this.Border.setCenter(paneAltaGuia);

    }

    @FXML
    private void Mod_Guia() {
        try {
            this.Border.setCenter(null);
            FXMLLoader cargarMod_Guia = new FXMLLoader(getClass().getResource("FXMLModificar_Guia.fxml"));
            Pane pane = null;
            try {
                pane = (Pane) cargarMod_Guia.load();
            } catch (IOException ex) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No se pudo abrir");
                msgE.show();
            }
            FXMLModificar_MaquinistaController modGuia = cargarMod_Guia.getController();
            modGuia.setGuias(Guias);
            this.Border.setCenter(pane);
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }

    }

    @FXML
    private void Buscar_Guia() {
        try {
            this.Border.setCenter(null);
            FXMLLoader cargar_BuscarGuia = new FXMLLoader(getClass().getResource("FXMLBuscar_Guia.fxml"));
            AnchorPane pane = null;
            try {
                pane = (AnchorPane) cargar_BuscarGuia.load();
            } catch (IOException ex) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No se pudo abrir");
                msgE.show();
            }
            FXMLBuscar_MaquinistaController BuscarGuia = cargar_BuscarGuia.getController();
            BuscarGuia.setGuias(Guias);
            this.Border.setCenter(pane);
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }

    }

    @FXML
    private void guardar_Guias() {
        try {
            try {

                FileWriter fw = new FileWriter("GuiasTuristicos.vsr");
                PrintWriter escritor = new PrintWriter(fw);
                escritor.println(Guias.size());
                for (int i = 0; i < Guias.size(); i++) {
                    Maquinistas temp = Guias.get(i);
                    escritor.println(temp.getNombre());
                    escritor.println(temp.getApPaterno());
                    escritor.println(temp.getCedula());
                    escritor.println(temp.getAntiguedad());
                }
                escritor.close();
                Alert msgE = new Alert(Alert.AlertType.INFORMATION);
                msgE.setTitle("Guardado");
                msgE.setHeaderText("Se guardo correctamente");
                msgE.show();
            } catch (IOException e) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No es posible guardar");
                msgE.show();
            }
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }

    }

    @FXML
    private void abrir_Guias() {
        try {
            FileReader fr = new FileReader("GuiasTuristicos.vsr");
            BufferedReader lector = new BufferedReader(fr);
            int tam = Integer.valueOf(lector.readLine());
            for (int i = 0; i < tam; i++) {
                String Nombre = lector.readLine();
                String ApPaterno = lector.readLine();
                int Cedula = Integer.valueOf(lector.readLine());
                String Especialidad = lector.readLine();
                int Antiguedad = Integer.valueOf(lector.readLine());
                Maquinistas temp = new Maquinistas(Nombre, ApPaterno, Cedula, Antiguedad);
                this.Guias.add(temp);
                //habliliar_Guias();
            }
            lector.close();
            Alert msgE = new Alert(Alert.AlertType.INFORMATION);
            msgE.setTitle("Abrir");
            msgE.setHeaderText("Se abrió correctamente");
            msgE.show();

        } catch (FileNotFoundException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No es posible abrir");
            msgE.show();
        } catch (IOException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("Error fisico en su unidad");
            msgE.show();
        }

    }

    public void habliliar_Guias() {
        btnModGuia.setDisable(false);
        btnBuscarGuia.setDisable(false);
        btn_GuardarGuias.setDisable(false);
    }

    public void deshablilitar_guias() {
        btnModGuia.setDisable(true);
        btnBuscarGuia.setDisable(true);
        btn_GuardarGuias.setDisable(true);
    }

    //RECORRIDOS
    @FXML
    private void Alta_Recorrido() {
        this.Border.setCenter(null);
        FXMLLoader lector = new FXMLLoader(getClass().getResource("FXMLAlta_Recorrido.fxml"));
        Pane paneAltaRecorrido = null;
        try {
            paneAltaRecorrido = (Pane) lector.load();
        } catch (IOException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No se pudo abrir");
            msgE.show();
        }
        FXMLAlta_TrenesController altaRecorrido = lector.getController();
        altaRecorrido.setRecorridos(Recorridos);
        this.Border.setCenter(paneAltaRecorrido);
    }

    @FXML
    private void Mod_Recorrido() {
        try {
            this.Border.setCenter(null);
            FXMLLoader cargarMod_Reco = new FXMLLoader(getClass().getResource("FXMLMod_Recorrido.fxml"));
            Pane pane = null;
            try {
                pane = (Pane) cargarMod_Reco.load();
            } catch (IOException ex) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No se pudo abrir");
                msgE.show();
            }
            FXMLMod_TrenController modGuia = cargarMod_Reco.getController();
            modGuia.setRecorridos(Recorridos);
            this.Border.setCenter(pane);
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }
    }

    @FXML
    private void Buscar_Recorrido() {
        try {
            this.Border.setCenter(null);
            FXMLLoader cargar_BuscarRecorrido = new FXMLLoader(getClass().getResource("FXMLBuscar_Recorrido.fxml"));
            AnchorPane pane = null;
            try {
                pane = (AnchorPane) cargar_BuscarRecorrido.load();
            } catch (IOException ex) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No se pudo abrir");
                msgE.show();
            }
            FXMLBuscar_TrenController BuscarRecorrido = cargar_BuscarRecorrido.getController();
            BuscarRecorrido.setRecorridos(Recorridos);
            this.Border.setCenter(pane);
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }
    }

    @FXML
    private void Guardar_Recorrido() {
        
        try {
            try {

                FileWriter fw = new FileWriter("RecorridosTuristicos.vsr");
                PrintWriter escritor = new PrintWriter(fw);

                escritor.println(Recorridos.size());
                for (int i = 0; i < Recorridos.size(); i++) {
                    Trenes temp = Recorridos.get(i);
                    escritor.println(temp.getNombreRecorrido());
                    escritor.println(temp.getNoVagones());
                    escritor.println(temp.getCiudad());
                    escritor.println(temp.getIDMaquina());
                }
                escritor.close();
                Alert msgE = new Alert(Alert.AlertType.INFORMATION);
                msgE.setTitle("Guardado");
                msgE.setHeaderText("Se guardo correctamente");
                msgE.show();
            } catch (IOException e) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No es posible guardar");
                msgE.show();
            }
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }

    }

    @FXML
    private void Abrir_Recorrido() {
        try {
            FileReader fr = new FileReader("RecorridosTuristicos.vsr");
            BufferedReader lector = new BufferedReader(fr);
            int tam = Integer.valueOf(lector.readLine());
            for (int i = 0; i < tam; i++) {
                String nombre = lector.readLine();
                int max = Integer.valueOf(lector.readLine());
                int min = Integer.valueOf(lector.readLine());
                String origen = lector.readLine();
                String destino = lector.readLine();
                Trenes temp = new Trenes(nombre, max, origen, destino);
                this.Recorridos.add(temp);
                //habliliar_Recorridos();
            }
            lector.close();
            Alert msgE = new Alert(Alert.AlertType.INFORMATION);
            msgE.setTitle("Abrir");
            msgE.setHeaderText("Se abrió correctamente");
            msgE.show();

        } catch (FileNotFoundException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No es posible abrir");
            msgE.show();
        } catch (IOException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("Error fisico en su unidad");
            msgE.show();
        }
    }

    public void habliliar_Recorridos() {
        btnModRecorrido.setDisable(false);
        btnBuscarRecorrido.setDisable(false);
        btnGuardarRecorrido.setDisable(false);
        btnReservacion.setDisable(false);
    }

    public void deshablilitar_Recorridos() {
        btnModRecorrido.setDisable(true);
        btnBuscarRecorrido.setDisable(true);
        btnGuardarRecorrido.setDisable(true);
        btnReservacion.setDisable(true);
    }

    //RESERVACIONES
    @FXML
    private void reservaciones() {
        this.Border.setCenter(null);
        FXMLLoader lectorReservaciones = new FXMLLoader(getClass().getResource("FXMLReservaciones.fxml"));
        Pane pane = null;
        try {
            pane = (Pane) lectorReservaciones.load();
        } catch (IOException e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No se pudo abrir");
            msgE.show();
        }
        FXMLReservacionesController Control = lectorReservaciones.getController();
        Control.setReservaciones(Reservaciones);
        Control.setGuias(Guias);
        Control.setRecorridos(Recorridos);
        this.Border.setCenter(pane);

    }

    @FXML
    private void Archivo_Reservaciones() {
        try {
            try {
                Calendar fecha = new GregorianCalendar();
                int ano = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                int hora = fecha.get(Calendar.HOUR_OF_DAY);
                int minuto = fecha.get(Calendar.MINUTE);

                FileWriter fw = new FileWriter("Reservaciones" + dia + mes + ano + "_" + hora + ":" + minuto + ".txt");
                PrintWriter escritor = new PrintWriter(fw);

                escritor.println("Total Reservaciones: " + Reservaciones.size());
                for (int i = 0; i < Reservaciones.size(); i++) {
                    Viajes temp = Reservaciones.get(i);
                    escritor.println("*****Reservación " + (i + 1) + "*****");
                    escritor.println("Guia: " + temp.getNombreGuia());
                    escritor.println("Recorrido: " + temp.getNombreRecorrido());
                    escritor.println("Asistentes: " + temp.getAsistentes());
                    escritor.println("Fecha: " + temp.getDia() + " / " + temp.getMes() + " / " + temp.getAnio());
                    escritor.println();
                }
                escritor.close();
                Alert msgE = new Alert(Alert.AlertType.INFORMATION);
                msgE.setTitle("Guardado");
                msgE.setHeaderText("Se guardo correctamente");
                msgE.show();
            } catch (IOException e) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No es posible guardar");
                msgE.show();
            }
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("No hay registros");
            msgE.setHeaderText("Por favor abra un archivo o haga nuevos registros");
            msgE.show();
        }
    }

    public void hablilitarReservaciones() {
        btnReservacion.setDisable(false);
    }

    public void deshablilitar_Reservaciones() {
        btnReservacion.setDisable(true);
    }

}

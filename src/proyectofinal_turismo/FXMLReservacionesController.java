package proyectofinal_turismo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import misclases.Maquinistas;
import misclases.Trenes;
import misclases.Viajes;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLReservacionesController implements Initializable {
    private ArrayList <Maquinistas> guias = null;
    private ArrayList <Trenes> recorridos = null;
    
    
    private ArrayList <Viajes> reservaciones = null;
    @FXML
    private ComboBox<String> cmbGuia;
    @FXML
    private ComboBox<String> cmbRecorridos;
    @FXML
    private TextField txtAsistentes;
    @FXML
    private ComboBox<Integer> txtDia;
    @FXML
    private ComboBox<String> txtMes;
    @FXML
    private TextField txtAnio;
    @FXML
    private AnchorPane Pane;

    
    public void setGuias(ArrayList<Maquinistas> guias) {
        this.guias = guias;
        if(guias.isEmpty()==true){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("No hay Registros");
            msg.setHeaderText("Por favor cree nuevos registros de guias"
                    + "\n o abra un archivo");
            msg.showAndWait();
            this.Pane.setVisible(false);
        }
        ObservableList <String> nombresGuias = FXCollections.observableArrayList();
        
        for (int i = 0; i < guias.size(); i++) {
            Maquinistas temp = guias.get(i);
            nombresGuias.add(temp.getNombre() +" "+temp.getApPaterno());
            cmbGuia.setItems(nombresGuias);    
            
        }
        txtAnio.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }
            }
        });
        txtAsistentes.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }
            }
        });
    }

    public void setRecorridos(ArrayList<Trenes> recorridos) {
        this.recorridos = recorridos;
        if(recorridos.isEmpty()==true){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("No hay Registros");
            msg.setHeaderText("Por favor cree nuevos registros de recorridos"
                    + "\n o abra un archivo");
            msg.showAndWait();
            this.Pane.setVisible(false);
        }
        ObservableList <String> nombresRecorridos = FXCollections.observableArrayList();
        for (int i  = 0; i < recorridos.size(); i++) {
            Trenes temp = recorridos.get(i);
            nombresRecorridos.add(temp.getNombreRecorrido());
            cmbRecorridos.setItems(nombresRecorridos);
        }
        
    }

    public void setReservaciones(ArrayList<Viajes> reservaciones) {
        this.reservaciones = reservaciones;
    }
    
    ObservableList<Integer> Dias = FXCollections.observableArrayList(
    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,29,30,31
    );
    ObservableList<String> Meses = FXCollections.observableArrayList(
    "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
            "Septiembre","Octubre","Noviembre","Diciembre"
    );

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtDia.setItems(Dias);
        txtMes.setItems(Meses);
    }   
    
    
    @FXML
    private void aceptar(){
        try {
            String guia = cmbGuia.getValue();
            String recorrido = cmbRecorridos.getValue();
            int asistentes = Integer.valueOf(txtAsistentes.getText());
            int dia = txtDia.getValue();
            String mes = txtMes.getValue();
            int anio = Integer.valueOf(txtAnio.getText());
            
            Viajes temp = new Viajes(guia, recorrido, asistentes, dia, mes, anio);
            this.reservaciones.add(temp);
            
            txtAsistentes.setText("");
            txtAnio.setText("");
            txtDia.getSelectionModel().clearSelection();
            txtMes.getSelectionModel().clearSelection();
            cmbGuia.getSelectionModel().clearSelection();
            cmbRecorridos.getSelectionModel().clearSelection();
            
            
            
            Alert msgC = new Alert(Alert.AlertType.INFORMATION);
            msgC.setTitle("Reservacion");
            msgC.setHeaderText("Se creo correctamente la reservación");
            msgC.show();
            
            try {
                
                FileWriter fw = new FileWriter("Reservacion" + temp.getNombreGuia()+"_"+ temp.getNombreRecorrido() +  ".txt");
                PrintWriter escritor = new PrintWriter(fw);
                    escritor.println("*****Reservación*****");
                    escritor.println("Guia: " +  temp.getNombreGuia());
                    escritor.println("Recorrido: " + temp.getNombreRecorrido());
                    escritor.println("Asistentes: " + temp.getAsistentes());
                    escritor.println("Fecha: " + temp.getDia() + " / " +temp.getMes() +" / "+ temp.getAnio());
                    escritor.println();
                    escritor.close();
            } catch (IOException e) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No es posible Guardar Recibo");
                msgE.show();
            }
            
            
            
        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("No se pudo agregar");
                msgE.show();
        }
    }
    
    @FXML
    private void cancelar(){
        this.Pane.setVisible(false);
    }
}

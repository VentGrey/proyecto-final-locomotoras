/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal_turismo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import misclases.Trenes;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLBuscar_TrenController implements Initializable {

    ArrayList<Trenes> recorridos = null;
    ArrayList<Trenes> TempNombre = new ArrayList<Trenes>();
    ArrayList<Trenes> TempOrigen = new ArrayList<Trenes>();
    int pointer = 0;

    @FXML
    private AnchorPane Pane;
    @FXML
    private Label lblIndice;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnFin;
    @FXML
    private Button btnAdelante;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblMax;
    @FXML
    private Label lblOrigen;
    @FXML
    private Label lblDestino;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtOrigen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpiar();
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

        txtNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isLetter(car)) {
                    event.consume();
                }
            }
        });
        txtOrigen.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isLetter(car)) {
                    event.consume();
                }
            }
        });
    }

    @FXML
    private void buscar_Nombre() {
        try {
            limpiar();
            txtOrigen.setText("");
            String Buscar = txtNombre.getText();
            for (int i = 0; i < recorridos.size(); i++) {
                if (Buscar.equals(recorridos.get(i).getNombreRecorrido())) {
                    String nom = recorridos.get(i).getNombreRecorrido();
                    int max = recorridos.get(i).getNoVagones();
                    String ori = recorridos.get(i).getCiudad();
                    String dest = recorridos.get(i).getIDMaquina();
                    Trenes Busqueda = new Trenes(nom, max, ori, dest);
                    TempNombre.add(Busqueda);
                }
            }
            for (int j = 0; j < TempNombre.size(); j++) {
                lblNombre.setText(TempNombre.get(j).getNombreRecorrido());
                lblMax.setText(String.valueOf(TempNombre.get(j).getNoVagones()));
                lblOrigen.setText(TempNombre.get(j).getCiudad());
                lblDestino.setText(TempNombre.get(j).getIDMaquina());
                this.lblIndice.setText((pointer + 1) + "/" + TempNombre.size());

            }
            if (TempNombre.size() == 0) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setHeaderText("No existe el registo");
                msg.show();
            }

        } catch (Exception ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setHeaderText("Error en la aplicación");
            msg.setContentText(ex.getMessage());
            msg.show();
        }
    }

    @FXML
    private void buscar_Origen() {
        try {
            limpiar();
            txtNombre.setText("");
            String Buscar = txtOrigen.getText();
            for (int i = 0; i < recorridos.size(); i++) {
                if (Buscar.equals(recorridos.get(i).getCiudad())) {
                    String nom = recorridos.get(i).getNombreRecorrido();
                    int max = recorridos.get(i).getNoVagones();
                    String ori = recorridos.get(i).getCiudad();
                    String dest = recorridos.get(i).getIDMaquina();
                    Trenes Busqueda = new Trenes(nom, max, ori, dest);
                    TempOrigen.add(Busqueda);
                }
            }
            for (int j = 0; j < TempOrigen.size(); j++) {
                lblNombre.setText(TempOrigen.get(j).getNombreRecorrido());
                lblMax.setText(String.valueOf(TempOrigen.get(j).getNoVagones()));
                lblOrigen.setText(TempOrigen.get(j).getCiudad());
                lblDestino.setText(TempOrigen.get(j).getIDMaquina());
                this.lblIndice.setText((pointer + 1) + "/" + TempOrigen.size());

            }
            if (TempOrigen.size() == 0) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setHeaderText("No existe el registo");
                msg.show();
            }

        } catch (Exception ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setHeaderText("Error en la aplicación");
            msg.setContentText(ex.getMessage());
            msg.show();
        }
    }

    @FXML
    private void cancelar() {
        this.Pane.setVisible(false);
    }

    private void limpiar() {
        lblNombre.setText("");
        lblMax.setText("");
        lblOrigen.setText("");
        lblDestino.setText("");
        lblIndice.setText("0/0");
        TempNombre.clear();
        TempOrigen.clear();

    }

}

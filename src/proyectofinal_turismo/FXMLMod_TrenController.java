package proyectofinal_turismo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class FXMLMod_TrenController implements Initializable {

    ArrayList<Trenes> recorridos = null;
    int pointer = 0;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtMin;
    @FXML
    private TextField txtOrigen;
    @FXML
    private TextField txtDestino;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnFin;
    @FXML
    private Button btnAdelante;
    @FXML
    private Label lblIndice;
    @FXML
    private Button btnInicio;
    @FXML
    private AnchorPane Pane;

    public void setRecorridos(ArrayList<Trenes> recorridos) {
        this.recorridos = recorridos;
        Trenes temp = recorridos.get(pointer);
        this.txtNombre.setText(temp.getNombreRecorrido());
        this.txtMax.setText(String.valueOf(temp.getNoVagones()));
        this.txtOrigen.setText(temp.getCiudad());
        this.txtDestino.setText(temp.getIDMaquina());
        this.lblIndice.setText((pointer + 1) + "/" + recorridos.size());

        txtNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isLetter(car)) {
                    event.consume();
                }
            }
        });
        txtMax.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }
            }
        });
        txtMin.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }
            }
        });

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void modificar() {
        try {
            recorridos.remove(pointer);
            String nombre = txtNombre.getText();
            int max = Integer.valueOf(txtMax.getText());
            int min = Integer.valueOf(txtMin.getText());

            do {

                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error");
                msgE.setHeaderText("Valor de Minimo y Maximo invalidos"
                        + "\n Verifique");
                msgE.show();

            } while (min < max);

            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            Trenes nuevo = new Trenes(nombre, max, destino, destino);
            recorridos.add(this.pointer, nuevo);

            Alert msgC = new Alert(Alert.AlertType.INFORMATION);
            msgC.setTitle("Modificar");
            msgC.setHeaderText("Ha modificado el registro correctamente");
            msgC.show();

        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No se pudo modificar");
            msgE.show();
        }
    }

    @FXML
    private void moverRegistro(ActionEvent event) {
        try {
            Trenes temp;
            if (event.getSource() == btnInicio) {
                pointer = 0;

                temp = recorridos.get(pointer);
                this.txtNombre.setText(temp.getNombreRecorrido());
                this.txtMax.setText(String.valueOf(temp.getNoVagones()));
                this.txtOrigen.setText(temp.getCiudad());
                this.txtDestino.setText(temp.getIDMaquina());
                this.lblIndice.setText((pointer + 1) + "/" + recorridos.size());
            }
            if (event.getSource() == btnAtras) {
                if ((pointer - 1) >= 0) {
                    pointer--;

                    temp = recorridos.get(pointer);
                    this.txtNombre.setText(temp.getNombreRecorrido());
                    this.txtMax.setText(String.valueOf(temp.getNoVagones()));
                    this.txtOrigen.setText(temp.getCiudad());
                    this.txtDestino.setText(temp.getIDMaquina());
                    this.lblIndice.setText((pointer + 1) + "/" + recorridos.size());
                }
            }
            if (event.getSource() == btnAdelante) {
                if ((pointer + 1) < recorridos.size()) {
                    pointer++;

                    temp = recorridos.get(pointer);
                    this.txtNombre.setText(temp.getNombreRecorrido());
                    this.txtMax.setText(String.valueOf(temp.getNoVagones()));
                    this.txtOrigen.setText(temp.getCiudad());
                    this.txtDestino.setText(temp.getIDMaquina());
                    this.lblIndice.setText((pointer + 1) + "/" + recorridos.size());
                }
            }
            if (event.getSource() == btnFin) {
                pointer = recorridos.size() - 1;

                temp = recorridos.get(pointer);
                this.txtNombre.setText(temp.getNombreRecorrido());
                this.txtMax.setText(String.valueOf(temp.getNoVagones()));
                this.txtOrigen.setText(temp.getCiudad());
                this.txtDestino.setText(temp.getIDMaquina());
                this.lblIndice.setText((pointer + 1) + "/" + recorridos.size());
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
    private void Borrar() {
        try {
            recorridos.remove(pointer);

            txtNombre.setText("");
            txtMax.setText("");
            txtMin.setText("");
            txtOrigen.setText("");
            txtDestino.setText("");

            Alert msgC = new Alert(Alert.AlertType.INFORMATION);
            msgC.setTitle("Eliminar");
            msgC.setHeaderText("Ha eliminado el registro correctamente");
            msgC.show();

        } catch (Exception e) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No se pudo eliminar");
            msgE.show();
        }
    }

    @FXML
    private void Cancelar() {
        this.Pane.setVisible(false);
    }

}

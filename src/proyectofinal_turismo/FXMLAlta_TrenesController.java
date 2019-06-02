package proyectofinal_turismo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import misclases.Trenes;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLAlta_TrenesController implements Initializable {

    ArrayList<Trenes> recorridos = null;
    private FXMLSistemaController cont_padre;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtMAX;
    @FXML
    private TextField txtOrigen;
    @FXML
    private TextField txtDestino;
    @FXML
    private Button btnAceptar;
    @FXML
    private Pane Border;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setRecorridos(ArrayList<Trenes> recorridos) {
        this.recorridos = recorridos;

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
        txtDestino.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isLetter(car)) {
                    event.consume();
                }
            }
        });
        txtMAX.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }
            }
        });
    }

    public void setCont_padre(FXMLSistemaController cont_padre) {
        this.cont_padre = cont_padre;
    }

    @FXML
    private void Agregar_Recorrido() {
        try {
            boolean correcto = false;
            do {
                String modelo = txtNombre.getText();
                int n_vagones = Integer.valueOf(txtMAX.getText());
                String ciudad = txtOrigen.getText();
                String id_maquina = txtDestino.getText();
                if (modelo != null) {

                    correcto = true;
                    Trenes temporal = new Trenes(modelo, n_vagones, ciudad, id_maquina);
                    this.recorridos.add(temporal);

                    txtNombre.setText("");
                    txtMAX.setText("");
                    txtOrigen.setText("");
                    txtDestino.setText("");

                    Alert msgC = new Alert(Alert.AlertType.INFORMATION);
                    msgC.setTitle("Agregado");
                    msgC.setHeaderText("Se agrego correctamente el registro");
                    msgC.showAndWait();
                    

                } else {
                    correcto = false;
                    Alert msgE = new Alert(Alert.AlertType.ERROR);
                    msgE.setTitle("Error");
                    msgE.setHeaderText("Valor Maximo y Minimo Incorrectos");
                    txtMAX.setText("");
                    msgE.showAndWait();
                }
                
            } while (correcto == false);
            
        } catch (Exception ex) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("Error");
            msgE.setHeaderText("No se pudo agregar");
            msgE.show();
        }
    }

    @FXML
    private void cerrar_ventana() {
        this.Border.setVisible(false);
    }

}

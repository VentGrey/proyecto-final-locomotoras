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
import misclases.Maquinistas;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class FXMLAlta_MaquinistaController implements Initializable {

    private ArrayList<Maquinistas> maquinistas = null;
    private FXMLSistemaController cont_padre;
    
    @FXML
    private Button btnAceptar;
    
    @FXML
    private TextField txtApPaterno;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtCedula;
    
    @FXML
    private TextField txtAntiguedad;
    
    @FXML
    private Pane Border;
    
    @FXML
    private Button btnCancelar;

    public void setFather(FXMLSistemaController father) {
        this.cont_padre = father;
        

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setMaquinistas(ArrayList<Maquinistas> maquinistas) {
        this.maquinistas = maquinistas;
        txtNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char temp = event.getCharacter().charAt(0);

                if (!Character.isLetter(temp)) {
                    event.consume();
                }
            }
        });
        txtApPaterno.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char temp = event.getCharacter().charAt(0);

                if (!Character.isLetter(temp)) {
                    event.consume();
                }
            }
        });
        txtCedula.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char temp = event.getCharacter().charAt(0);

                if (!Character.isDigit(temp)) {
                    event.consume();
                }
                
                    
            }
        });
        txtAntiguedad.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char temp = event.getCharacter().charAt(0);

                if (!Character.isDigit(temp)) {
                    event.consume();
                }
            }
        });
    }

    public ArrayList<Maquinistas> getMaquinistas() {
        return maquinistas;
    }

    public Button getBtnAceptar() {
        return btnAceptar;
    }

    @FXML
    private void alta_maquinistas() {

        try {
            for (int i = 0; i < maquinistas.size(); i++) {
                        int get = maquinistas.get(i).getCedula();
                        if (get == Integer.valueOf(txtCedula.getText())) {
                            txtCedula.setText("");
                             Alert msgE = new Alert(Alert.AlertType.ERROR);
                             msgE.setTitle("Error en el registro de la cédula");
                             msgE.setHeaderText("El numero de la cédula actual"
                                     + " no esta"
                                     + " disponible"
                                     + "\nIntente con otro");
                             msgE.show();
                        }
                    }
            String nombre = txtNombre.getText();
            if (nombre.length() >= 15) {
                Alert msgE = new Alert(Alert.AlertType.ERROR);
                msgE.setTitle("Error en el nombre");
                msgE.setHeaderText("El nombre que usted está intentando "
                        + "registrar supera los 15 carácteres, ingrese un "
                        + "nombre más corto");
            }
            String apPaterno = txtApPaterno.getText();
            int cedula = Integer.valueOf(txtCedula.getText());
            int antiguedad = Integer.valueOf(txtAntiguedad.getText());

            Maquinistas temporal = new Maquinistas(nombre, apPaterno, cedula, 
                    antiguedad);
            this.maquinistas.add(temporal);

            txtNombre.setText("");
            txtApPaterno.setText("");
            txtCedula.setText("");
            txtAntiguedad.setText("");
            
            Alert msgC = new Alert(Alert.AlertType.INFORMATION);
            msgC.setTitle("¡Listo!");
            msgC.setHeaderText("Se agregó el maquinista.");
            msgC.show();

        } catch (Exception ex) {
            Alert msgE = new Alert(Alert.AlertType.ERROR);
            msgE.setTitle("¡ERROR!");
            msgE.setHeaderText("No se pudo agregar maquinista :( ");
            msgE.show();
        }
    }

    @FXML
    private void cerrar_ventana() {
        this.Border.setVisible(false);
    }
}

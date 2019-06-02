package proyectofinal_turismo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import misclases.Maquinistas;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLModificar_MaquinistaController implements Initializable {

    ArrayList<Maquinistas> guias = null;
    private int pointer = 0;

    @FXML
    private AnchorPane Pane;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApPaterno;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtEspecialidad;
    @FXML
    private TextField txtAntiguedad;
    @FXML
    private Label lblIndice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setGuias(ArrayList<Maquinistas> guias) {
        this.guias = guias;
        Maquinistas temp = guias.get(pointer);
        this.txtNombre.setText(temp.getNombre());
        this.txtApPaterno.setText(temp.getApPaterno());
        this.txtCedula.setText(String.valueOf(temp.getCedula()));
        this.txtAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
        this.lblIndice.setText((pointer + 1) + "/" + guias.size());
        
        txtNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);
                
                if (!Character.isLetter(car)){
                    event.consume();
                }
            }
        });
        txtApPaterno.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);
                
                if (!Character.isLetter(car)){
                    event.consume();
                }
            }
        });
        txtEspecialidad.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);
                
                if (!Character.isLetter(car)){
                    event.consume();
                }
            }
        });
        txtCedula.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);
                
                if (!Character.isDigit(car)){
                    event.consume();
                }
            }
        });
        txtAntiguedad.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);
                
                if (!Character.isDigit(car)){
                    event.consume();
                }
            }
        });

    }

    @FXML
    private void avanza() {
        if ((pointer + 1) < guias.size()) {
            pointer++;
            Maquinistas temp = guias.get(pointer);
            this.txtNombre.setText(temp.getNombre());
            this.txtApPaterno.setText(temp.getApPaterno());
            this.txtCedula.setText(String.valueOf(temp.getCedula()));
            this.txtAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
            this.lblIndice.setText((pointer + 1) + "/" + guias.size());
        }
    }

    @FXML
    private void retrocede() {
        if ((pointer - 1) >= 0) {
            pointer--;
            Maquinistas temp = guias.get(pointer);
            this.txtNombre.setText(temp.getNombre());
            this.txtApPaterno.setText(temp.getApPaterno());
            this.txtCedula.setText(String.valueOf(temp.getCedula()));
            this.txtAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
            this.lblIndice.setText((pointer + 1) + "/" + guias.size());
        }
    }

    @FXML
    private void inicio() {
        pointer = 0;
        Maquinistas temp = guias.get(pointer);
        this.txtNombre.setText(temp.getNombre());
        this.txtApPaterno.setText(temp.getApPaterno());
        this.txtCedula.setText(String.valueOf(temp.getCedula()));
        this.txtAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
        this.lblIndice.setText((pointer + 1) + "/" + guias.size());
    }

    @FXML
    private void fin() {
        pointer = guias.size() - 1;
        Maquinistas temp = guias.get(pointer);
        this.txtNombre.setText(temp.getNombre());
        this.txtApPaterno.setText(temp.getApPaterno());
        this.txtCedula.setText(String.valueOf(temp.getCedula()));
        this.txtAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
        this.lblIndice.setText((pointer + 1) + "/" + guias.size());
    }

    @FXML
    private void Modificar() {
        try {
            guias.remove(pointer);
            String nombre = txtNombre.getText();
            String apPaterno = txtApPaterno.getText();
            int cedula = Integer.valueOf(txtCedula.getText());
            String especialidad = txtEspecialidad.getText();
            int antiguedad = Integer.valueOf(txtAntiguedad.getText());
            Maquinistas nuevo = new Maquinistas(nombre, apPaterno, cedula, antiguedad);
            guias.add(this.pointer, nuevo);

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
    private void Eliminar() {
        try {
            guias.remove(pointer);

            txtNombre.setText("");
            txtApPaterno.setText("");
            txtCedula.setText("");
            txtEspecialidad.setText("");
            txtAntiguedad.setText("");

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
    private void cerrar_ventana() {
        this.Pane.setVisible(false);
    }

}

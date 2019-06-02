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
import misclases.Maquinistas;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLBuscar_MaquinistaController implements Initializable {

    ArrayList<Maquinistas> guias = null;
    ArrayList<Maquinistas> Temp = new ArrayList<Maquinistas>();
    int pointer = 0;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApPaterno;
    @FXML
    private Label lblCedula;
    @FXML
    private Label lblEspecialidad;
    @FXML
    private Label lblAntiguedad;
    @FXML
    private Label lblIndice;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnAdelante;
    @FXML
    private Button btnUltimo;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpiar();

    }

    public void setGuias(ArrayList<Maquinistas> guias) {
        this.guias = guias;
        if(guias.isEmpty()==true){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("No hay Registros");
            msg.setHeaderText("Por favor cree nuevos registros de guias"
                    + "\n o abra un archivo");
            msg.showAndWait();
            this.pane.setVisible(false);
        }

        txtBuscar.setOnKeyTyped(new EventHandler<KeyEvent>() {
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
    private void Buscar() {
        try {
            limpiar();
            String Buscar = txtBuscar.getText();
            for (int i = 0; i < guias.size(); i++) {
                String comparar = guias.get(i).getNombre();
                if (Buscar.equals(comparar)) {
                    String nom = guias.get(i).getNombre();
                    String apPa = guias.get(i).getApPaterno();
                    int ced = guias.get(i).getCedula();
                    int ant = guias.get(i).getAntiguedad();

                    Maquinistas Busqueda = new Maquinistas(nom, apPa, ced, ant);
                    Temp.add(Busqueda);
                }
            }
            
            for (int j = 0; j < Temp.size(); j++) {
                lblNombre.setText(Temp.get(j).getNombre());
                lblApPaterno.setText(Temp.get(j).getApPaterno());
                lblCedula.setText(String.valueOf(Temp.get(j).getCedula()));
                lblAntiguedad.setText(String.valueOf(Temp.get(j).getAntiguedad()));
                lblIndice.setText((pointer + 1) + "/" + Temp.size());
            }
            if(Temp.size() == 0){                
                Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("No hay Registro");
            msg.setHeaderText("No existe el Registro");
            msg.showAndWait();
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
    private void moverRegistro(ActionEvent event) {
        try {
            Maquinistas temp;
            if (event.getSource() == btnInicio) {
                pointer = 0;
                temp = Temp.get(pointer);
                this.lblNombre.setText(temp.getNombre());
                this.lblApPaterno.setText(temp.getApPaterno());
                this.lblCedula.setText(String.valueOf(temp.getCedula()));
                this.lblAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
                this.lblIndice.setText((pointer + 1) + "/" + Temp.size());
            }
            if (event.getSource() == btnAtras) {
                if ((pointer - 1) >= 0) {
                    pointer--;

                    temp = Temp.get(pointer);
                    this.lblNombre.setText(temp.getNombre());
                    this.lblApPaterno.setText(temp.getApPaterno());
                    this.lblCedula.setText(String.valueOf(temp.getCedula()));
                    this.lblAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
                    this.lblIndice.setText((pointer + 1) + "/" + Temp.size());
                }
            }
            if (event.getSource() == btnAdelante) {
                if ((pointer + 1) < Temp.size()) {
                    pointer++;

                    temp = Temp.get(pointer);
                    this.lblNombre.setText(temp.getNombre());
                    this.lblApPaterno.setText(temp.getApPaterno());
                    this.lblCedula.setText(String.valueOf(temp.getCedula()));
                    this.lblAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
                    this.lblIndice.setText((pointer + 1) + "/" + Temp.size());
                }
            }
            if (event.getSource() == btnUltimo) {
                pointer = Temp.size() - 1;
                temp = Temp.get(pointer);
                this.lblNombre.setText(temp.getNombre());
                this.lblApPaterno.setText(temp.getApPaterno());
                this.lblCedula.setText(String.valueOf(temp.getCedula()));
                this.lblAntiguedad.setText(String.valueOf(temp.getAntiguedad()));
                this.lblIndice.setText((pointer + 1) + "/" + Temp.size());
            }

        } catch (Exception ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setHeaderText("Error en la aplicación");
            msg.setContentText(ex.getMessage());
            msg.show();
        }
    }

    public void limpiar() {
        lblNombre.setText("");
        lblApPaterno.setText("");
        lblCedula.setText("");
        lblEspecialidad.setText("");
        lblAntiguedad.setText("");
        lblIndice.setText("0/0");
        Temp.clear();

    }
}

package misclases;

/**
 *
 * @author Carlos
 */
public class Trenes {
    private String Modelo;
    private int NoVagones;
    private String Ciudad;
    private String IDMaquina;

    public Trenes() {
    }

    
    public Trenes(String Modelo, int Max, String Inicio, String Termino) {
        this.Modelo = Modelo;
        this.NoVagones = Max;
        this.Ciudad = Inicio;
        this.IDMaquina = Termino;
    }

    public String getNombreRecorrido() {
        return Modelo;
    }

    public void setNombreRecorrido(String NombreRecorrido) {
        this.Modelo = NombreRecorrido;
    }

    public int getNoVagones() {
        return NoVagones;
    }

    public void setNoVagones(int NoVagones) {
        this.NoVagones = NoVagones;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getIDMaquina() {
        return IDMaquina;
    }

    public void setIDMaquina(String IDMaquina) {
        this.IDMaquina = IDMaquina;
    }

    @Override
    public String toString() {
        return "******RECORRIDOS******" + "\nNombre del Recorrido: " + Modelo + "\nMáximo Personas: " + NoVagones + "\nMinimo Personas: " + "\nPunto de Inicio: " + Ciudad + "\nPunto de Terminación: " + IDMaquina + "******************";
    }
}

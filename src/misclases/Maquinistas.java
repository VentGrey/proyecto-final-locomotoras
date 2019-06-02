package misclases;

/**
 *
 * @author Carlos
 */
public class Maquinistas {
    private String Nombre;
    private String ApPaterno;
    private int Cedula;
    private int Antiguedad;
    

    public Maquinistas() {
    }
    
    public Maquinistas(String Nombre, String ApPaterno, int Cedula, int Antiguedad) {
        this.Nombre = Nombre;
        this.ApPaterno = ApPaterno;
        this.Cedula = Cedula;
        this.Antiguedad = Antiguedad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApPaterno() {
        return ApPaterno;
    }

    public void setApPaterno(String ApPaterno) {
        this.ApPaterno = ApPaterno;
    }

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int Cedula) {
        this.Cedula = Cedula;
    }

    public int getAntiguedad() {
        return Antiguedad;
    }

    public void setAntiguedad(int Antiguedad) {
        this.Antiguedad = Antiguedad;
    }

    @Override
    public String toString() {
        return "******GUIA TURISTICO******" + "Nombre: " + Nombre + "\nApPaterno: " + ApPaterno + "\nCedula: " + Cedula + "\nEspecialidad: " + "\nAntiguedad: " + Antiguedad + "\n******************";
    }

    
    
}

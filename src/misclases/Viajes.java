package misclases;

/**
 *
 * @author Carlos
 */
public class Viajes {
    
    private String NombreGuia;
    private String NombreRecorrido;
    private int Asistentes;
    private int Dia;
    private String Mes;
    private int Anio;

    public Viajes() {
    }

    public Viajes(String NombreGuia, String NombreRecorrido, int Asistentes, int Dia, String Mes, int Anio) {
        this.NombreGuia = NombreGuia;
        this.NombreRecorrido = NombreRecorrido;
        this.Asistentes = Asistentes;
        this.Dia = Dia;
        this.Mes = Mes;
        this.Anio = Anio;
    }
    
    public String getNombreGuia() {
        return NombreGuia;
    }

    public void setNombreGuia(String NombreGuia) {
        this.NombreGuia = NombreGuia;
    }

    public String getNombreRecorrido() {
        return NombreRecorrido;
    }

    public void setNombreRecorrido(String NombreRecorrido) {
        this.NombreRecorrido = NombreRecorrido;
    }

    public int getAsistentes() {
        return Asistentes;
    }

    public void setAsistentes(int Asistentes) {
        this.Asistentes = Asistentes;
    }

    public int getDia() {
        return Dia;
    }

    public void setDia(int Dia) {
        this.Dia = Dia;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String Mes) {
        this.Mes = Mes;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    
    

    @Override
    public String toString() {
        return "******RESERVACIONES******" + "\nGuia: " + NombreGuia + "\nRecorrido: " + NombreRecorrido + 
                "\nAsistentes: " + Asistentes + "\nFecha: " + Dia +"/"+Mes+"/"+Anio ;
    }
    
    
   
}

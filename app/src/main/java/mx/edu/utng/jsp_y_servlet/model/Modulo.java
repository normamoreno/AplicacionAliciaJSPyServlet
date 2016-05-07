package mx.edu.utng.jsp_y_servlet.model;

/**
 * Created by NORMA on 06/04/2016.
 */
public class Modulo {
    private int idModulo;
    private int idUsuario;
    private String nombre;


    public Modulo() {
        idModulo=0;
        idUsuario=0;
        nombre="";

    }

    public Modulo(int idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

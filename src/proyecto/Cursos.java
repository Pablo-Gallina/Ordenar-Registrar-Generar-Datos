package proyecto;

public class Cursos {
    private Integer codigo = 0;
    private String nombre="", seccion="";

    public Cursos() {
        
    }

    public Cursos(Integer codigo, String nombre, String seccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.seccion=seccion;
    }

    //Getters & Setters
    //Codigo
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    //Nombre
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Seccion
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    
    @Override
     public String toString() {
        return getCodigo()+", "+getNombre()+", "+getSeccion();
    }
}

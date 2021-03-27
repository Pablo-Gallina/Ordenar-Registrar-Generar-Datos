package proyecto;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
/**
 *
 * @author GG
 */
public class Proyecto {
    private Scanner teclado = new Scanner(System.in);
    private int codigo;
    private String nombre, seccion;
    boolean salir = false; //variable para detectar si el usuario quiere finalizar el programa
    ArrayList<Cursos> curso=new ArrayList<>();
    
    //Funcion para mostrar el menu principal
    public void menu(){
        teclado = new Scanner(System.in); //Variable que guarda la respuesta del usuario
          
        int opcion; //Guardaremos la opcion del usuario
        
        //Mientras el usuario no quiera finalizar el programa
        while (!salir) {
            System.out.println("Menu Principal");
            System.out.println("1. Registrar Curso");
            System.out.println("2. Ver Registros");
            System.out.println("3. Generar Reporte");
            System.out.println("4. Salir");
            
            try {
                System.out.println("Seleccione una de las opciones");
                opcion = teclado.nextInt(); //Opcion seleccionada por el usuario
                
                //Casos segun el usario seleccione
                switch (opcion) {
                    //Nuevo Curso
                    case 1:
                        //Menu nuevo curso
                        menuNuevoCurso(opcion);
                        break;
                    //Ver Registros
                    case 2:
                        System.out.println("Ver Registros");
                        mostrarDatosCurso();
                        break;
                    //Generar Registros
                    case 3:
                        System.out.println("Generar reporte");
                        break;
                    //Filnalizar proceso
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.err.println("Seleccione una opcion valida");
                }
            } catch (InputMismatchException e) {
                System.err.println("Debes insertar un número");
                teclado.next();
            }
        }
    }
    
    //Funcion para el menu de nuevo curso, recibe como parametro la opcion seleccioada por el usaurio
    public void menuNuevoCurso(int opcion){
        System.out.println("*********Nuevo curso***********");
        System.out.println("1. Registrar un solo Curso");
        System.out.println("2. Registrar varios Cursos");
        System.out.println("3. Regresar");
        
        try {
            System.out.println("Seleccione una de las opciones");
            opcion = teclado.nextInt(); //Opcion seleccionada por el usuario
                //Casos segun el usario seleccione
            switch (opcion) {
                //Agregar solo un curso
                case 1:
                    System.out.println("uno solo");
                    ingresarDatosCurso();
                    break;
                //Agregar varios cursos
                case 2:
                    System.out.println("varios");
                    ingresarVariosDatos();
                    break;
                //Regresar al menu pricipal
                case 3:
                    menu();
                    break;
                default:
                    System.err.println("Seleccione una opcion valida");
                }
        } catch (InputMismatchException e) {
            System.err.println("Debes insertar solo un número");
            teclado.next();
        }
    }
    
    public void ingresarDatosCurso(){
    
    // Use add() method to add elements in the vector
    codigo = teclado.nextInt();
    nombre = teclado.nextLine();
    seccion = teclado.nextLine();
    curso.add(new Cursos(codigo,nombre,seccion));
    
    }
    
    public void ingresarVariosDatos(){
        
        int num = teclado.nextInt();
        for (int i=0; i<num; i++) {
            System.out.println("Ingrese producto "+(i+1)+": ");
            codigo = teclado.nextInt();
            teclado.nextLine();
            nombre = teclado.nextLine();
            seccion = teclado.nextLine();
            curso.add(new Cursos(codigo,nombre,seccion));
        }
    }
    
    public void ordenar() {
        for(int i=0;i<curso.size();i++) {
            for(int j=0;j<(curso.size()-1)-i;j++) {
                if (curso.get(j).getCodigo()>curso.get(j+1).getCodigo()) {
                    int newCodigo;
                    String newNombre, newSeccion;
                    
                    newCodigo=curso.get(j).getCodigo();
                    newNombre=curso.get(j).getNombre();
                    newSeccion=curso.get(j).getSeccion();
                    
                    curso.get(j).setCodigo(curso.get(j+1).getCodigo());
                    curso.get(j).setNombre(curso.get(j+1).getNombre());
                    curso.get(j).setSeccion(curso.get(j+1).getSeccion());
                    
                    curso.get(j+1).setCodigo(newCodigo);
                    curso.get(j+1).setNombre(newNombre);
                    curso.get(j+1).setSeccion(newSeccion);
                }
            }
        }
    }
    
    public void mostrarDatosCurso(){
        ordenar();
        
        System.out.println("Sueldos ordenados de menor a mayor.");
        for(int i=0;i<curso.size();i++) {
            System.out.println(curso.get(i).getCodigo()+", "+curso.get(i).getNombre()+", "+curso.get(i).getSeccion());
        }
        
        
    }
    public static void main(String[] args) {
        Proyecto miPrograma = new Proyecto();
        miPrograma.menu();
    }    
    
}

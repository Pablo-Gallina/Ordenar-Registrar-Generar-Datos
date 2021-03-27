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
    nombre = teclado.next();
    seccion = teclado.next();
    curso.add(new Cursos(codigo,nombre,seccion));
    
    }
    
    public void ingresarVariosDatos(){
        
        int num = teclado.nextInt();
        for (int i=0; i<=num; i++) {
            System.out.println("Ingrese producto "+(i+1)+": ");
            codigo = teclado.nextInt();
            nombre = teclado.next();
            seccion = teclado.next();
            curso.add(new Cursos(codigo,nombre,seccion));
        }
    }
    
    
    public void mostrarDatosCurso(){
  
        System.out.println("The vector is: " + curso);
        
        
        float mayor, menor;
        int pos, pos1;

        mayor=curso.get(0).getCodigo();
        menor=curso.get(0).getCodigo();
        pos=0;
        pos1=0;
    
        for (int i = 1; i <curso.size(); i++) {
            if (curso.get(i).getCodigo()>mayor) {
                mayor=curso.get(i).getCodigo();
                pos=i;
            }         
        }
        System.out.println("El producto mas carro es: "+curso.get(pos).getCodigo());
        /*
        for (int i = 1; i <producto.length; i++) {
            if (precio[i]<menor) {
                menor=precio[i];
                pos1=i;
            }            
        }
        System.out.println("El producto mas barato es: "+producto[pos1]);
        System.out.println("con un precio de Q."+precio[pos1]);*/
    }
    public static void main(String[] args) {
        Proyecto miPrograma = new Proyecto();
        miPrograma.menu();
    }    
    
}

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
    String continuar;
    boolean salir = false; //variable para detectar si el usuario quiere finalizar el programa
    ArrayList<Cursos> curso=new ArrayList<>();
    int opcion;
    
    //Funcion para mostrar el menu principal
    public void menu(){
        teclado = new Scanner(System.in); //Variable que guarda la respuesta del usuario
          
         //Guardaremos la opcion del usuario
        
        //Mientras el usuario no quiera finalizar el programa
        while (!salir) {
            System.out.println("************Menu Principal************");
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
                        menuNuevoCurso();
                        break;
                    //Ver Registros
                    case 2:
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
    
    //Funcion para el menu de nuevo curso
    public void menuNuevoCurso(){
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
                    ingresarDatosCurso();
                    break;
                //Agregar varios cursos
                case 2:
                    
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
        System.out.println("*****Registrar un solo Curso******");
        // Use add() method to add elements in the vector
        System.out.print("Ingrese el codigo de la clase: ");
        
        codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Ingrese el nombre de la clase: ");
        nombre = teclado.nextLine();

        System.out.print("Ingrese la seccion de la clase: ");
        seccion = teclado.nextLine();
        
        curso.add(new Cursos(codigo,nombre,seccion));
        System.out.println("\u001B[32m Se registro el dato correctamente \u001B[0m");
        
        System.out.print("¿Deseas registrar otra clase? S/N: ");
        
        continuar = teclado.next();
        if(continuar.equals("S") || continuar.equals("s")){
            menuNuevoCurso();
        }
    }
    
    public void ingresarVariosDatos(){
        System.out.println("*****Registrar varios Cursos******");
        System.out.println("¿Cuantas clases desa registrar?");
        int num = teclado.nextInt();
        for (int i=0; i<num; i++) {
            System.out.println("Clase No."+(i+1)+": ");
            
            System.out.print("Ingrese el codigo de la clase: ");
            codigo = teclado.nextInt();
            teclado.nextLine();
            
            System.out.print("Ingrese el nombre de la clase: ");
            nombre = teclado.nextLine();
            
            System.out.print("Ingrese la seccion de la clase: ");
            seccion = teclado.nextLine();
            
            curso.add(new Cursos(codigo,nombre,seccion));
        }
        System.out.println("\u001B[32m Se registro el dato correctamente \u001B[0m");
        
        System.out.print("¿Deseas registrar otra clase? S/N: ");

        continuar = teclado.next();
        if(continuar.equals("S") || continuar.equals("s")){
            menuNuevoCurso();
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
    
    public void verTodo(){
        System.out.println("Datos ordenados de menor a mayor segun su codigo");
        
        for(int i=0;i<curso.size();i++) {
            System.out.println(curso.get(i).getCodigo()+", "+curso.get(i).getNombre()+", "+curso.get(i).getSeccion());
        }
        System.out.print("¿Deseas ver otro registro? S/N: ");
        
        continuar = teclado.next();
        if(continuar.equals("S") || continuar.equals("s")){
            mostrarDatosCurso();
        }
    }
    
    public void buscarCurso(){
        int encontrado = 0;
        try {
            System.out.println("Ingrese el codigo del curso: ");
            opcion = teclado.nextInt(); //Opcion seleccionada por el usuario
            
            for(int i=0;i<curso.size();i++) {
                if(curso.get(i).getCodigo().equals(opcion)){
                   System.out.println(curso.get(i).getCodigo()+", "+curso.get(i).getNombre()+", "+curso.get(i).getSeccion()); 
                   encontrado=1;
                }   
            }
            
            if(encontrado==0){
                System.err.println("No se encotraron concidencias");
            }
            System.out.print("¿Deseas buscar otro registro? S/N: ");
        
            continuar = teclado.next();
            if(continuar.equals("S") || continuar.equals("s")){
                buscarCurso();
            }
            
        } catch (InputMismatchException e) {
            System.err.println("Debes insertar solo un número");
            teclado.next();
        }
        
    }
    
    public void mostrarDatosCurso(){
        ordenar();
        
        if(curso.isEmpty()){
            System.err.println("No existen registros guardados, porfavor registre al menos un dato");
            System.out.println("1. Registrar Curso");
            System.out.println("2. Regresar al menu principal");
            try {
                System.out.println("Seleccione una de las opciones");
                opcion = teclado.nextInt(); //Opcion seleccionada por el usuario
                    //Casos segun el usario seleccione
                switch (opcion) {
                    //Agregar curso
                    case 1:
                        menuNuevoCurso();
                        break;
                    //Regresar al menu pricipal
                    case 2:

                        menu();
                        break;
                    default:
                        System.err.println("Seleccione una opcion valida");
                    }
            } catch (InputMismatchException e) {
                System.err.println("Debes insertar solo un número");
                teclado.next();
            }
            
        } else{
            System.out.println("***********Ver Registros***********");
            System.out.println("1. Ver todos los cursos");
            System.out.println("2. Buscar curso por codigo");
            try {
                System.out.println("Seleccione una de las opciones");
                opcion = teclado.nextInt(); //Opcion seleccionada por el usuario
                    //Casos segun el usario seleccione
                switch (opcion) {
                    //Agregar curso
                    case 1:
                        verTodo();
                        break;
                    //Regresar al menu pricipal
                    case 2:
                        buscarCurso();
                        break;
                    default:
                        System.err.println("Seleccione una opcion valida");
                    }
            } catch (InputMismatchException e) {
                System.err.println("Debes insertar solo un número");
                teclado.next();
            }
            
        }
        
        
    }
    public static void main(String[] args) {
        Proyecto miPrograma = new Proyecto();
        miPrograma.menu();
    }    
    
}

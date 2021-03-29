package proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;

/**
 *
 * @author GG
 */
public class Proyecto {
    private Scanner teclado = new Scanner(System.in);
    private int codigo;
    private String nombre, seccion;
    String continuar;
    boolean salir = false, cargado=false; //variable para detectar si el usuario quiere finalizar el programa
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
            System.out.println("3. Cargar archivo txt");
            System.out.println("4. Generar Reporte");
            System.out.println("5. Salir");
            
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
                        cargarArchivo();
                    //Filnalizar proceso
                    case 4:
                        generarReporte();
                    //Filnalizar proceso
                    case 5:
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
    
    public void guardarDatosDelTxt(String direccion){
        File file = new File(direccion);

	Scanner scanner;
	try {
            //se pasa el flujo al objeto scanner
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
		// el objeto scanner lee linea a linea desde el archivo
		String linea = scanner.nextLine();
		Scanner delimitar = new Scanner(linea);
		//se usa una expresión regular
		//que valida que antes o despues de una coma (,) exista cualquier cosa
		//parte la cadena recibida cada vez que encuentre una coma				
		delimitar.useDelimiter("\\s*,\\s*");
                                
                int codigotxt = Integer.parseInt(delimitar.next());
                String nombretxt = delimitar.next();
                String secciontxt = delimitar.next();
				
		curso.add(new Cursos(codigotxt,nombretxt,secciontxt));
		}
		//se cierra el ojeto scanner
                System.out.println("\u001B[32m Se cargo el archivo correctamente \u001B[0m");
		scanner.close();
                cargado=true;
	} catch (FileNotFoundException e) {
            System.err.println("No se encontro el archivo, Introduce una direccion valida");
            System.err.println("Ejemplo: C:\\Users\\YouPC\\Documents\\arhivo.txt ");
            cargarArchivo();
	}
    }
    
    public void cargarArchivo(){
        if(cargado==true){
            System.err.println("Atencion, ya has cargado antes un archivo");   
        }
        System.out.println("**********Cargar Archivo**********");
        String direccion;
        System.out.println("0. Regresar al menu principal");
        System.out.print("Ingrese la direccion del archivo: ");
        
        direccion = teclado.next();
        
        if (direccion.equals("0")){
            menu();
        } else {
            guardarDatosDelTxt(direccion);
            menu();
        }    
    }
    
    //añadir más estudiantes al archivo
    public void generarReporte() {
        ordenar();
        if (!cargado) {
            System.err.println("No existen datos para generar un reporte");
            menu();
        }else{
            FileWriter flwriter = null;
            
            try {
                flwriter = new FileWriter("C:\\Users\\GG\\Documents\\salida.txt");
                BufferedWriter bfwriter = new BufferedWriter(flwriter);

                for(int i=0;i<curso.size();i++) {
                    bfwriter.write(curso.get(i).getCodigo() + "," + curso.get(i).getNombre() + "," + curso.get(i).getSeccion() + "\n");
                }

                bfwriter.close();
                System.out.println("Archivo creado satisfactoriamente...");
                
                System.out.println("¿Desas abrir el archivo? S/N: ");
                String preguntame = "";
                preguntame = teclado.next();
                if(preguntame.equals("S") || preguntame.equals("s")){
                    File objetofile = new File ("C:\\Users\\GG\\Documents\\salida.txt");
                    Desktop.getDesktop().open(objetofile);
                    menu();
                }else{
                    menu();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
        
        
    }
    
    public static void main(String[] args) {
        Proyecto miPrograma = new Proyecto();
        miPrograma.menu();
    }    
    
}

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
    private int codigo, opcion;
    private String nombre, seccion, continuar;
    String salida = "C:\\Users\\Public\\Documents\\salida.txt"; // direccion de salida del archivo generado del reporte
    boolean salir = false, cargado=false; //variable para detectar si el usuario quiere finalizar el programa
    
    //Lista de los los cursos
    ArrayList<Cursos> curso=new ArrayList<>();
    
    //Funcion para mostrar el menu principal
    public void menu(){
        teclado = new Scanner(System.in); //Variable que guarda la respuesta del usuario
        
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
                    //Cargar archivo txt
                    case 3:
                        cargarArchivo();
                    //Generar el reporte en formato txt de los datos registrados
                    case 4:
                        generarReporte();
                    //Filnalizar proceso
                    case 5:
                        salir = true;
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Seleccione una opcion valida");
                }
                //si existe un error al ingresar una opcion no valida
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
                //opcion no valida o no existente
                default:
                    System.err.println("Seleccione una opcion valida");
                }
         //si existe un error al ingresar una opcion no valida
        } catch (InputMismatchException e) {
            System.err.println("Debes insertar solo un número");
            teclado.next();
        }
    }
    //Funcion del submenu agregar curso, registrar un solo curso
    public void ingresarDatosCurso(){
        System.out.println("*****Registrar un solo Curso******");
       
        //capturar el codigo
        System.out.print("Ingrese el codigo de la clase: ");
        codigo = teclado.nextInt();
        teclado.nextLine(); //para leer espacios en la siguiente linea
        
        //capturar el nombre
        System.out.print("Ingrese el nombre de la clase: ");
        nombre = teclado.nextLine();
        
        //capturar la seccion
        System.out.print("Ingrese la seccion de la clase: ");
        seccion = teclado.nextLine();
        
        //Agregar los nuevos datos al array cursos
        curso.add(new Cursos(codigo,nombre,seccion));
        
        //Notificacion de que se agrego todo
        System.out.println("\u001B[32m Se registro el dato correctamente \u001B[0m");
        
        //Pregunta en caso de que el usuario desea registrar un nuevo curso
        System.out.print("¿Deseas registrar otra clase? S/N: ");
        
        continuar = teclado.next();
        if(continuar.equals("S") || continuar.equals("s")){
            menuNuevoCurso();
        }
    }
    
    //Funcion del submenu agregar curso, registrar varios cursos
    public void ingresarVariosDatos(){
        System.out.println("*****Registrar varios Cursos******");
        System.out.println("¿Cuantos cursos desas registrar?");
        int num = teclado.nextInt();
        for (int i=0; i<num; i++) {
            System.out.println("Clase No."+(i+1)+": ");
            
            //capturar el codigo
            System.out.print("Ingrese el codigo de la clase: ");
            codigo = teclado.nextInt();
            teclado.nextLine(); //para leer espacios en la siguiente linea

            //capturar el nombre
            System.out.print("Ingrese el nombre de la clase: ");
            nombre = teclado.nextLine();

            //capturar la seccion
            System.out.print("Ingrese la seccion de la clase: ");
            seccion = teclado.nextLine();

            //Agregar los nuevos datos al array cursos
            curso.add(new Cursos(codigo,nombre,seccion));
        }
         //Notificacion de que se agrego todo
        System.out.println("\u001B[32m Se registro el dato correctamente \u001B[0m");
        
        //Pregunta en caso de que el usuario desea registrar un nuevo curso
        System.out.print("¿Deseas registrar otra clase? S/N: ");

        continuar = teclado.next();
        if(continuar.equals("S") || continuar.equals("s")){
            menuNuevoCurso();
        }
        
    }
    //Funcion para ordenar los datos en almacenados en el ArrayList cursos
    public void ordenar() {
        
        //Recorrer el array
        for(int i=0;i<curso.size();i++) {
            for(int j=0;j<(curso.size()-1)-i;j++) {
                
                //comparar si el dato en el array es mayor o menor que el anterior
                if (curso.get(j).getCodigo()>curso.get(j+1).getCodigo()) {
                    
                    //Variables que almacenan los nuevos datos segun su nueva posicion
                    int newCodigo;
                    String newNombre, newSeccion;
                    
                    //Asignandole el nuevo valor a las variables con los nuevos valoras
                    newCodigo=curso.get(j).getCodigo();
                    newNombre=curso.get(j).getNombre();
                    newSeccion=curso.get(j).getSeccion();
                    
                    //Asignando nuevo orden al array con los datos ordenados
                    curso.get(j).setCodigo(curso.get(j+1).getCodigo());
                    curso.get(j).setNombre(curso.get(j+1).getNombre());
                    curso.get(j).setSeccion(curso.get(j+1).getSeccion());
                    
                    //Asignando el valor nuevo al siguiente dato en el array para seguir comparando
                    curso.get(j+1).setCodigo(newCodigo);
                    curso.get(j+1).setNombre(newNombre);
                    curso.get(j+1).setSeccion(newSeccion);
                }
            }
        }
    }
    
    //Funcion para ver todos los datos
    public void verTodo(){
        System.out.println("Datos ordenados de menor a mayor segun su codigo");
        
        //For para recorrer y mostrar todos los datos del array
        for(int i=0;i<curso.size();i++) {
            System.out.println(curso.get(i).getCodigo()+", "+curso.get(i).getNombre()+", "+curso.get(i).getSeccion());
        }
        System.out.print("¿Deseas ver otro registro? S/N: ");
        
        //Para volver o no a ver otro registro
        continuar = teclado.next();
        if(continuar.equals("S") || continuar.equals("s")){
            mostrarDatosCurso();
        }
    }
    
    //Funcion para buscar un registro segun su codigo
    public void buscarCurso(){
        int encontrado = 0; // variable que guarda si el dato se encontro
        try {
            System.out.println("Ingrese el codigo del curso: ");
            opcion = teclado.nextInt(); //dato a buscar
            
            //Recorrer todo el array de cursos
            for(int i=0;i<curso.size();i++) {
                //Si encuentra el dato, lo imprime
                if(curso.get(i).getCodigo().equals(opcion)){
                   System.out.println(curso.get(i).getCodigo()+", "+curso.get(i).getNombre()+", "+curso.get(i).getSeccion()); 
                   encontrado=1;
                }   
            }
            
            //sino se encontraron concidencias
            if(encontrado==0){
                System.err.println("No se encotraron concidencias");
            }
            System.out.print("¿Deseas buscar otro registro? S/N: ");
            
            //si desea seguir buscanod o no
            continuar = teclado.next();
            if(continuar.equals("S") || continuar.equals("s")){
                buscarCurso();
            }
        
        //error si el usuario ingresa mal una opcion
        } catch (InputMismatchException e) {
            System.err.println("Debes insertar solo un número");
            teclado.next();
        }
        
    }
    
    //Funcion para mostrar datos
    public void mostrarDatosCurso(){
        
        //se llama a ordenar para que ordene previo a mostrar los datos
        ordenar();
        
        //si no existe ningun dato devuelve un mensaje
        if(curso.isEmpty()){
            System.err.println("No existen registros guardados, porfavor registre al menos un dato");
            System.out.println("1. Registrar Curso");
            System.out.println("2. Regresar al menu principal");
            //Opcines segun el usuario decida
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
         // en caso de que si existan registros en el array cursos
        } else{
            //sub menu
            System.out.println("***********Ver Registros***********");
            System.out.println("1. Ver todos los cursos");
            System.out.println("2. Buscar curso por codigo");
            System.out.println("3. Regresar al menu principal");
            try {
                System.out.println("Seleccione una de las opciones");
                opcion = teclado.nextInt(); //Opcion seleccionada por el usuario
                    //Casos segun el usario seleccione
                switch (opcion) {
                    //Ver todos los cursos
                    case 1:
                        verTodo();
                        break;
                    //Buscar curso segun su codigo
                    case 2:
                        buscarCurso();
                        break;
                    case 3:
                        menu();
                        break;
                    default:
                        System.err.println("Seleccione una opcion valida");
                    }
                //si el usuario ingresa mal la opcion 
            } catch (InputMismatchException e) {
                System.err.println("Debes insertar solo un número");
                teclado.next();
            }
            
        }
        
        
    }
    
    //Funcion para guardar los datos del archivo de entrada txt en el array de cursos
    public void guardarDatosDelTxt(String direccion){
        File file = new File(direccion); //direccion dada por el usuario en donde se encuetra su archivo de entrada
	Scanner scanner;
	try {
            //se pasa el flujo al objeto scanner
            scanner = new Scanner(file);
            
            //Ciclo para recorrer los lineas que posee el archivo txt
            while (scanner.hasNextLine()) {
		// el objeto scanner lee linea a linea desde el archivo
		String linea = scanner.nextLine();
		Scanner delimitar = new Scanner(linea);
		//valida que antes o despues de una coma (,) exista cualquier cosa
		//parte la cadena recibida cada vez que encuentre una coma				
		delimitar.useDelimiter("\\s*,\\s*");
                                
                int codigotxt = Integer.parseInt(delimitar.next()); //guardando el primer valor antes de una coma en codigotxt
                String nombretxt = delimitar.next(); //guardando el siguient valor
                String secciontxt = delimitar.next(); //guardando el siguiente valor
               
                //Guardando los datos del archivo txt al array curso
		curso.add(new Cursos(codigotxt,nombretxt,secciontxt));
		}
		//Notificacion de que todo salio bien
                System.out.println("\u001B[32m Se cargo el archivo correctamente \u001B[0m");
                //se cierra el ojeto scanner
		scanner.close();
                cargado=true;
        //Erro si el usuario ingresa mal la direccion o el archivo no existe
	} catch (FileNotFoundException e) {
            System.err.println("No se encontro el archivo, Introduce una direccion valida");
            System.err.println("Ejemplo: C:\\Users\\YouPC\\Documents\\arhivo.txt ");
            cargarArchivo();
	}
    }
    
    //Funcion para cargar el archivo
    public void cargarArchivo(){
        //Notificacion si el usuario ya cargo un registro
        if(cargado==true){
            System.err.println("Atencion, ya has cargado antes un archivo");   
        }
        System.out.println("**********Cargar Archivo**********");
        String direccion;
        System.out.println("0. Regresar al menu principal");
        System.out.print("Ingrese la direccion del archivo: ");
        
        direccion = teclado.next(); // direccion del archivo txt
        
        //regresar al menu
        if (direccion.equals("0")){
            menu();
        } else {
            //llamar a la funcion guardarDatosDelTXT y se le pasa el parametro de la direccion del archivo txt
            guardarDatosDelTxt(direccion);
            menu();
        }    
    }
    
    //Generar el archivo txt
    public void generarReporte() {
        //ordenar los datos del array antes de generar el archivo txt
        ordenar();
        
        
        
        //si no hay ningun dato para generar el reporte
        if (!cargado && curso.isEmpty()) {
            System.err.println("No existen datos para generar un reporte");
            menu();
        //si existen datos
        }else{
            FileWriter flwriter = null;
            try {
                flwriter = new FileWriter(salida);//direccion para guardar el archivo txt
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                
                //Recorrer el array curso y escribir los valores en el archivo txt
                for(int i=0;i<curso.size();i++) {
                    bfwriter.write(curso.get(i).getCodigo() + "," + curso.get(i).getNombre() + "," + curso.get(i).getSeccion() + "\n");
                }

                bfwriter.close();
                
                //Todo salio bien
                System.out.println("\u001B[32m Archivo creado satisfactoriamente... \u001B[0m");
                
                System.out.println("¿Desas abrir el archivo? S/N: ");
                String pregunta = "";
                pregunta = teclado.next();
                //si el usuario quiere ver el txt generado
                if(pregunta.equals("S") || pregunta.equals("s")){
                    //abrir el txt
                    File objetofile = new File (salida); //direccion
                    Desktop.getDesktop().open(objetofile);
                    menu();
                }else{
                    menu();
                }
            //Si existe algun error        
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

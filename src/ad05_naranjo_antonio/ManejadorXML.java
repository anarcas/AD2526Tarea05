/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad05_naranjo_antonio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.XQuery;

/**
 * Clase encargada de la lógica de negocio y conexión con BaseX.
 * Gestiona la base de datos XML y la exportación de colecciones.
 * 
 * @author Antonio Naranjo Castillo
 * @version 1.0
 * @since 17/04/2026
 */
public class ManejadorXML {
    
    private final Context contexto;
    private final String nombreDB;
    private final String directorioDB;
    

    // Constructor de la conexión
    public ManejadorXML(String nombreBD, String rutaXML) throws Exception {
        this.contexto = new Context(); // Inicializamos el motor
        this.nombreDB = nombreBD;
        this.directorioDB = rutaXML;
        
        // Se realiza la conexión a la base de datos
        conectar();

    }
    
    // Método para abrir o crear la base de datos
    private void conectar() throws Exception {
        // Se crea el comando con el nombre de la base de datos y la ruta del fichero XML a consultar
        CreateDB db = new CreateDB(this.nombreDB, this.directorioDB);
        // Se ejecuta sobre el contexto de la clase
        db.execute(this.contexto);
    }

    // Método para cerrar el contexto al salir de la app
    public void cerrar() {
        if (contexto != null) {
            contexto.close();
        }
    }
    
  /*
    * Ejecuta una consulta XQuery y devuelve el resultado como String
    */
 public String consultar(String query) throws BaseXException{
    XQuery xq = new XQuery (query);
    return xq.execute(this.contexto);
}
 
 /**
     * Método que coordina la exportación masiva basada en los campos de la interfaz.
     * @param nombreCarpeta Ruta del directorio
     * @param entidades Array con nombres de ficheros (ej: "empleado")
     * @param xpaths Array con rutas XPath (ej: "//empleados/empleado")
     * @throws java.lang.Exception
     */
    public void exportarDesdeInterfaz(String nombreCarpeta, String[] entidades, String[] xpaths) throws Exception {
        File directorio = new File(nombreCarpeta);
        crearDirectorio(directorio);

        // Recorremos los arrays. Se asume que tienen el mismo tamaño.
        for (int i = 0; i < entidades.length; i++) {
            // Solo procesamos si el campo no está vacío
            if (!entidades[i].trim().isEmpty() && !xpaths[i].trim().isEmpty()) {
                procesarEntidad(directorio, entidades[i].trim(), xpaths[i].trim());
            }
        }
    }
 
 // Método para extrear la información de cada nodo y guardarlos en los ficheros
 public void procesarEntidad(File directorio, String entidad, String xpath) throws Exception{
 // Obtenemos el total de elementos
 String totalStr =consultar("count("+xpath+")");
 int total =Integer.parseInt(totalStr.trim());
 
 // Validación crítica de la información introducida por el usuario
    if (total == 0) {
        // Lanzamos un error descriptivo que atrapará el catch de la interfaz
        throw new Exception("La consulta para '" + entidad + "' (" + xpath + ") no ha devuelto resultados. "
                + "Verifique que la entidad y el XPath son correctos.");
    }
 
     System.out.println(">> Extrayendo "+total+" registros de: "+entidad);
     for (int i = 1; i <= total; i++) {
         // Consulta el nodo en la posición i
         String contenido=consultar(xpath+"["+i+"]");
         // Crear el fichero
         File archivo = new File(directorio, entidad+i);
         escribirFichero(archivo,contenido);
         
     }
 }
 
    // Método para crear el directorio
     public void crearDirectorio(File directorio) throws IOException {
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio '" + directorio.getName() + "' creado con éxito.");
            }
        } else {
            System.out.println("El directorio '" + directorio.getName() + "' ya existe.");
        }
    }
     
     // Método para crear el archivo
     public void crearFichero(File archivo) throws IOException {
        if (!archivo.exists()) {
            if (archivo.createNewFile()) {
                System.out.println("   Fichero '" + archivo.getName() + "' creado.");
            }
        } else {
            System.out.println("   Fichero '" + archivo.getName() + "' ya existe.");
        }
    }
     
     // Método para escribir en el fichero
     public void escribirFichero(File fichero, String contenido) throws  IOException{

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write(contenido);
            bw.newLine();
            System.out.println("   Escritura completada: " + fichero.getPath());

    }}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
 * Clase Ejercicio1 que extrae las colecciones Empleados, Clientes y Proveedores
 * de una base de datos BaseX y genera una copia local en el directorio
 * {@value #NOMBRE_BD}, creando un fichero de texto por cada registro
 * encontrado.
 *
 * @author Antonio Naranjo Castillo
 * @version 1.0
 * @since 17/04/2026
 */
public class Ejercicio1 {

    /**
     * Contexto de conexión con BaseX; representa la sesión activa.
     */
    private static Context contexto;

    /**
     * Ruta al fichero XML que se carga como base de datos.
     */
    private static final String DIRECTORIO_BD = "src/recursos/empresaTechSolutions.xml";

    /**
     * Nombre de la base de datos y del directorio de salida.
     */
    private static final String NOMBRE_BD = "Colecciones2526";

    /**
     * Código de escape ANSI para texto amarillo en consola.
     */
    private static final String AMARILLO = "\u001B[33m";

    /**
     * Código de escape ANSI para restablecer el color de consola.
     */
    private static final String RESET = "\u001B[0m";

    /**
     * Punto de entrada de la aplicación.Establece la conexión con BaseX, crea
     * el directorio de salida y delega el procesamiento de cada entidad.
     *
     * @param args argumentos de línea de comandos
     * @throws java.io.IOException para captar errores al crear el directorio
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        // Conectar con la base de datos
        contexto = new Context();

        try {
            // Carga el XML como base de datos BaseX y abre la sesión
            new CreateDB(NOMBRE_BD, DIRECTORIO_BD).execute(contexto);
            System.out.printf("%s>> Conexión con base de datos exitosa.%s%n", AMARILLO, RESET);

            // Se crea el objeto File una sola vez y se reutiliza en todos los métodos
            File directorio = new File(NOMBRE_BD);
            crearDirectorio(directorio);

            // Una llamada de procesamiento por cada entidad solicitada en la tarea
            procesarEntidad(directorio, "empleado", "//empleados/empleado");
            procesarEntidad(directorio, "cliente", "//clientes/cliente");
            procesarEntidad(directorio, "proveedor", "//proveedores/proveedor");

        } catch (BaseXException ex) {
            System.err.println(">> No se ha podido conectar con la base de datos.");
            System.err.println(ex);
        } finally {
            // Se garantiza el cierre de la conexión aunque ocurra una excepción
            contexto.close();
        }
    }

    /**
     * Consulta el número total de registros de una entidad y escribe cada uno
     * en un fichero de texto independiente dentro del directorio indicado.
     * <p>
     * Los ficheros se nombran con el patrón {@code entidadN.txt}, donde N es el
     * índice posicional en el XML.
     * </p>
     *
     * @param directorio carpeta de destino, ya creada
     * @param entidad prefijo del fichero: {@code "empleado"}, {@code "cliente"}
     * o {@code "proveedor"}
     * @param consultaXPath expresión XPath que selecciona la colección completa
     * de la entidad
     */
    private static void procesarEntidad(File directorio, String entidad, String consultaXPath) {
        try {
            // count() devuelve el número de nodos que coinciden con el XPath
            String queryRecuento = "count(" + consultaXPath + ")";
            String resultRecuento = consultar(queryRecuento);

            int total = Integer.parseInt(resultRecuento);
            System.out.printf("%n>> Total de %ss: %d%n", entidad, total);

            // Recorrer y guardar cada registro
            String query;
            String result;
            String extension;
            String nombreArchivo;
            File archivo;

            for (int i = 1; i <= total; i++) {
                // El predicado posicional [i] selecciona el nodo i-ésimo
                query = consultaXPath + "[" + i + "]";
                result = consultar(query);

                nombreArchivo = entidad + i;
                archivo = new File(directorio, nombreArchivo);

                crearFichero(archivo);
                escribirFichero(archivo, result);
            }

        } catch (BaseXException ex) {
            // Si la consulta falla, se informa y se abandona el procesamiento de esta entidad
            System.err.println(">> Error de BaseX al procesar '" + entidad + "': " + ex.getMessage());
        } catch (IOException ex) {
            // Si surge algún error al crear el fichero
            System.err.println(">> Error de E/S al procesar '" + entidad + "': " + ex.getMessage());
        }

    }

    /**
     * Ejecuta una consulta XQuery sobre el contexto activo y devuelve el
     * resultado.
     * <p>
     * Propaga {@link BaseXException} para que el llamador pueda gestionar el
     * error con el contexto adecuado, evitando retornos {@code null}.
     * </p>
     *
     * @param query expresión XQuery a ejecutar
     * @return resultado de la consulta como cadena de texto
     * @throws BaseXException si BaseX no puede ejecutar la consulta
     */
    public static String consultar(String query) throws BaseXException {
        XQuery xq = new XQuery(query);
        String result = xq.execute(contexto);
        return result;
    }

    /**
     * Crea el directorio indicado si no existe ({@link File#mkdirs()}).
     *
     * @param directorio objeto {@code File} que representa la carpeta a crear
     */
    public static void crearDirectorio(File directorio) throws IOException {
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio '" + directorio.getName() + "' creado con éxito.");
            }
        } else {
            System.out.println("El directorio '" + directorio.getName() + "' ya existe.");
        }
    }

    /**
     * Crea un fichero vacío en disco si todavía no existe.
     *
     * @param archivo objeto {@code File} que representa el fichero a crear
     * @throws java.io.IOException Si existe un error al crear el fichero
     */
    public static void crearFichero(File archivo) throws IOException {
        if (!archivo.exists()) {
            if (archivo.createNewFile()) {
                System.out.println("   Fichero '" + archivo.getName() + "' creado.");
            }
        } else {
            System.out.println("   Fichero '" + archivo.getName() + "' ya existe.");
        }
    }

    /**
     * Escribe el contenido recibido en el fichero indicado, sobreescribiendo
     * cualquier contenido previo.
     *
     * @param fichero objeto {@code File} que representa el fichero de destino
     * @param contenido texto a escribir
     */
    public static void escribirFichero(File fichero, String contenido) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write(contenido);
            bw.newLine();
            System.out.println("   Escritura completada: " + fichero.getPath());
        } catch (FileNotFoundException e) {
            System.err.println(">> Fichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println(">> Error de E/S al escribir: " + e.getMessage());
        }

    }

}

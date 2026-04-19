/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad05_naranjo_antonio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.XQuery;

/**
 * Clase encargada de la lógica de negocio y conexión con BaseX.
 * Actúa como capa de abstracción para la gestión de la base de datos XML
 * y la ejecución de consultas XQuery, garantizando la persistencia y manipulación de datos.
 *
 * @author Antonio Naranjo Castillo
 * @version 1.0
 * @since 17/04/2026
 */
public class ManejadorXML {

    // Contexto de BaseX para el manejo de sesiones de trabajo.
    private final Context contexto;
    private final String nombreDB;
    private final String directorioDB;

    /**
     * Inicializa la instancia del manejador y establece la conexión con el motor de base de datos.
     *
     * @param nombreBD Nombre asignado a la base de datos.
     * @param rutaXML  Ubicación en el sistema de archivos del fichero fuente XML.
     * @throws Exception Si ocurre un fallo crítico durante la inicialización.
     */
    public ManejadorXML(String nombreBD, String rutaXML) throws Exception {
        this.contexto = new Context();
        this.nombreDB = nombreBD;
        this.directorioDB = rutaXML;

        // Se invoca el procedimiento de conexión para preparar el entorno de datos.
        conectar();

    }

    /**
     * Ejecuta el proceso de creación o apertura de la base de datos en el motor BaseX.
     *
     * @throws Exception Si se produce un error durante la ejecución del comando CreateDB.
     */
    private void conectar() throws Exception {
        // Instancia el comando de creación de la base de datos a partir del fichero XML.
        CreateDB db = new CreateDB(this.nombreDB, this.directorioDB);
        // Se ejecuta la instrucción sobre el contexto de la aplicación.
        db.execute(this.contexto);
    }

    /**
     * Libera los recursos del sistema asociados al contexto activo de la base de datos.
     * Se debe ejecutar al finalizar la aplicación.
     */
        public void cerrar() {
        // Verifica la integridad del contexto antes de proceder al cierre para evitar excepciones.
        if (contexto != null) {
            contexto.close();
        }
        
    }

    /**
     * Ejecuta una sentencia XQuery sobre el contexto de BaseX activo.
     *
     * @param query Cadena de caracteres conteniendo la expresión XQuery a evaluar.
     * @return El resultado procesado de la consulta en formato String.
     * @throws BaseXException Si la consulta es sintácticamente errónea o falla la ejecución.
     */
    private String sentenciar(String query) throws BaseXException {
        // Se encapsula la consulta y se realiza la ejecución sobre el contexto persistente.
        XQuery xq = new XQuery(query);
        return xq.execute(this.contexto);
    }

    /**
     * Gestiona la creación de directorios en el sistema de archivos local.
     * Realiza una validación previa para evitar duplicidades o errores de
     * acceso.
     *
     * @param directorio Objeto File que representa la ruta a crear.
     * @throws IOException Si ocurre un error de permisos o el sistema operativo
     * deniega la operación.
     */
    public void crearDirectorio(File directorio) throws IOException {
        // Se valida la existencia del directorio antes de intentar su creación
        if (!directorio.exists()) {
            // Se invoca el comando de creación y se verifica el éxito de la operación
            if (directorio.mkdirs()) {
                System.out.println("Directorio '" + directorio.getName() + "' creado con éxito.");
            } else {
                // Se interrumpe la ejecución mediante una excepción si el sistema deniega el acceso
                throw new IOException("No se pudo crear el directorio: " + directorio.getName() + ". Compruebe los permisos.");
            }
        } else {
            System.out.println("El directorio '" + directorio.getName() + "' ya existe.");
        }
    }

    /**
     * Implementa la persistencia de datos en disco mediante la escritura de
     * ficheros.
     *
     * @param fichero Objeto File destino.
     * @param contenido Cadena de texto a volcar en el archivo.
     * @throws IOException Si surge una anomalía durante el flujo de salida de
     * datos.
     */
    private void escribirFichero(File fichero, String contenido) throws IOException {
        // Se utiliza un bloque try-with-resources para asegurar el cierre automático del flujo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write(contenido);
            bw.newLine();
            System.out.println("   Escritura completada: " + fichero.getPath());
        }
    }

    /**
     * Coordina el proceso de exportación masiva de datos estructurados, delegando
     * la lógica de procesamiento por cada entidad definida.
     *
     * @param nombreCarpeta Ruta del directorio de destino para la exportación.
     * @param entidades     Arreglo con los nombres identificativos de las entidades.
     * @param xpaths        Arreglo con las expresiones XPath correspondientes a cada entidad.
     * @throws BaseXException Si la consulta XQuery falla.
     * @throws IOException    Si ocurre un error durante la gestión de archivos.
     */
    public void recibirDatos(String nombreCarpeta, String[] entidades, String[] xpaths) throws BaseXException, IOException {
        File directorio = new File(nombreCarpeta);
        crearDirectorio(directorio);

        // Se itera sobre los conjuntos de datos, asumiendo una correspondencia biunívoca entre entidad y xpath, tal y como está diseñada la interfaz gráfica.
        for (int i = 0; i < entidades.length; i++) {
            procesarEntidad(directorio, entidades[i], xpaths[i]);
        }
    }

    /**
     * Ejecuta la extracción de información granular de un nodo XML específico y
     * garantiza su persistencia en archivos individuales.
     *
     * @param directorio Directorio base de exportación.
     * @param entidad Nombre de la entidad a procesar.
     * @param xpath Ruta de acceso a los nodos XML.
     * @throws IllegalArgumentException Si el conjunto de resultados está vacío.
     * @throws BaseXException Si falla la ejecución de la consulta.
     * @throws IOException Si falla la escritura en disco.
     */
    private void procesarEntidad(File directorio, String entidad, String xpath) throws IllegalArgumentException, BaseXException, IOException {
        // Se determina el volumen de datos mediante una consulta de conteo para la validación
        String totalStr = sentenciar("count(" + xpath + ")");
        int total = Integer.parseInt(totalStr.trim());

        // Se valida la integridad del resultado previo a la iteración
        if (total == 0) {
            throw new IllegalArgumentException("La consulta para '" + entidad + "' (" + xpath + ") no ha devuelto resultados. "
                    + "Verifique que la entidad y el XPath son correctos.");
        }

        System.out.println(">> Extrayendo " + total + " registros de: " + entidad);
        // Se realiza la iteración posicional para extraer cada nodo individualmente
        for (int i = 1; i <= total; i++) {
            String contenido = sentenciar(xpath + "[" + i + "]");
            File archivo = new File(directorio, entidad + i);
            escribirFichero(archivo, contenido);
        }
    }

    /**
     * Recupera y formatea el listado global de proveedores existentes en la base de datos.
     *
     * @return Una representación textual tabulada de la relación proveedores-países.
     * @throws BaseXException Si falla la consulta XQuery.
     * @throws IOException    Si ocurre un error durante la ejecución.
     */
    public String obtenerListadoProveedores() throws BaseXException, IOException {
        
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-12s%n", "NOMBRE", "PAÍS"));
        sb.append("----------------------------------\n");

        // Se ejecutan consultas independientes para nombres y países, sincronizando los resultados mediante índices
        String nombres = sentenciar("//proveedores/proveedor/nombre/text()");
        String paises = sentenciar("//proveedores/proveedor/pais/text()");

        // Se almacenan los datos relevantes en listas, cada elemento tras un salto de línea
        String[] arrayNombres = nombres.split("\\n");
        String[] arrayPaises = paises.split("\\n");

        for (int i = 0; i < arrayNombres.length; i++) {
            sb.append(String.format("%-20s %-12s%n", arrayNombres[i].trim(), arrayPaises[i].trim()));
        }

        return sb.toString();
    }

    /**
     * Obtiene el conjunto de identificadores únicos (IDs) asociados a la
     * entidad empleados.
     *
     * @return Un arreglo de Strings con los IDs normalizados.
     * @throws BaseXException Si ocurre un error en el motor.
     * @throws IOException Si ocurre un error de E/S.
     */
    public String[] obtenerListadoIDEmpleados() throws BaseXException, IOException {
        // Se ordena la consulta para mantener consistencia en la recuperación de datos
        // ID es clave primaria, no es necesario realizar una consulta de valores únicos
        String resultado = sentenciar("for $e in //empleados/empleado"
                + " order by $e/@id"
                + " return data($e/@id)");

        // Si no hay empleados, se devuelve un array vacío
        if (resultado == null || resultado.isEmpty()) {
            return new String[0];
        }

        String[] lista = resultado.split("\\n");
        int recuentoEmpleados = lista.length;
        String[] listaIds = new String[recuentoEmpleados];
        // Se normaliza cada identificador mediante el truncamiento de espacios
        for (int i = 0; i < recuentoEmpleados; i++) {
            listaIds[i] = lista[i].trim();

        }

        return listaIds;
        
    }

    /**
     * Genera un informe detallado de un empleado específico seleccionado por su
     * identificador.
     *
     * @param id Identificador único del empleado.
     * @return Cadena de caracteres con la información formateada.
     * @throws BaseXException Si falla la consulta XQuery.
     * @throws IOException Si ocurre un error de ejecución.
     */
    public String obtenerListadoEmpleados(String id) throws BaseXException, IOException {
        
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-16s %-12s%n", "EMPLEADO", "PUESTO"));
        sb.append("------------------------------------\n");

        // Se filtra la información mediante predicados de identidad (ID) en la consulta
        String nombres = sentenciar("for $e in //empleados/empleado"
                + " where $e/@id=" + id
                + " return data($e/nombre)");
        String puestos = sentenciar("for $e in //empleados/empleado"
                + " where $e/@id=" + id
                + " return data($e/puesto)");

        String[] arrayNombres = nombres.split("\\n");
        String[] arrayPuestos = puestos.split("\\n");

        for (int i = 0; i < arrayNombres.length; i++) {
            sb.append(String.format("%-16s %-12s%n", arrayNombres[i].trim(), arrayPuestos[i].trim()));
        }

        return sb.toString();
    }

    /**
     * Recupera una relación de productos cuyo precio supera un umbral definido
     * por el usuario.
     *
     * @param precio Valor numérico umbral para el filtrado de productos.
     * @return Informe tabulado con los productos que cumplen la condición.
     * @throws BaseXException Si falla la consulta XQuery.
     * @throws IOException Si ocurre un error de ejecución.
     */
    public String obtenerListadoProductos(Double precio) throws BaseXException, IOException {
        
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-12s%n", "PRODUCTO", "PRECIO"));
        sb.append("----------------------------------------\n");

        // Se ejecuta una consulta de filtrado condicional sobre el nodo precio
        String productos = sentenciar("for $p in //productos/producto"
                + " where $p/precio>" + precio
                + " return data($p/nombre)");
        String precios = sentenciar("for $p in //productos/producto"
                + " where $p/precio>" + precio
                + " return data($p/precio)");

        String[] arrayProductos = productos.split("\\n");
        String[] arrayPrecios = precios.split("\\n");

        for (int i = 0; i < arrayProductos.length; i++) {
            sb.append(String.format("%-20s %8.2f%n", arrayProductos[i].trim(), Double.parseDouble(arrayPrecios[i].trim())));
        }

        return sb.toString();
    }

    /**
     * Extrae el conjunto de identificadores únicos para la entidad pedidos.
     *
     * @return Arreglo de cadenas con los IDs de los pedidos ordenados.
     * @throws BaseXException Si la consulta es inválida.
     * @throws IOException Si ocurre un error de ejecución.
     */
    public String[] obtenerListadoIDPedidos() throws BaseXException, IOException {

        String resultado = sentenciar("for $p in //pedidos/pedido"
                                   + " order by $p/@id"
                                   + " return data($p/@id)");

        if (resultado == null || resultado.isEmpty()) {
            return new String[0];
        }

        String[] lista = resultado.split("\\n");
        int recuentoPedidos = lista.length;
        String[] listaIds = new String[recuentoPedidos];

        for (int i = 0; i < recuentoPedidos; i++) {
            listaIds[i] = lista[i].trim();
        }

        return listaIds;
        
    }
    
    /**
     * Realiza una inspección dinámica de los campos de un pedido seleccionado
     * mediante su ID. Aplica técnicas de concatenación en XQuery para obtener
     * pares clave-valor.
     *
     * @param id Identificador del pedido.
     * @return Representación textual detallada de los datos del pedido.
     * @throws BaseXException Si la consulta falla.
     * @throws IOException Si ocurre un error de ejecución.
     */
    public String obtenerListadoPedidos(String id) throws BaseXException, IOException {
        // Se genera una estructura de datos clave:valor mediante concatenación en XQuery
        String xQuery = "for $p in //pedidos/pedido[@id='" + id + "']//*[not(*)] "
                      + "return concat(name($p), '____', string($p))";

        String respuesta = sentenciar(xQuery);

        if (respuesta == null || respuesta.isEmpty()) {
            return "No se encontraron datos para el pedido: " + id;
        }

        // Se declara un mapa para organizar datos por etiquetas; mantiene el orden original.
        Map<String, List<String>> mapaPedidos = new LinkedHashMap<>();
        String[] resultados = respuesta.split("\\n");

        for (String linea : resultados) {
            if (!linea.trim().isEmpty()) {
                // Se divide la línea en etiqueta y valor usando el delimitador.
                String[] partes = linea.split("_{4}");
                String etiqueta = partes[0].toUpperCase().trim();
                String valor = (partes.length > 1) ? partes[1].trim() : "Sin valor";

            // Se obtiene la lista asociada a la etiqueta o la crea si no existe.
            List<String> listaValores = mapaPedidos.get(etiqueta);
            if (listaValores == null) {
                listaValores = new ArrayList<>();
                mapaPedidos.put(etiqueta, listaValores);
            }

            // Se agrega el valor a la lista asociada a su etiqueta.
            listaValores.add(valor);            }
        }

        // Se contruye el StringBuilder para generar la salida final.
        StringBuilder sb = new StringBuilder();
        sb.append("--- Datos del Pedido: ").append(id).append(" ---\n");
        // Se procesa cada línea aplicando una división por el delimitador definido
        for (Map.Entry<String, List<String>> entry : mapaPedidos.entrySet()) {
            String etiqueta = entry.getKey();
            List<String> valores = entry.getValue();
            // Se unen múltiples valores con coma si encuentra varios elementos.
            String valorFormateado = String.join(", ", valores);
            sb.append(String.format("%-15s --> %s%n", etiqueta, valorFormateado));
        }

        return sb.toString();
    }
    
    /**
     * Recupera la relación de productos únicos existentes en el catálogo.
     *
     * @return Arreglo de cadenas con los nombres de los productos ordenados.
     * @throws BaseXException Si falla la consulta XQuery.
     * @throws IOException Si ocurre un error de ejecución.
     */
    public String[] obtenerListadoNombresProductos() throws BaseXException, IOException {
        // Se utiliza distinct-values para garantizar la unicidad de los nombres de producto (no se trata de una clave primaria)
        String resultado = sentenciar("for $n in distinct-values(//productos/producto/nombre)"
                                   + " order by $n"
                                   + " return $n");

        if (resultado == null || resultado.isEmpty()) {
            return new String[0];
        }

        String[] lista = resultado.split("\\n");
        int recuentoProductos = lista.length;
        String[] listaProductos = new String[recuentoProductos];

        for (int i = 0; i < recuentoProductos; i++) {
            listaProductos[i] = lista[i].trim();

        }

        return listaProductos;
    }

    /**
     * Ejecuta una modificación atómica del precio de un producto específico.
     * Realiza un proceso de verificación tras la escritura para asegurar la
     * persistencia.
     *
     * @param nombreProducto Nombre del producto a actualizar.
     * @param nuevoPrecio Nuevo valor del precio.
     * @return Resumen textual de la operación realizada.
     * @throws BaseXException Si la actualización falla.
     * @throws IOException Si ocurre un error de ejecución.
     */
    public String actualizarPrecioProducto(String nombreProducto, Double nuevoPrecio) throws BaseXException, IOException {
        
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-12s%n", "PRODUCTO", "PRECIO"));
        sb.append("----------------------------------------\n");

        // Ejecución de la instrucción de actualización
        sentenciar("replace value of node //producto[nombre='" + nombreProducto + "']/precio with " + nuevoPrecio);

        // Ejecución de la consulta de verificación
        String precioActualizado = sentenciar("data(//producto[nombre='" + nombreProducto + "']/precio)");

        if (precioActualizado == null || precioActualizado.isEmpty()) {
            System.err.println("DEBUG: No se encontró el producto con nombre: " + nombreProducto);
        }

        sb.append(String.format("%-20s %6.2f", nombreProducto, Double.parseDouble(precioActualizado)));

        return sb.toString();
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ad05_naranjo_antonio;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.basex.core.BaseXException;

/**
 * Clase que implementa la interfaz gráfica de usuario (GUI) para la gestión de
 * la aplicación. Actúa como la capa de presentación, coordinando la interacción
 * del usuario con el manejador de la base de datos XML (ManejadorXML).
 * Implementa el control de estado de los componentes según la disponibilidad de
 * la conexión.
 *
 * @author Antonio Naranjo Castillo
 * @version 1.0
 * @since 18/04/2026
 */
public class InterfazGrafica extends javax.swing.JFrame {

    // Instancia del controlador responsable de las operaciones sobre la base de datos XML.
    private ManejadorXML manejador;

    /**
     * Constructor de la clase. Inicializa los componentes de la interfaz,
     * establece la disposición espacial y configura el estado inicial de los
     * controles mediante la desactivación de funcionalidades dependientes de la
     * base de datos.
     */
    public InterfazGrafica() {
        initComponents();
        // Ajuste dinámico de las dimensiones de la ventana al contenido generado
        this.pack();
        // Centrado de la ventana en el área de visualización del escritorio
        this.setLocationRelativeTo(null);
        // Configuración inicial de seguridad: deshabilitar operaciones sin conexión activa
        desactivarComponentes();

    }

    /**
     * Alimenta el componente JComboBox con los identificadores de empleados
     * extraídos de la base de datos.
     *
     * @throws BaseXException Si la consulta de recuperación de IDs falla.
     * @throws IOException Si ocurre un error durante el acceso a los datos.
     */
    private void iniciarComboIDempleados() throws BaseXException, IOException {
        // Limpieza previa del componente para evitar duplicidad de datos en recargas
        jComboBoxIdsEmpleados.removeAllItems();
        String[] ids = manejador.obtenerListadoIDEmpleados();
        // Carga iterativa de los identificadores en el selector
        for (String id : ids) {
            jComboBoxIdsEmpleados.addItem(id);
        }

    }

    /**
     * Sincroniza el JComboBox con los identificadores de pedidos disponibles en
     * el sistema.
     *
     * @throws BaseXException Si la consulta XQuery falla.
     * @throws IOException Si ocurre un error de E/S.
     */
    private void iniciarComboIDpedidos() throws BaseXException, IOException {
        jComboBoxIdsPedidos.removeAllItems();
        String[] ids = manejador.obtenerListadoIDPedidos();
        for (String id : ids) {
            jComboBoxIdsPedidos.addItem(id);
        }

    }

    /**
     * Carga el catálogo de nombres de productos en el componente de selección.
     *
     * @throws BaseXException Si falla la recuperación de productos.
     * @throws IOException Si se produce un error de ejecución.
     */
    private void iniciarComboNombresProductos() throws BaseXException, IOException {
        jComboBoxNombresProductos.removeAllItems();
        String[] nombres = manejador.obtenerListadoNombresProductos();
        for (String nombre : nombres) {
            jComboBoxNombresProductos.addItem(nombre);
        }

    }

    /**
     * Habilita los componentes de la interfaz de usuario una vez establecida la
     * conexión con la base de datos, restringiendo simultáneamente la edición
     * de campos de configuración.
     */
    private void activarComponentes() {

        // Activación de la lógica de negocio y listados
        jButtonInicarBD.setEnabled(false);
        jButtonCerrarBD.setEnabled(true);
        jButtonGenerarFicheros.setEnabled(true);
        jButtonListarProveedores.setEnabled(true);
        jButtonListarEmpleados.setEnabled(true);
        jButtonListarProductos.setEnabled(true);
        jButtonListarPedidos.setEnabled(true);
        jButtonActualizarPrecio.setEnabled(true);

        // Bloqueo de campos de configuración para evitar inconsistencias durante la sesión
        jTextFieldNomBD.setEditable(false);
        jTextFieldDirectorioBDxml.setEditable(false);

    }

    /**
     * Deshabilita los componentes de la interfaz de usuario que requieren una
     * conexión activa, restableciendo la capacidad de edición para los
     * parámetros de inicialización de la base de datos.
     */
    private void desactivarComponentes() {

        // Restricción de acceso a funcionalidades de consulta y manipulación
        jButtonInicarBD.setEnabled(true);
        jButtonCerrarBD.setEnabled(false);
        jButtonGenerarFicheros.setEnabled(false);
        jButtonListarProveedores.setEnabled(false);
        jButtonListarEmpleados.setEnabled(false);
        jButtonListarProductos.setEnabled(false);
        jButtonListarPedidos.setEnabled(false);
        jButtonActualizarPrecio.setEnabled(false);

        // Habilitación de la edición para reconectar o reconfigurar la base de datos
        jTextFieldNomBD.setEditable(true);
        jTextFieldDirectorioBDxml.setEditable(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomBD = new javax.swing.JTextField();
        jTextFieldDirectorioBDxml = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDir = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEnt3 = new javax.swing.JTextField();
        jTextFieldEnt2 = new javax.swing.JTextField();
        jTextFieldEnt1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldxPath1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldxPath2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldxPath3 = new javax.swing.JTextField();
        jButtonGenerarFicheros = new javax.swing.JButton();
        jButtonInicarBD = new javax.swing.JButton();
        jButtonCerrarBD = new javax.swing.JButton();
        jPanelEntidad = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButtonListarProveedores = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaListadoProveedores = new javax.swing.JTextArea();
        jPanelXpath = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaListadoEmpleados = new javax.swing.JTextArea();
        jButtonListarEmpleados = new javax.swing.JButton();
        jComboBoxIdsEmpleados = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonSalir = new javax.swing.JButton();
        jPanelXpath1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaListadoProductos = new javax.swing.JTextArea();
        jButtonListarProductos = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jPanelXpath2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaListadoPedidos = new javax.swing.JTextArea();
        jButtonListarPedidos = new javax.swing.JButton();
        jComboBoxIdsPedidos = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jPanelXpath3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaListadoPrecio = new javax.swing.JTextArea();
        jButtonActualizarPrecio = new javax.swing.JButton();
        jComboBoxNombresProductos = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldNuevoPrecio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de datos XML TechSolutions | AD Tarea 05 - Antonio Naranjo");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("Ejercicio 1 >> Generar ficheros de las entidades indicadas previas consultas xPath");
        jLabel1.setToolTipText("Generar ficheros de las siguiente entidades");

        jTextFieldNomBD.setText("Colecciones2526");
        jTextFieldNomBD.setToolTipText("Nombre del directorio donde se guardarán los ficheros de las entidades (en la carpeta raíz del proyecto)");

        jTextFieldDirectorioBDxml.setText("src/recursos/empresaTechSolutions.xml");
        jTextFieldDirectorioBDxml.setToolTipText("Ruta relativa de acceso a la base de datos XML");

        jLabel5.setText("Directorio: ");

        jLabel6.setText("Ruta BD XML: ");

        jLabel7.setText("Nombre base de datos: ");

        jTextFieldDir.setText("Colecciones2526");
        jTextFieldDir.setToolTipText("Nombre del directorio donde se guardarán los ficheros de las entidades (en la carpeta raíz del proyecto)");

        jLabel2.setText("Entidad 1: ");

        jLabel3.setText("Entidad 2: ");

        jLabel4.setText("Entidad 3: ");

        jTextFieldEnt3.setText("proveedor");
        jTextFieldEnt3.setToolTipText("Nombre de la entidad 3");

        jTextFieldEnt2.setText("cliente");
        jTextFieldEnt2.setToolTipText("Nombre de la entidad 2");

        jTextFieldEnt1.setText("empleado");
        jTextFieldEnt1.setToolTipText("Nombre de la entidad 1");

        jLabel8.setText("xPath entidad 1:");

        jTextFieldxPath1.setText("//empleados/empleado");
        jTextFieldxPath1.setToolTipText("Consulta xPath de la entidad 1");

        jLabel9.setText("xPath entidad 2:");

        jTextFieldxPath2.setText("//clientes/cliente");
        jTextFieldxPath2.setToolTipText("Consulta xPath de la entidad 2");

        jLabel10.setText("xPath entidad 3:");

        jTextFieldxPath3.setText("//proveedores/proveedor");
        jTextFieldxPath3.setToolTipText("Consulta xPath de la entidad 3");

        jButtonGenerarFicheros.setBackground(new java.awt.Color(51, 255, 102));
        jButtonGenerarFicheros.setText("Generar ficheros de entidades");
        jButtonGenerarFicheros.setToolTipText("Haz clic para crear los ficheros de las entidades en el directorio definido");
        jButtonGenerarFicheros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarFicherosActionPerformed(evt);
            }
        });

        jButtonInicarBD.setBackground(new java.awt.Color(0, 255, 255));
        jButtonInicarBD.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonInicarBD.setText("Iniciar BD XML");
        jButtonInicarBD.setToolTipText("Haz clic para iniciar la base de datos XML");
        jButtonInicarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicarBDActionPerformed(evt);
            }
        });

        jButtonCerrarBD.setBackground(new java.awt.Color(255, 51, 102));
        jButtonCerrarBD.setText("Cerrar BD XML");
        jButtonCerrarBD.setToolTipText("Haz clic para cerrar la base de datos XML");
        jButtonCerrarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarBDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(81, 81, 81)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldEnt1)
                                    .addComponent(jTextFieldEnt2)
                                    .addComponent(jTextFieldEnt3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDir)
                                    .addComponent(jTextFieldNomBD))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonGenerarFicheros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(27, 27, 27)
                                .addComponent(jTextFieldDirectorioBDxml))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldxPath2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldxPath3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonInicarBD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonCerrarBD))
                                    .addComponent(jTextFieldxPath1))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldNomBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldDirectorioBDxml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInicarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCerrarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldEnt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldEnt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldEnt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldxPath1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextFieldxPath2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldxPath3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButtonGenerarFicheros, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelEntidad.setBackground(new java.awt.Color(255, 204, 204));

        jLabel11.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel11.setText("Ejercicio 2.a >> Listar proveedores");
        jLabel11.setToolTipText("Listar todos los proveedores");

        jButtonListarProveedores.setBackground(new java.awt.Color(255, 102, 102));
        jButtonListarProveedores.setText("Listar proveedores");
        jButtonListarProveedores.setToolTipText("Haz clic para listar todos los proveedores que exiten en la base de datos xml");
        jButtonListarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarProveedoresActionPerformed(evt);
            }
        });

        jTextAreaListadoProveedores.setColumns(20);
        jTextAreaListadoProveedores.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaListadoProveedores.setRows(5);
        jScrollPane1.setViewportView(jTextAreaListadoProveedores);

        javax.swing.GroupLayout jPanelEntidadLayout = new javax.swing.GroupLayout(jPanelEntidad);
        jPanelEntidad.setLayout(jPanelEntidadLayout);
        jPanelEntidadLayout.setHorizontalGroup(
            jPanelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEntidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonListarProveedores)
                    .addGroup(jPanelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanelEntidadLayout.setVerticalGroup(
            jPanelEntidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEntidadLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonListarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanelXpath.setBackground(new java.awt.Color(204, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel12.setText("Ejercicio 2.b >> Listar empleado");
        jLabel12.setToolTipText("Listar empleado en función del ID seleccionado");

        jTextAreaListadoEmpleados.setColumns(20);
        jTextAreaListadoEmpleados.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaListadoEmpleados.setRows(5);
        jScrollPane2.setViewportView(jTextAreaListadoEmpleados);

        jButtonListarEmpleados.setBackground(new java.awt.Color(0, 153, 255));
        jButtonListarEmpleados.setText("Listar empleados");
        jButtonListarEmpleados.setToolTipText("Haz clic para listar los empleados según el ID seleccionado");
        jButtonListarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarEmpleadosActionPerformed(evt);
            }
        });

        jLabel13.setText("id empleado: ");

        javax.swing.GroupLayout jPanelXpathLayout = new javax.swing.GroupLayout(jPanelXpath);
        jPanelXpath.setLayout(jPanelXpathLayout);
        jPanelXpathLayout.setHorizontalGroup(
            jPanelXpathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpathLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelXpathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXpathLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIdsEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jButtonListarEmpleados))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelXpathLayout.setVerticalGroup(
            jPanelXpathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpathLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanelXpathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxIdsEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonListarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jButtonSalir.setBackground(new java.awt.Color(255, 0, 51));
        jButtonSalir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalir.setText("Salir de la aplicación");
        jButtonSalir.setToolTipText("Haz clic para crear los ficheros de las entidades en el directorio definido");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelXpath1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel14.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel14.setText("Ejercicio 2.c >> Listar productos");
        jLabel14.setToolTipText("Listar productos que superen el indicado");

        jTextAreaListadoProductos.setColumns(20);
        jTextAreaListadoProductos.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaListadoProductos.setRows(5);
        jScrollPane3.setViewportView(jTextAreaListadoProductos);

        jButtonListarProductos.setBackground(new java.awt.Color(204, 204, 0));
        jButtonListarProductos.setText("Listar productos");
        jButtonListarProductos.setToolTipText("Haz clic para listar los productos cuyo precio es mayor al indicado");
        jButtonListarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarProductosActionPerformed(evt);
            }
        });

        jLabel15.setText("precio >  ");
        jLabel15.setToolTipText("");

        jTextFieldPrecio.setToolTipText("Se mostrarán productos de mayor precio al indicado (tipo de dato Double)");

        javax.swing.GroupLayout jPanelXpath1Layout = new javax.swing.GroupLayout(jPanelXpath1);
        jPanelXpath1.setLayout(jPanelXpath1Layout);
        jPanelXpath1Layout.setHorizontalGroup(
            jPanelXpath1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpath1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelXpath1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXpath1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonListarProductos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(9, 9, 9))
        );
        jPanelXpath1Layout.setVerticalGroup(
            jPanelXpath1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpath1Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelXpath1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jButtonListarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jPanelXpath2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel16.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel16.setText("Ejercicio 2.d >> Listar pedidos");
        jLabel16.setToolTipText("Listar pedidos en función del ID seleccionado");

        jTextAreaListadoPedidos.setColumns(20);
        jTextAreaListadoPedidos.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaListadoPedidos.setRows(5);
        jScrollPane4.setViewportView(jTextAreaListadoPedidos);

        jButtonListarPedidos.setBackground(new java.awt.Color(153, 102, 255));
        jButtonListarPedidos.setText("Listar pedidos");
        jButtonListarPedidos.setToolTipText("Haz clic para listar los pedidos según el ID seleccionado");
        jButtonListarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarPedidosActionPerformed(evt);
            }
        });

        jLabel17.setText("id pedido: ");

        javax.swing.GroupLayout jPanelXpath2Layout = new javax.swing.GroupLayout(jPanelXpath2);
        jPanelXpath2.setLayout(jPanelXpath2Layout);
        jPanelXpath2Layout.setHorizontalGroup(
            jPanelXpath2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpath2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelXpath2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXpath2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIdsPedidos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonListarPedidos))
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanelXpath2Layout.setVerticalGroup(
            jPanelXpath2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpath2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(jPanelXpath2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBoxIdsPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonListarPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelXpath3.setBackground(new java.awt.Color(255, 204, 153));

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel18.setText("Ejercicio 2.e >> Actualizar precio producto");
        jLabel18.setToolTipText("Actualizar el precio de un producto en función del ID seleccionado");

        jTextAreaListadoPrecio.setColumns(20);
        jTextAreaListadoPrecio.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaListadoPrecio.setRows(5);
        jScrollPane5.setViewportView(jTextAreaListadoPrecio);

        jButtonActualizarPrecio.setBackground(new java.awt.Color(255, 204, 51));
        jButtonActualizarPrecio.setText("Actualizar precio");
        jButtonActualizarPrecio.setToolTipText("Haz clic para actualizar el precio del producto según el ID seleccionado y la cantidad introducida");
        jButtonActualizarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarPrecioActionPerformed(evt);
            }
        });

        jLabel19.setText("Nombre producto: ");

        jLabel20.setText("Nuevo precio: ");

        jTextFieldNuevoPrecio.setToolTipText("Introducir el nuevo precio del producto (tipo de dato Double)");

        javax.swing.GroupLayout jPanelXpath3Layout = new javax.swing.GroupLayout(jPanelXpath3);
        jPanelXpath3.setLayout(jPanelXpath3Layout);
        jPanelXpath3Layout.setHorizontalGroup(
            jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpath3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelXpath3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(jPanelXpath3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanelXpath3Layout.createSequentialGroup()
                        .addGroup(jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelXpath3Layout.createSequentialGroup()
                                .addComponent(jTextFieldNuevoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButtonActualizarPrecio))
                            .addComponent(jComboBoxNombresProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelXpath3Layout.setVerticalGroup(
            jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXpath3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBoxNombresProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelXpath3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldNuevoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonActualizarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelXpath3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelXpath1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelXpath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelXpath2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelXpath3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelXpath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelXpath2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelXpath1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Gestiona el evento de generación de ficheros XML a partir de las rutas y
     * entidades configuradas por el usuario. Realiza validaciones de entrada
     * antes de delegar la tarea al manejador de la base de datos.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonGenerarFicherosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarFicherosActionPerformed
        // TODO add your handling code here:

        String mensaje;
        // Validación de la integridad del objeto manejador antes de operar
        if (manejador != null) {
            try {
                // Extracción y normalización de parámetros de configuración
                String directorioStr = jTextFieldDir.getText().trim();

                // Verificación de campos obligatorios para prevenir excepciones de E/S
                if (directorioStr.isEmpty()) {
                    mensaje = "Debe especificar un nombre para el directorio de destino, el campo de texto no puede estar vacío.";
                    System.err.println(mensaje);
                    throw new IllegalArgumentException(mensaje);

                } else {
                    String entidad1 = jTextFieldEnt1.getText().trim();
                    String entidad2 = jTextFieldEnt2.getText().trim();
                    String entidad3 = jTextFieldEnt3.getText().trim();

                    if (entidad1.isEmpty() || entidad2.isEmpty() || entidad3.isEmpty()) {
                        mensaje = "Alguna de las entidades no están definidas, el campo de texto no puede estar vacío.";
                        System.err.println(mensaje);
                        throw new IllegalArgumentException(mensaje);
                    }

                    String xPath1 = jTextFieldxPath1.getText().trim();
                    String xPath2 = jTextFieldxPath2.getText().trim();
                    String xPath3 = jTextFieldxPath3.getText().trim();

                    if (xPath1.isEmpty() || xPath2.isEmpty() || xPath3.isEmpty()) {
                        mensaje = "Alguna de las consultas xPath están vacías, el campo de texto no puede estar vacío.";
                        System.err.println(mensaje);
                        throw new IllegalArgumentException(mensaje);
                    }

                    String[] entidades = {entidad1, entidad2, entidad3};
                    String[] xpaths = {xPath1, xPath2, xPath3};

                    // Ejecución del procedimiento masivo de exportación
                    manejador.recibirDatos(directorioStr, entidades, xpaths);

                    JOptionPane.showMessageDialog(this, "Proceso finalizado correctamente.\nFicheros generados en: " + directorioStr);
                }

            } catch (IllegalArgumentException ex) {
                // Capturar errores de validación previa (campos vacíos)
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (BaseXException ex) {
                // Se captan errores de base de datos XML (BaseX)
                JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                // Se captan errores de lectura/escritura de archivos
                JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Se captar otros errores que se puedan dar
                JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error XML", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Técnicamente no debería ocurrir si el botón está desactivado, pero se mantiene por seguridad.
            JOptionPane.showMessageDialog(this, "La base de datos no está iniciada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButtonGenerarFicherosActionPerformed

    /**
     * Inicializa la conexión con el motor BaseX utilizando los parámetros
     * introducidos por el usuario. Tras una conexión exitosa, activa los
     * componentes de la interfaz y precarga los datos en los selectores.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonInicarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicarBDActionPerformed
        // TODO add your handling code here:
        String mensaje;

        try {

            String nombre = jTextFieldNomBD.getText().trim();
            String ruta = jTextFieldDirectorioBDxml.getText().trim();

            if (nombre.isEmpty() || ruta.isEmpty()) {
                mensaje = "El nombre de la base de datos y la ruta están vacías, los campos de texto no pueden estar vacíos.";
                System.err.println(mensaje);
                throw new IllegalArgumentException(mensaje);
            } else {
                // Instanciación del manejador, lo cual establece el contexto con BaseX
                manejador = new ManejadorXML(nombre, ruta);
                JOptionPane.showMessageDialog(this, "Conexión con BaseX establecida con éxito.");

                // Habilitación de la lógica operativa y carga inicial de los componentes de selección
                activarComponentes();
                iniciarComboIDempleados();
                iniciarComboIDpedidos();
                iniciarComboNombresProductos();
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        } catch (BaseXException ex) {
            JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error XML", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonInicarBDActionPerformed

    /**
     * Gestiona la terminación controlada de la aplicación, solicitando
     * confirmación al usuario y asegurando el cierre de los recursos de la base
     * de datos.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (manejador != null) {
                manejador.cerrar();
            }
            System.exit(0);
        }
    }//GEN-LAST:event_jButtonSalirActionPerformed

    /**
     * Intercepta el evento de cierre de ventana para asegurar la liberación de
     * recursos.
     *
     * @param evt Evento de cierre de ventana.
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (manejador != null) {
                manejador.cerrar();
            }
            System.exit(0); // Cerramos todo
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * Recupera y muestra el listado de proveedores en el área de texto
     * correspondiente.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonListarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarProveedoresActionPerformed
        // TODO add your handling code here:
        if (manejador != null) {
            try {
                String listado = manejador.obtenerListadoProveedores();
                jTextAreaListadoProveedores.setText(listado);
            } catch (BaseXException ex) {
                JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error XML", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonListarProveedoresActionPerformed

    /**
     * Ejecuta la recuperación de datos de un empleado específico seleccionado
     * mediante el componente ComboBox.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonListarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarEmpleadosActionPerformed
        // TODO add your handling code here:

        String id = (String) jComboBoxIdsEmpleados.getSelectedItem();

        if (manejador != null) {
            try {
                String listado = manejador.obtenerListadoEmpleados(id);
                jTextAreaListadoEmpleados.setText(listado);
            } catch (BaseXException ex) {
                JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error XML", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButtonListarEmpleadosActionPerformed

    /**
     * Desconecta de forma segura el manejador de la base de datos y restablece
     * el estado de la interfaz gráfica a modo desconectado.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonCerrarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarBDActionPerformed
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas cerrar la Base de Datos XML?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (manejador != null) {
                manejador.cerrar();
            }
            // Restauración del estado de la interfaz
            desactivarComponentes();
        }


    }//GEN-LAST:event_jButtonCerrarBDActionPerformed

    /**
     * Filtra y muestra productos cuyo precio es superior al valor especificado
     * en el campo de texto.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonListarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarProductosActionPerformed
        // TODO add your handling code here:

        if (manejador != null) {
            // Variable para almacenar el mensaje de salida
            String mensaje;

            try {

                // Validación del precio introducido
                Double precio;
                try {
                    precio = Double.parseDouble(jTextFieldPrecio.getText().trim().replace(",", "."));
                    if (precio <= 0) {
                        mensaje = "Error: El precio debe ser mayor que cero.";
                        System.err.println(mensaje);
                        throw new IllegalArgumentException(mensaje);
                    }
                } catch (NumberFormatException e) {
                    mensaje = "Error: El precio debe ser un número decimal válido (tipo dato Double).";
                    System.err.println(mensaje);
                    throw new IllegalArgumentException(mensaje);
                }

                String listado = manejador.obtenerListadoProductos(precio);
                jTextAreaListadoProductos.setText(listado);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (BaseXException ex) {
                JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.toString());
            }
        }
    }//GEN-LAST:event_jButtonListarProductosActionPerformed

    /**
     * Recupera y muestra el detalle de un pedido específico seleccionado en el
     * ComboBox.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonListarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarPedidosActionPerformed
        // TODO add your handling code here:

        String id = (String) jComboBoxIdsPedidos.getSelectedItem();

        if (manejador != null) {
            try {
                String listado = manejador.obtenerListadoPedidos(id);
                jTextAreaListadoPedidos.setText(listado);
            } catch (BaseXException ex) {
                JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error XML", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonListarPedidosActionPerformed

    /**
     * Actualiza el valor del precio para un producto seleccionado y actualiza
     * la vista.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButtonActualizarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarPrecioActionPerformed
        // TODO add your handling code here:

        String nombreProducto = (String) jComboBoxNombresProductos.getSelectedItem();

        if (manejador != null) {
            String mensaje;
            try {
                // Validación y conversión del nuevo precio solicitado
                Double nuevoPrecio;
                try {
                    nuevoPrecio = Double.parseDouble(jTextFieldNuevoPrecio.getText().trim().replace(",", "."));
                    if (nuevoPrecio <= 0) {
                        mensaje = "Error: El nuevo precio debe ser mayor que cero.";
                        System.err.println(mensaje);
                        throw new IllegalArgumentException(mensaje);
                    }
                } catch (NumberFormatException e) {
                    mensaje = "Error: El nuevo precio debe ser un número decimal válido (tipo dato Double).";
                    System.err.println(mensaje);
                    throw new IllegalArgumentException(mensaje);
                }

                // Ejecución de la actualización atómica
                String listado = manejador.actualizarPrecioProducto(nombreProducto, nuevoPrecio);
                jTextAreaListadoPrecio.setText(listado);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (BaseXException ex) {
                JOptionPane.showMessageDialog(this, "Error de Base de Datos XML (BaseX): " + ex.getMessage(), "Error en Motor XML", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error de E/S: " + e.getMessage(), "Error E/S", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.toString());
            }
        }

    }//GEN-LAST:event_jButtonActualizarPrecioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizarPrecio;
    private javax.swing.JButton jButtonCerrarBD;
    private javax.swing.JButton jButtonGenerarFicheros;
    private javax.swing.JButton jButtonInicarBD;
    private javax.swing.JButton jButtonListarEmpleados;
    private javax.swing.JButton jButtonListarPedidos;
    private javax.swing.JButton jButtonListarProductos;
    private javax.swing.JButton jButtonListarProveedores;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxIdsEmpleados;
    private javax.swing.JComboBox<String> jComboBoxIdsPedidos;
    private javax.swing.JComboBox<String> jComboBoxNombresProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelEntidad;
    private javax.swing.JPanel jPanelXpath;
    private javax.swing.JPanel jPanelXpath1;
    private javax.swing.JPanel jPanelXpath2;
    private javax.swing.JPanel jPanelXpath3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextAreaListadoEmpleados;
    private javax.swing.JTextArea jTextAreaListadoPedidos;
    private javax.swing.JTextArea jTextAreaListadoPrecio;
    private javax.swing.JTextArea jTextAreaListadoProductos;
    private javax.swing.JTextArea jTextAreaListadoProveedores;
    private javax.swing.JTextField jTextFieldDir;
    private javax.swing.JTextField jTextFieldDirectorioBDxml;
    private javax.swing.JTextField jTextFieldEnt1;
    private javax.swing.JTextField jTextFieldEnt2;
    private javax.swing.JTextField jTextFieldEnt3;
    private javax.swing.JTextField jTextFieldNomBD;
    private javax.swing.JTextField jTextFieldNuevoPrecio;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldxPath1;
    private javax.swing.JTextField jTextFieldxPath2;
    private javax.swing.JTextField jTextFieldxPath3;
    // End of variables declaration//GEN-END:variables
}

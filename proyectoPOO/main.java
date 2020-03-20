package proyectoPOO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        // Inicializamos VentanaPrincipal
        VentanaPrincipal miVentana = new VentanaPrincipal();
    }
}

class VentanaPrincipal extends JFrame implements ActionListener {
    // Declaramos los 3 botones
    JButton botonRegistrarProducto  = new JButton("Registrar Stock del producto");
    JButton botonRealizarVenta      = new JButton("Realizar venta");
    JButton botonSalir              = new JButton("Salir");

    static VentanaRegistrarStock ventanaRegistroProducto = new VentanaRegistrarStock();
    static VentanaRealizarVenta ventanaRealizarVenta = new VentanaRealizarVenta();

    VentanaPrincipal(){
        setBounds(1000, 500, 300, 300);  // Medidas iniciales de la ventana y una posicion inicial
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Que debe hacer la ventana al cerrarse
        setTitle("App punto de venta");                     // Establece un título
        setLayout(new BorderLayout());
        setVisible(true);                                   // Hace visible la ventana (invisible por defecto)

        add(botonRegistrarProducto, BorderLayout.NORTH);    // El boton va en la parte superior
        add(botonRealizarVenta, BorderLayout.CENTER);       // El boton va en la parte media
        add(botonSalir, BorderLayout.SOUTH);                // El boton va en la parte inferior

        // Ponemos los 3 botones a la escucha
        botonRegistrarProducto.addActionListener(this);
        botonRealizarVenta.addActionListener(this);
        botonSalir.addActionListener(this);
    }
    public void actionPerformed(ActionEvent accion){
        if (accion.getActionCommand() == "Registrar Stock del producto"){
            ventanaRegistroProducto.setVisible(true);
        }else if (accion.getActionCommand() == "Realizar venta"){
            ventanaRealizarVenta.setVisible(true);
        }else {     // Salir
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    // Devuelve una lista de los articulos almacenados, util para JComboBox
    public static String[] getListaArticulos(){
        String [] nombresArticulos = new String[VentanaRegistrarStock.articulosAgregados + 3];
        // + 3 agrega 4 filas adicionales para EL SUBTOTAL, IVA Y TOTAL
        for (int i = 0; i < VentanaRegistrarStock.articulosAgregados; i++){
            nombresArticulos[i] = VentanaRegistrarStock.misArticulos[i][1];
        }
        return nombresArticulos;
    }
}

class VentanaRegistrarStock extends JFrame implements ActionListener{
    // Lista de articulos array
    static String [][] misArticulos = new String[100][3];

    // Numero de artículos ya ingresados (cuenta desde 0)
    static int articulosAgregados = 2;

    // Declaración e inicialización del array que almacena el nombre de las columnas
    private String [] nombresColumnas ={"Unidades", "Nombre del Producto", "Precio Unitario"};

    // Sur
    JButton botonRegresar = new JButton("<= Regresar");

    VentanaRegistrarStock(){
        // Datos iniciales de la tabla
        misArticulos[0][0] = "10";
        misArticulos[0][1] = "Mouse Logitech";
        misArticulos[0][2] = "12";

        misArticulos[1][0] = "30";
        misArticulos[1][1] = "Monitor Samsung 19,5";
        misArticulos[1][2] = "99";

        setBounds(1000, 400, 1050, 450);  // Medidas iniciales de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Que debe hacer la ventana al cerrarse
        setTitle("Registrar Stock del producto");           // Establece el titulo de la ventana
        setLayout(new BorderLayout());                      // Establece la disposición de los elementos

        // Centro - TODO Establecer un margen
        JTable tablaProductos = new JTable(misArticulos, nombresColumnas);

        // Le agregamos un Scroll al JTable
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        // Pone el boton a la escucha
        botonRegresar.addActionListener(this);

        add(new FrameRegistraStockZonaSuperior(), BorderLayout.NORTH);
        add(tablaProductos, BorderLayout.CENTER);
        add(botonRegresar, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent accion) {
        if (accion.getActionCommand() == "<= Regresar"){
            VentanaPrincipal.ventanaRegistroProducto.setVisible(false);
        }
    }
}

// Esta capa se agrega a la ventana Registra Stock
// TODO Establecer un layout apropiado para ordenar mejor los componentes
class FrameRegistraStockZonaSuperior extends JPanel implements ActionListener{
    // static int articulosAgregados = 2;

    // Declaracion de componentes de la GUI
    // 1° Fila Norte
    JLabel etiquetaNombre = new JLabel("Nombre: ");
    JTextField campoNombreArticulo = new JTextField(10);

    // 2° Fila Norte
    JLabel etiquetaTipo = new JLabel("Tipo: ");
    ButtonGroup grupoBtnTipoArticulo = new ButtonGroup();
    JRadioButton radioBtnComestible = new JRadioButton("Comestible");
    JRadioButton radioBtnUtensilio  = new JRadioButton("Utensilio");
    JRadioButton radioBtnOficina    = new JRadioButton("Oficina");
    JRadioButton radioBtnIndustrial = new JRadioButton("Industrial");

    // 3° Fila Norte
    JLabel etiquetaPrecioUnitario = new JLabel("Precio Unitario:");
    JTextField campoPrecioUnitario = new JTextField(10);
    JButton botonAgregarProducto = new JButton("Agregar producto");

    FrameRegistraStockZonaSuperior(){
        // Relaciona los botones de tipo Radio
        grupoBtnTipoArticulo.add(radioBtnComestible);
        grupoBtnTipoArticulo.add(radioBtnUtensilio);
        grupoBtnTipoArticulo.add(radioBtnOficina);
        grupoBtnTipoArticulo.add(radioBtnIndustrial);

        // Componentes acoplados en el area NORTE del BorderLayout
        add(etiquetaNombre);
        add(campoNombreArticulo);

        add(etiquetaTipo);
        add(radioBtnComestible);
        add(radioBtnUtensilio);
        add(radioBtnOficina);
        add(radioBtnIndustrial);

        add(etiquetaPrecioUnitario);
        add(campoPrecioUnitario);
        add(botonAgregarProducto);

        // Pone los botones a la escucha
        botonAgregarProducto.addActionListener(this);

        radioBtnComestible.addActionListener(this);
        radioBtnUtensilio.addActionListener(this);
        radioBtnOficina.addActionListener(this);
        radioBtnIndustrial.addActionListener(this);

        // Estable un alias al radioButton
        radioBtnComestible.setActionCommand("Comestible");
        radioBtnUtensilio.setActionCommand("Utensilio");
        radioBtnOficina.setActionCommand("Oficina");
        radioBtnIndustrial.setActionCommand("Industrial");
    }

    @Override
    public void actionPerformed(ActionEvent accion) {

        if (accion.getActionCommand() == "Agregar producto") {
            String nombreProducto = campoNombreArticulo.getText();
            if (campoNombreArticulo.getText().isEmpty()) {
                JOptionPane.showInputDialog("El campo del nombre del artículo no puede estar vacio");
            } else { // SI llega aqui ha cumplido todos los requisitos
                try{
                    double precioUnitario = Double.parseDouble(campoPrecioUnitario.getText()); // Lo uso como filtro para el try
                    String tipo = grupoBtnTipoArticulo.getSelection().getActionCommand();
                    VentanaRegistrarStock.misArticulos[VentanaRegistrarStock.articulosAgregados][0] = campoPrecioUnitario.getText();
                    VentanaRegistrarStock.misArticulos[VentanaRegistrarStock.articulosAgregados][1] = campoNombreArticulo.getText();
                    VentanaRegistrarStock.misArticulos[VentanaRegistrarStock.articulosAgregados][2] = campoPrecioUnitario.getText();

                    // Aumenta en 1 el contador para rellenar el siguiente registro
                    VentanaRegistrarStock.articulosAgregados++;

                    // Borrar los campos
                    campoNombreArticulo.setText("");
                    campoPrecioUnitario.setText("");

                    // Recargar la tabla o toda las capas
                    // TODO Hacer el obj ventana estatico y llamarlo para que repinte
                    VentanaPrincipal.ventanaRegistroProducto.repaint();

                }catch (Exception e){
                    e.printStackTrace();
                    JOptionPane.showInputDialog("El precio no es del tipo numérico");
                }
            }
        }
    }
}

class VentanaRealizarVenta extends JFrame implements ActionListener{
    // Nombres de las columnas de la tabla
    String [] nombresColumnas ={"Unidades", "Nombre del Producto", "Precio Unitario", "Precio total"};

    static JTable tablaFactura = new JTable();
    static String [][] arrayFactura = new String[100][4];

    JButton botonSalir = new JButton("<= Regresar");

    VentanaRealizarVenta(){
        setBounds(100, 100, 1000, 500);      // Medidas iniciales de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         // Que debe hacer la ventana al cerrarse
        setTitle("Registrar Venta");                            // Establece el titulo de la ventana
        setLayout(new BorderLayout());
        setVisible(false);                                   // Hace la ventana visible

        // Despues de la ultima posición del articulo en columna [3] agregar los textos: subtotal, iva y total.

        arrayFactura[0][2] = "Subtotal";
        arrayFactura[1][2] = "IVA";
        arrayFactura[2][2] = "Total";

        // La nueva tabla tiene [articulosAgregados + 3][4 columnas] - TODO Establecer un margen
        tablaFactura = new JTable(arrayFactura, nombresColumnas);

        botonSalir.addActionListener(this);

        add(new FrameRealizarVentaSuperior(), BorderLayout.NORTH);

        // Le agregamos un Scroll al JTable
        add(new JScrollPane(tablaFactura), BorderLayout.CENTER);

        add(botonSalir, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("<= Regresar")){
            VentanaPrincipal.ventanaRealizarVenta.setVisible(false);
            System.out.println("Si llega aqui");
        }
    }
}

class FrameRealizarVentaSuperior extends JPanel implements ActionListener{
    JLabel etiquetaSeleccioneProducto = new JLabel("Seleccione el producto");
    // Declara el JComboBox y lo inicializa con un array que es traido a travez de un metodo de la clase VentanaRegistraStock
    JComboBox desplegableListaArticulos = new JComboBox( VentanaPrincipal.getListaArticulos() );
    JLabel etiquetaNumUnidades = new JLabel("Numero de unidades: ");
    JTextField campoNumUnidades = new JTextField(10);
    JButton botonAgregarAlCarro = new JButton("Agregar al carro");

    static int articulosEnElCarro = 0;
    static double subtotal = 0;

    FrameRealizarVentaSuperior(){

        // Poner a la escucha
        desplegableListaArticulos.addActionListener(this);
        campoNumUnidades.addActionListener(this);
        desplegableListaArticulos.addActionListener(this);
        botonAgregarAlCarro.addActionListener(this);

        // Agrega los componentes al frame
        add(etiquetaSeleccioneProducto);
        add(desplegableListaArticulos);
        add(etiquetaNumUnidades);
        add(campoNumUnidades);
        add(botonAgregarAlCarro);
    }

    @Override
    public void actionPerformed(ActionEvent accion) {
        // Declaracion inicial:
        double unidadesRequeridas = 0;
        double precioArticulo = 0;
        double precioUnidadesPorPrecioUnitario = 0;

        // Variables auxiliares
        int numArticulo = desplegableListaArticulos.getSelectedIndex();
        String nombreArticulo = desplegableListaArticulos.getSelectedItem().toString();
        int unidadesDisponibles = Integer.parseInt(VentanaRegistrarStock.misArticulos[numArticulo][0]);

        // Si presiona Agregar al carro
        if (accion.getActionCommand().equals("Agregar al carro")) {
            // Mas Variables auxiliares
            try {
                unidadesRequeridas = Double.parseDouble(campoNumUnidades.getText());
                precioArticulo = Double.parseDouble(VentanaRegistrarStock.misArticulos[numArticulo][2]);
            } catch (Exception e) {
                System.out.println("El campo unidad no es valido");
            }

            // Comprobar si se tiene el num de unidades que pide y tienes que ser >0
            if (unidadesRequeridas <= unidadesDisponibles && unidadesRequeridas > 0) {  // Se han cumplido todos los requisitos
                precioUnidadesPorPrecioUnitario = precioArticulo * unidadesRequeridas;
                subtotal += precioUnidadesPorPrecioUnitario;

                // El array sera desplazado 1 fila hacia abajo y sera sobreescrita la primera posición
                for(int i = 3 + articulosEnElCarro; i > 0; i--){
                    VentanaRealizarVenta.arrayFactura[i][0] = VentanaRealizarVenta.arrayFactura[i - 1][0];
                    VentanaRealizarVenta.arrayFactura[i][1] = VentanaRealizarVenta.arrayFactura[i - 1][1];
                    VentanaRealizarVenta.arrayFactura[i][2] = VentanaRealizarVenta.arrayFactura[i - 1][2];
                }

                // En la columna 4 se debe mostrar el resultado de multiplicar la cantidad por el precio unitario
                VentanaRealizarVenta.arrayFactura[0][3] = String.valueOf(precioUnidadesPorPrecioUnitario);

                // Sobreescribimos la primera fila
                VentanaRealizarVenta.arrayFactura[0][0] = String.valueOf(unidadesRequeridas);
                VentanaRealizarVenta.arrayFactura[0][1] = nombreArticulo;
                VentanaRealizarVenta.arrayFactura[0][2] = String.valueOf(precioArticulo);
                VentanaRealizarVenta.arrayFactura[0][3] = String.valueOf(precioUnidadesPorPrecioUnitario);

                // Rellenamos el subtotal, el iva y el total

                articulosEnElCarro++;

                // Calcula el subtotal sumando todos los precios total hasta la var. subtotal
                VentanaRealizarVenta.arrayFactura[articulosEnElCarro][3] = String.valueOf( subtotal );
                double iva = subtotal * 0.12;
                double total = subtotal + iva;

                // Los pasamos a la tabla
                VentanaRealizarVenta.arrayFactura[articulosEnElCarro + 1][3] = String.valueOf( iva );
                VentanaRealizarVenta.arrayFactura[articulosEnElCarro + 2][3] = String.valueOf( total );

                // Recargamos la interfaz
                VentanaPrincipal.ventanaRealizarVenta.repaint();

            } else {
                System.out.println("El número es invalido o no se disponen de tantas unidades");
            }
        }
    }
}

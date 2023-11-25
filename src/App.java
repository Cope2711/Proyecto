package Raya.src;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;

// Clase principal que representa la aplicación del juego Tetris
public class App extends JPanel implements KeyListener {

    // Atributos para configurar la pantalla del juego
    String PantallaTitulo = "Tetris!!!";
    int PantallaWidth = 800;
    int PantallaHeight = 400;
    Color PantallaColorFondo = Color.CYAN;

    // Instancias de las clases que gestionan el tablero, las piezas y los movimientos
    Table table = new Table();
    Pieza pieza = new Pieza();
    Movimientos movimientos = new Movimientos();
    Colisiones colisiones = new Colisiones();

    // Método principal que inicia la aplicación
    public static void main(String[] args) throws Exception {
        // Crear una instancia de la aplicación
        App Juego = new App();
        // Inicializar y configurar el tablero
        Juego.table.CrearTablero();
        // Configurar y mostrar la pantalla del juego
        Juego.CrearPantalla();
        // Generar una nueva pieza para comenzar el juego
        Juego.GenerarNuevaPieza();
        // Configurar un scheduler para ejecutar la caída de la pieza a intervalos regulares
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> Juego.EjecutarCaidaDePieza(), 0, 200, TimeUnit.MILLISECONDS);
    }

    // Método para generar una nueva pieza en el juego
    public void GenerarNuevaPieza() {
        pieza.InsertarNuevaPieza();
    }

    // Método que ejecuta la caída de la pieza en el juego
    public void EjecutarCaidaDePieza() {
        String[][] Table = table.getTable();
        int[][] PiezaActual = pieza.getPiezaActual();
        // Verificar si la pieza puede descender en el tablero
        if (colisiones.SePuedeBajar(Table, PiezaActual)) {
            // Realizar el movimiento de caída de la pieza
            movimientos.CaidaDePieza(Table, PiezaActual);
        } else {
            // Si la pieza no puede descender más, convertirla en estática en el tablero
            colisiones.ConvertirAEstatica(Table, PiezaActual);
            // Restablecer la posición de la pieza y generar una nueva pieza
            pieza.setPiezaPositionActual(0);
            GenerarNuevaPieza();
        }
    }

    // Método para crear la ventana del juego
    public void CrearPantalla() {
        JFrame frame = new JFrame(this.PantallaTitulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(this.PantallaHeight, this.PantallaWidth);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    // Método que pinta el tablero en la pantalla
    private void PintarTablero(Graphics graphics) {
        // Obtener dimensiones y propiedades del tablero y la pieza actual
        int TableWidth = table.getTableWidth();
        int TableHeight = table.getTableHeight();
        int TablePositionX = table.getTablePositionX();
        int TablePositionY = table.getTablePositionY();
        int TablePixelSizeX = table.getTablePixelSizeX();
        int TablePixelSizeY = table.getTablePixelSizeY();
        Color TableVoidColor = table.getTableVoidColor();
        String TableVoidValue = table.getTableVoidValue();
        String TablePiezaValue = table.getTablePiezaValue();
        String TablePiezaStaticValue = table.getTablePiezaStaticValue();
        String[][] Table = table.getTable();
        Color PiezaColor = pieza.getPiezaColor();
        
        // Iterar sobre cada celda del tablero y dibujarla en la pantalla
        for (int height = 0; height < TableHeight; height++) {
            for (int width = 0; width < TableWidth; width++) {
                int x = TablePositionX + width * TablePixelSizeX;
                int y = TablePositionY + height * TablePixelSizeY;
                String Value = Table[width][height];
                
                // Dibujar celda vacía
                if (Value == TableVoidValue) {
                    graphics.setColor(TableVoidColor);
                    graphics.drawRect(x, y, TablePixelSizeX, TablePixelSizeY);
                } 
                // Dibujar celda ocupada por una pieza en movimiento
                else if (Value == TablePiezaValue) {
                    graphics.setColor(PiezaColor);
                    graphics.fillRect(x, y, TablePixelSizeX, TablePixelSizeY);
                } 
                // Dibujar celda ocupada por una pieza estática en el tablero
                else if (Value == TablePiezaStaticValue) {
                    graphics.setColor(PiezaColor);
                    graphics.fillRect(x, y, TablePixelSizeX, TablePixelSizeY);
                }
            }
        }
    }

    // Métodos de la interfaz KeyListener para manejar eventos de teclado
    public void keyPressed(KeyEvent e) {
        String[][] Table = table.getTable();
        int[][] PiezaActual = pieza.getPiezaActual();
        int IdPieza = pieza.getIdPieza();
        char tecla = Character.toLowerCase(e.getKeyChar());
        
        // Manejar las teclas de movimiento y rotación
        if (tecla == 'd') {
            movimientos.MoverDercha(Table, PiezaActual);
        } else if (tecla == 'a') {
            movimientos.MoverIzquierda(Table, PiezaActual);
        } else if (tecla == 'w') {
            movimientos.RotarPieza(Table, PiezaActual, IdPieza);
        }
    }

    // Métodos no utilizados de la interfaz KeyListener
    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    // Sobrescribe el método paintComponent para redibujar la pantalla
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        PintarTablero(graphics);
        repaint();
    }
}

package Raya.src;
import java.awt.Color;
import java.util.ArrayList;

// Clase que representa el tablero del juego Tetris
public class Table {

    // Propiedades del tablero
    int TableWidth = 10;
    int TableHeight = 22;
    int TablePositionX = 50;
    int TablePositionY = 50;
    int TablePixelSizeX = 30;
    int TablePixelSizeY = 30;
    String TableVoidValue = "0";
    String TablePiezaValue = "1";
    String TablePiezaStaticValue = "2";
    Color TableVoidColor = Color.GRAY;
    String[][] Table = new String[TableWidth][TableHeight];

    // Función para crear el tablero llenándolo con valores predeterminados
    public void CrearTablero() {
        for (int height = 0; height < TableHeight; height++) {
            for (int width = 0; width < TableWidth; width++) {
                this.Table[width][height] = TableVoidValue;
            }
        }
    }

    // Función que verifica si una línea en el tablero está completamente llena
    public boolean EstaLlenaLaLinea(String[][] Tablero, int PixelPositionY) {
        int ContadorDePixelStatic = 0;

        // Contar el número de píxeles estáticos en una línea específica
        for (int width = 0; width < TableWidth; width++) {
            if (Tablero[width][PixelPositionY] == this.TablePiezaStaticValue) {
                ContadorDePixelStatic++;
            }
        }
        // Devolver verdadero si todos los píxeles en la línea son estáticos
        return ContadorDePixelStatic == this.TableWidth;
    }

    // Función que vacía una línea llena en el tablero y desplaza las piezas hacia abajo
    public void VaciarLinea(String[][] Tablero, ArrayList<Integer> PixelsPositionsY) {
        for (Integer PixelPositionY : PixelsPositionsY) {
            // Verificar si la línea está llena
            if (EstaLlenaLaLinea(Tablero, PixelPositionY)) {
                // Vaciar la línea estableciendo los valores de píxeles en vacío
                for (int width = 0; width < TableWidth; width++) {
                    Tablero[width][PixelPositionY] = this.TableVoidValue;
                }
                // Desplazar las piezas hacia abajo después de vaciar la línea
                RecorrerPiezasAlVaciar(Tablero, PixelPositionY);
            }
        }
    }

    // Función que recorre las piezas hacia abajo después de vaciar una línea
    public void RecorrerPiezasAlVaciar(String[][] Tablero, int PixelPositionY) {
        for (int width = 0; width < this.TableWidth; width++) {
            for (int height = PixelPositionY; height > 0; height--) {
                // Desplazar cada pieza una posición hacia abajo
                Tablero[width][height] = Tablero[width][height - 1];
            }
        }
        // Actualizar el estado del tablero en la clase Table
        setTable(Tablero);
    }

    // Métodos getter y setter para acceder y modificar propiedades de la clase
    public int getTableWidth() {
        return TableWidth;
    }

    public void setTableWidth(int tableWidth) {
        TableWidth = tableWidth;
    }

    public int getTableHeight() {
        return TableHeight;
    }

    public void setTableHeight(int tableHeight) {
        TableHeight = tableHeight;
    }

    public int getTablePositionX() {
        return TablePositionX;
    }

    public void setTablePositionX(int tablePositionX) {
        TablePositionX = tablePositionX;
    }

    public int getTablePositionY() {
        return TablePositionY;
    }

    public void setTablePositionY(int tablePositionY) {
        TablePositionY = tablePositionY;
    }

    public int getTablePixelSizeX() {
        return TablePixelSizeX;
    }

    public void setTablePixelSizeX(int tablePixelSizeX) {
        TablePixelSizeX = tablePixelSizeX;
    }

    public int getTablePixelSizeY() {
        return TablePixelSizeY;
    }

    public void setTablePixelSizeY(int tablePixelSizeY) {
        TablePixelSizeY = tablePixelSizeY;
    }

    public String getTableVoidValue() {
        return TableVoidValue;
    }

    public void setTableVoidValue(String tableVoidValue) {
        TableVoidValue = tableVoidValue;
    }

    public String getTablePiezaValue() {
        return TablePiezaValue;
    }

    public void setTablePiezaValue(String tablePiezaValue) {
        TablePiezaValue = tablePiezaValue;
    }

    public String getTablePiezaStaticValue() {
        return TablePiezaStaticValue;
    }

    public void setTablePiezaStaticValue(String tablePiezaStaticValue) {
        TablePiezaStaticValue = tablePiezaStaticValue;
    }

    public Color getTableVoidColor() {
        return TableVoidColor;
    }

    public void setTableVoidColor(Color tableVoidColor) {
        TableVoidColor = tableVoidColor;
    }

    public String[][] getTable() {
        return Table;
    }

    public void setTable(String[][] table) {
        Table = table;
    }
}

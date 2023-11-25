package Raya.src;

import java.util.ArrayList;

// Clase que maneja las colisiones en el juego Tetris
public class Colisiones {

    // Instancias de las clases que gestionan el tablero y las piezas
    Table table = new Table();
    Pieza pieza = new Pieza();

    // Atributos obtenidos de la clase Table para facilitar el acceso
    String TablePiezaStaticValue = table.getTablePiezaStaticValue();
    int TableWidth = table.getTableWidth();
    int TableHeight = table.getTableHeight();

    // Función que comprueba si hay colisión en el próximo movimiento hacia abajo y genera una nueva pieza si es necesario
    public boolean SePuedeBajar(String[][] Table, int[][] PiezaActual) {
        int ComprobadorDeColision = 0;
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            int PixelPositionX = PiezaActual[Pixel][0];
            int PixelPositionY = PiezaActual[Pixel][1];
            if (PiezaActual[Pixel][1] + 1 < TableHeight && Table[PixelPositionX][PixelPositionY + 1] != TablePiezaStaticValue) {
                ComprobadorDeColision++;
            }
        }
        return ComprobadorDeColision == PiezaActual.length;
    }

    // Función que comprueba si hay colisión en la dirección -x
    public boolean SePuedeMoverIzquierda(String[][] Table, int[][] PiezaActual) {
        int ComprobadorDeColision = 0;
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            int PixelPositionX = PiezaActual[Pixel][0];
            int PixelPositionY = PiezaActual[Pixel][1];
            if (PiezaActual[Pixel][0] - 1 >= 0 && Table[PixelPositionX - 1][PixelPositionY] != TablePiezaStaticValue) {
                ComprobadorDeColision++;
            }
        }
        return ComprobadorDeColision == PiezaActual.length;
    }

    // Función que comprueba si hay colisión en la dirección +x
    public boolean SePuedeMoverDerecha(String[][] Table, int[][] PiezaActual) {
        int ComprobadorDeColision = 0;
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            int PixelPositionX = PiezaActual[Pixel][0];
            int PixelPositionY = PiezaActual[Pixel][1];
            if (PiezaActual[Pixel][0] + 1 < this.TableWidth && Table[PixelPositionX + 1][PixelPositionY] != TablePiezaStaticValue) {
                ComprobadorDeColision++;
            }
        }
        return ComprobadorDeColision == PiezaActual.length;
    }

    // Función que comprueba si hay colisión al rotar la pieza
    public boolean SePuedeRotar(String[][] Table, int[][] PiezaNueva, int[][] PiezaActual, int ValorX, int ValorY) {
        int ComprobadorDeColisionPiezaEstatica = 0;
        int ComprobadorDeColisionPared = 0;
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            try {
                if (Table[PiezaNueva[Pixel][0] + ValorX][PiezaNueva[Pixel][1] + ValorY] != this.TablePiezaStaticValue) {
                    ComprobadorDeColisionPiezaEstatica++;
                }
            } catch (Exception e) {}
            if (PiezaNueva[Pixel][0] + ValorX <= this.TableWidth && PiezaNueva[Pixel][1] + ValorY < this.TableHeight) {
                ComprobadorDeColisionPared++;
            }
        }
        return ComprobadorDeColisionPiezaEstatica == PiezaNueva.length && ComprobadorDeColisionPared == PiezaNueva.length;
    }

    // Función que convierte la pieza en estática en el tablero después de una colisión hacia abajo
    public void ConvertirAEstatica(String[][] Table, int[][] PiezaActual) {
        ArrayList<Integer> PixelsPositionsY = new ArrayList<>();
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            int PixelPositionX = PiezaActual[Pixel][0];
            int PixelPositionY = PiezaActual[Pixel][1];
            Table[PixelPositionX][PixelPositionY] = TablePiezaStaticValue;
            PixelsPositionsY.add(PixelPositionY);
        }
        table.VaciarLinea(Table, PixelsPositionsY);
    }
}

package Raya.src;

// Clase que gestiona los movimientos de las piezas en el juego Tetris
public class Movimientos {

    // Atributos de la clase
    int SigRotacion = 0;
    Table table = new Table();
    Pieza pieza = new Pieza();
    Colisiones colisiones = new Colisiones();

    int TableWidth = table.getTableWidth();

    // Función que realiza el movimiento de caída de la pieza hacia abajo
    public void CaidaDePieza(String[][] Table, int[][] PiezaActual) {
        String TableVoidValue = table.getTableVoidValue();
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            int PixelPositionX = PiezaActual[Pixel][0];
            int PixelPositionY = PiezaActual[Pixel][1];
            Table[PixelPositionX][PixelPositionY] = TableVoidValue;
            PiezaActual[Pixel][1] = PiezaActual[Pixel][1] + 1;
        }
        // Actualiza la posición de la pieza y refleja los cambios en el tablero
        pieza.setPiezaActual(PiezaActual);
        pieza.ActualizarPieza(Table);
    }

    // Función que aumenta la coordenada x de la pieza
    public void MoverDercha(String[][] Table, int[][] PiezaActual) {
        String TableVoidValue = table.getTableVoidValue();
        // Verifica si la pieza puede moverse a la derecha
        if (colisiones.SePuedeMoverDerecha(Table, PiezaActual)) {
            for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
                int PixelPositionX = PiezaActual[Pixel][0];
                int PixelPositionY = PiezaActual[Pixel][1];
                Table[PixelPositionX][PixelPositionY] = TableVoidValue;
                PiezaActual[Pixel][0] = PiezaActual[Pixel][0] + 1;
            }
        }
        // Actualiza la posición de la pieza y refleja los cambios en el tablero
        pieza.setPiezaActual(PiezaActual);
        pieza.ActualizarPieza(Table);
    }

    // Función que disminuye la coordenada x de la pieza
    public void MoverIzquierda(String[][] Table, int[][] PiezaActual) {
        String TableVoidValue = table.getTableVoidValue();
        // Verifica si la pieza puede moverse a la izquierda
        if (colisiones.SePuedeMoverIzquierda(Table, PiezaActual)) {
            for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
                int PixelPositionX = PiezaActual[Pixel][0];
                int PixelPositionY = PiezaActual[Pixel][1];
                Table[PixelPositionX][PixelPositionY] = TableVoidValue;
                PiezaActual[Pixel][0] = PiezaActual[Pixel][0] - 1;
            }
        }
        // Actualiza la posición de la pieza y refleja los cambios en el tablero
        pieza.setPiezaActual(PiezaActual);
        pieza.ActualizarPieza(Table);
    }

    // Función que realiza la rotación de la pieza
    public void RotadorDePieza(String[][] Table, int[][] PiezaActual, int PiezaPositionNext, int IdPieza) {
        int[][] PiezaNueva = pieza.Piezas(IdPieza, PiezaPositionNext);
        int ValorX = PiezaActual[0][0] - PiezaNueva[0][0];
        int ValorY = PiezaActual[0][1] - PiezaNueva[0][1];
        String TableVoidValue = table.getTableVoidValue();

        // Verifica si la pieza puede rotar y realiza la rotación si es posible
        if (colisiones.SePuedeRotar(Table, PiezaNueva, PiezaActual, ValorX, ValorY)) {
            for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
                int PixelPositionX = PiezaActual[Pixel][0];
                int PixelPositionY = PiezaActual[Pixel][1];
                PiezaNueva[Pixel][0] = PiezaNueva[Pixel][0] + ValorX;
                PiezaNueva[Pixel][1] = PiezaNueva[Pixel][1] + ValorY;
                PiezaActual[Pixel][0] = PiezaNueva[Pixel][0];
                PiezaActual[Pixel][1] = PiezaNueva[Pixel][1];
                Table[PixelPositionX][PixelPositionY] = TableVoidValue;
            }
            // Actualiza la posición de la pieza y refleja los cambios en el tablero
            pieza.ActualizarPieza(Table);
        }
    }

    // Función que gestiona la rotación de la pieza y actualiza su posición
    public void RotarPieza(String[][] Table, int[][] PiezaActual, int IdPieza) {
        int PositionValues = pieza.PiezasPositionValues(IdPieza);
        int PiezaPositionNext = pieza.getPiezaPositionActual();
        // Verifica si la pieza puede rotar y actualiza la posición de la pieza
        if (PositionValues > PiezaPositionNext) {
            PiezaPositionNext++;
        } else {
            PiezaPositionNext = 0;
        }
        RotadorDePieza(Table, PiezaActual, PiezaPositionNext, IdPieza);
        pieza.setPiezaPositionActual(PiezaPositionNext);
    }
}

package Raya.src;

import java.awt.Color;
import java.util.Random;

// Clase que representa una pieza en el juego Tetris
public class Pieza {

    // Índice de inicio para la generación de piezas
    int PiezaInicioIndex = 4;
    // Posición actual de la pieza en su ciclo de rotación
    int PiezaPositionActual = 0;
    // Color de la pieza
    Color PiezaColor = Color.MAGENTA;
    // Coordenadas actuales de la pieza en el tablero
    int[][] PiezaActual;
    // Identificador de la pieza
    public int IdPieza;

    // Instancia de la clase Table para acceder a propiedades del tablero
    Table table = new Table();

    // Método que actualiza la posición de la pieza en el tablero
    public void ActualizarPieza(String[][] Table) {
        // Obtener el valor que representa una celda ocupada por una pieza en el tablero
        String TablePiezaValue = table.getTablePiezaValue();
        // Iterar sobre cada pixel de la pieza
        for (int Pixel = 0; Pixel < PiezaActual.length; Pixel++) {
            int PixelPositionX = PiezaActual[Pixel][0];
            int PixelPositionY = PiezaActual[Pixel][1];
            // Actualizar la posición en el tablero con el valor que representa la presencia de la pieza
            Table[PixelPositionX][PixelPositionY] = TablePiezaValue;
        }
        // Actualizar el estado del tablero en la clase Table
        table.setTable(Table);
    }

    // Método que genera una nueva pieza en el tablero con sus coordenadas iniciales
    public void InsertarNuevaPieza() {
        // Generar un identificador aleatorio para la nueva pieza
        Random random = new Random();
        IdPieza = random.nextInt(7);
        // Obtener las coordenadas iniciales de la nueva pieza
        this.PiezaActual = Piezas(IdPieza, PiezaPositionActual);
        // Ajustar las coordenadas para que la pieza aparezca en la parte superior del tablero
        for (int Pixel = 0; Pixel < this.PiezaActual.length; Pixel++) {
            this.PiezaActual[Pixel][0] = this.PiezaActual[Pixel][0] + this.PiezaInicioIndex;
        }
    }

    // Método que devuelve las coordenadas de una pieza específica en su posición de rotación actual
    public int[][] Piezas(int PiezaId, int PiezaPosition) {
        // Definir las coordenadas de cada tipo de pieza en sus diferentes posiciones de rotación
        int[][][] Hero =          new int[][][]{{{0, 1}, {0, 0}, {0, 2}, {0, 3}}, {{1, 0}, {0, 0}, {2, 0}, {3, 0}}};
        int[][][] LNormal =       new int[][][]{{{0, 1}, {0, 0}, {0, 2}, {1, 2}}, {{1, 0}, {2, 0}, {0, 0}, {0, 1}}, {{1, 1}, {1, 2}, {1, 0}, {0, 0}}, {{1, 1}, {0, 1}, {2, 1}, {2, 0}}};
        int[][][] LInvertido =    new int[][][]{{{1, 1}, {1, 0}, {1, 2}, {0, 2}}, {{1, 1}, {2, 1}, {0, 1}, {0, 0}}, {{0, 1}, {0, 2}, {0, 0}, {1, 0}}, {{1, 0}, {0, 0}, {2, 0}, {2, 1}}};
        int[][][] ZetaNormal =    new int[][][]{{{1, 0}, {0, 0}, {1, 1}, {2, 1}}, {{1, 1}, {1, 0}, {0, 1}, {0, 2}}};
        int[][][] ZetaInvertido = new int[][][]{{{1, 0}, {2, 0}, {1, 1}, {0, 1}}, {{0, 1}, {0, 0}, {1, 1}, {1, 2}}};
        int[][][] Cuadrado =      new int[][][]{{{0, 0}, {1, 0}, {0, 1}, {1, 1}}};
        int[][][] Podio =         new int[][][]{{{1, 1}, {0, 1}, {1, 0}, {2, 1}}, {{0, 1}, {0, 0}, {1, 1}, {0, 2}}, {{1, 0}, {2, 0}, {1, 1}, {0, 0}}, {{1, 1}, {1, 2}, {0, 1}, {1, 0}}};

        // Seleccionar el conjunto de coordenadas según el tipo de pieza y su posición de rotación
        switch (PiezaId) {
            case 0 : {return Hero[PiezaPosition];}
            case 1 : {return LNormal[PiezaPosition];}
            case 2 : {return LInvertido[PiezaPosition];}
            case 3 : {return ZetaNormal[PiezaPosition];}
            case 4 : {return ZetaInvertido[PiezaPosition];}
            case 5 : {return Cuadrado[PiezaPosition];}
            case 6 : {return Podio[PiezaPosition];}
            default: return new int[0][0];
        }
    }

    // Método que devuelve el número total de posiciones de rotación para una pieza
    public int PiezasPositionValues(int IdPieza) {
        // Contador para almacenar el número de posiciones de rotación válidas
        int PositionValues = 0;
        // Iterar hasta encontrar una posición de rotación no válida y devolver el contador
        while (true) {
            try {
                // Intentar obtener las coordenadas para la posición actual
                Piezas(IdPieza, PositionValues);
                // Incrementar el contador
                PositionValues++;
            } catch (Exception e) {
                // Devolver el contador decrementado al encontrar una posición no válida
                return PositionValues - 1;
            }
        }
    }

    // Métodos getter y setter para acceder y modificar propiedades de la clase
    public int getPiezaInicioIndex() {
        return PiezaInicioIndex;
    }

    public void setPiezaInicioIndex(int piezaInicioIndex) {
        PiezaInicioIndex = piezaInicioIndex;
    }

    public Color getPiezaColor() {
        return PiezaColor;
    }

    public void setPiezaColor(Color piezaColor) {
        PiezaColor = piezaColor;
    }

    public int[][] getPiezaActual() {
        return PiezaActual;
    }

    public void setPiezaActual(int[][] piezaActual) {
        PiezaActual = piezaActual;
    }

    public int getPiezaPositionActual() {
        return PiezaPositionActual;
    }

    public void setPiezaPositionActual(int piezaPositionActual) {
        PiezaPositionActual = piezaPositionActual;
    }

    public int getIdPieza() {
        return IdPieza;
    }

    public void setIdPieza(int idPieza) {
        IdPieza = idPieza;
    }
}

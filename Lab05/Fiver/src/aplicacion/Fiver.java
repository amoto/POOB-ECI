
package aplicacion;
import java.util.*;
import java.io.*;

public class Fiver{
  private int turnos;
  private boolean[][] tablero;
  public static final int[] dX={-1,0,0,1};
  public static final int[] dY={0,-1,1,0};
	/**
	* Crea un tablero de Fiver de tamano tamano x tamano con todas las casillas "apagadas"
	*	@param tamano, el tamano del fiver
	* @throws FiverExcepcion cuando el tamano sea demasiado pequeño o grande para el fiver
	*/
  public Fiver(int tamano)throws FiverExcepcion{
    if(tamano<3 || tamano>38) throw new FiverExcepcion(FiverExcepcion.TAMANO_INVALIDO);
    turnos = 0;
    tablero = new boolean[tamano][tamano];
    for(int i = 0; i < tamano; i++){
      Arrays.fill(tablero[i],false);
    }
  }

	/**
	* Determina si el jugador ha ganado o no
	* @return true si el jugador ha ganado, false dlc
	*/
  private boolean finish(){
    int nt = 0;
    boolean ck = true;
    if(ck){
      for(int i = 0; i < tablero.length; i++)
        for(int j = 0; j < tablero[i].length; j++)
          if(tablero[i][j]) nt++;
      ck = nt==tablero.length*tablero.length;
    }
    return ck;
  }

	/**
	* Determina los intentos maximos del juego
	* @return el numero de intentos maximos del juego
	*/
  private int nIntentos(){
    return (tablero.length*tablero.length)+10;
  }

	/**
	* juega en la posicion determinada del fiver
	* @param f, la fila en la que desea jugar
	* @param c, la columna en la que desea jugar
	* @return true si el jugador ha ganado, false dlc
	* @throws FiverExcepcion si la posicion no existe, o se han agotado los turnos
	*/
  public boolean jugar(int f, int c)throws FiverExcepcion{
    if(f<0 || f>=tablero.length || c<0 || c>=tablero.length) throw new FiverExcepcion(FiverExcepcion.FUERA_RANGO);
    if(turnos>=nIntentos() && !finish()) throw new FiverExcepcion(FiverExcepcion.LIMITE_TURNO);
		if(!finish()){
			tablero[f][c] = !tablero[f][c];
			for(int i = 0; i < dX.length; i++){
				int nf = f + dX[i],nc = c + dY[i];
				if(nf>= 0 && nf < tablero.length && nc >= 0 && nc < tablero.length){
				tablero[nf][nc]= !tablero[nf][nc];
				}
			}
			turnos++;
		}
    return finish();
  }

	/**
	* obtiene el valor del fiver en la posicion determinada
	* @param f, la fila de la que desea obtener el valor
	* @param c, la columna de la que desea obtener el valor
	* @return el valor del fiver en la posicion dada
	* @throws FiverExcepcion si la posicion no existe
	*/
  public boolean get(int f, int c)throws FiverExcepcion{
    if(f<0 || f>=tablero.length || c<0 || c>=tablero.length) throw new FiverExcepcion(FiverExcepcion.FUERA_RANGO);
    return tablero[f][c];
  }

	/**
	* determina los movimientos restantes de la partida
	* @return los movimientos restantes de la partida
	*/
	public int restantes(){
		return nIntentos()-turnos;
	}
}

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import  sun.audio.*; 
import javafx.scene.media.*;
/**
 * Una maquina de juegos que consiste en n SlotMachines ubicadas en forma de diamante, en el que se puede jugar con todas las maquinas o una especifica
 * , en caso de jugar con todas las maquinas el jugador gana si obtiene una o mas lines horizontales, verticales o diagonales (con tamañno > 2) con el mismo color
 * , en caso de jugar con una sola maquina se ganará si todos los elementos de esa maquina tienen el mismo color.
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 001
 */
public class DiamondSlotMachine
{
    // variables del programa
    private int x;
    private int y;
    private boolean isVisible;
    private int juegosTotales;
    private int[] juegosTotalesMaquina;
    private int juegosGanados;
    private int[] juegosGanadosMaquina;
    private int frutas;
    private int casillas;
    private int[][] matriz;
    private SlotMachine[] juegos;
    

    /**
     * Crea el DiamodSlotMachine con n maquinas, usando m "frutas"
     * 
     * @param n>=3, el numero de maquinas y debe ser impar    2<m<=n+1<=6 el numero de "frutas" con las que se jugara
     */
    public DiamondSlotMachine(int n, int m)
    {
        if(n < 3){
            JOptionPane.showMessageDialog(null,"El numero de maquinas debe ser mayor o igual a 3 y deme ser impar");
        }
        else if(m <= 1){
            JOptionPane.showMessageDialog(null,"El numero de frutas debe ser entre 2 y 6");
        }
        else if(m > 6){
            JOptionPane.showMessageDialog(null,"Insuficientes frutas");
        }else{
            if(n%2==0){
                n+=1;
                JOptionPane.showMessageDialog(null,"El número debe ser impar, su número a cambiado a "+n);
            }
            x = 0;
            y = 0;
            isVisible = false;
            juegosTotales = 0;
            juegosGanados = 0;
            frutas = m;
            casillas = n;
            juegosTotalesMaquina = new int[n];
            juegosGanadosMaquina = new int[n];
            juegos = new SlotMachine[n];
            matriz = new int [n][n];
            for(int i = 0; i < n; i++){
                Arrays.fill(matriz[i],-1);
            }
            Arrays.fill(juegosTotalesMaquina,0);
            Arrays.fill(juegosGanadosMaquina,0);
            presentacion();
            fill();
        }
    }

    /**
     * Este metodo llena la matriz con las posiciones del juego con -1 donde no haya máquina, y el indice de cada dado de la SlotMachine donde exista
     */
    private void fill()
    {
        int mitad;
        int cont;
        if(casillas%2!=0){
            for(int i = 0; i < casillas; i++){
                cont = 0;
                mitad = Math.abs((casillas/2) - i);
                for(int j = 0; j < casillas; j++){
                    if(mitad == 0 && cont < juegos[i].getCasillas()){
                        mitad += 1;
                        matriz[i][j] = cont;
                        cont += 1;
                    }
                    mitad -= 1;
                }
            }
        }else{
            for(int i = 0; i < casillas; i++){
                cont = 0;
                if((casillas/2) - i > 0){
                    mitad = Math.abs((casillas/2) - i - 1);
                }else{
                    mitad = Math.abs((casillas/2) - i);
                }
                for(int j = 0; j < casillas; j++){
                    if(mitad == 0 && cont < juegos[i].getCasillas()){
                        mitad += 1;
                        matriz[i][j] = cont;
                        cont += 1;
                    }
                    mitad -= 1;
                }
            }
        }
    }

    /**
     * Este metodo crea cada  SlotMachine y la ubica en su posicion correspondiente
     */
    private void presentacion ()
    {
        //Se evalua si el numero de maquinas es par o impar
        if(casillas%2==0){
            //Se crea y mueve cada SlotMachine (primero vertical y luego horizontalmente) a su posicion en el diamante
            for(int i = 0; i < casillas/2; i++){
                juegos[i] = new SlotMachine((2+(2*i)),frutas);
                juegos[i].move(50*i,50*((casillas/2)-i-1));
            }
            for(int i = casillas/2; i < casillas; i++){
                juegos[i] = new SlotMachine((casillas-(2*(i-(casillas/2)))),frutas);
                juegos[i].move(50*i,50*(i-(casillas/2)));
            }
        }else{
            //Se crea y mueve cada SlotMachine (primero vertical y luego horizontalmente) a su posicion en el diamante
            for(int i = 0; i <= (casillas/2); i++){
                juegos[i] = new SlotMachine((1+(2*i)),frutas);
                juegos[i].move(50*i,50*((casillas/2)-i));
            }
            for(int i = (casillas/2)+1; i < casillas; i++){
                juegos[i] = new SlotMachine((casillas-(2*(i-(casillas/2)))),frutas);
                juegos[i].move(50*i,50*(i-(casillas/2)));
            }
        }
    }
    
    // Miniciclo 1
    
    /**
     * Permite jugar con todas las maquinas
     */
    public void pullAll()
    {
        juegosTotales += 1;
        for(int i = 0; i < casillas; i++){
            juegos[i].pull();
        }
        if(isWinningStateAll()){
            juegosGanados += 1;
            Toolkit.getDefaultToolkit().beep();
        }
    }


    /**
     * permite jugar n veces consecutivas con todas las maquinas
     *
     * @param  0<=intentos el numero de veces que se desea jugar
     */
    public void pullAllTimes(int intentos)
    {
        if(intentos < 0){
            JOptionPane.showMessageDialog(null,"El numero de intentos debe ser mayor o igual a 0");
        }else{
            for(int j = 0; j < intentos; j++){
                pullAll();
            }
        }
        
    }

    /**
     * Este metodo evalua si al jugar con todas las maquinas se encuentran en un estado de victoria o no
     *
     * @return     true si se encuentra en un estado de victoria, de lo contrario retorna false
     */
    public boolean isWinningStateAll()
    {
        boolean ans = false;
        //Revisamos cada SlotMachine (revision horizontal) si se ha ganado en alguna
        for(int i = 1; i < casillas -1 && !ans; i++){
            ans = juegos[i].isWinningState();

        }
        //Revisamos el diamante verticalmente basandonos en los "indices" de la matriz
        for(int i = 1; i < casillas - 1 && !ans; i++){
            int negativos = 0;
            int j=0;
            int cuenta=0;
            //contamos las posiciones en las que no hay SlotMachine (-1 en la matriz)
            while(matriz[j][i]==-1 && j<casillas){
                negativos+=1;
                j+=1;
            }
            //Revisamos toda la columna evadiendo los negativos
            for( j=negativos; j < casillas-negativos-1; j++){
                if (juegos[j].getDado(matriz[j][i]) == juegos[j+1].getDado(matriz[j+1][i])){
                    cuenta+=1;
                }
            }
            if(cuenta==(casillas-(2*negativos)-1)){
                ans = true;
            }
        }
        //Revisamos todos los diagonales
        for (int i=0;i<casillas-(casillas/2+casillas%2)+1 && !ans;i++){
            int j=0;
            int negativos=0;
            int cuentaa=0;
            int cuentab=0;
            int cuentac=0;
            int cuentad=0;
            int n=0;
            //Contamos las posiciones sin SlotMachine en la diagonal de izquierda a derecha (-1 en la matriz)
            while(matriz[i+j][j]==-1 && j+i<casillas-1){
                negativos+=1;
                j+=1;
            }
            for (j=negativos;j<casillas-i-negativos-1;j++){
                n+=1;
                //Revisamos las diagonales de izquierda a derecha
                //Revisamos las diagonales de arriba hacia abajo desde la esquina superior izquierda
                if(juegos[i+j].getDado(matriz[i+j][j]) == juegos[i+j+1].getDado(matriz[i+j+1][j+1])){
                    cuentaa+=1;
                }
                //Revisamos las diagonales de izquierda a derecha desde la esquina superior izquierda
                if(juegos[j].getDado(matriz[j][i+j]) == juegos[j+1].getDado(matriz[j+1][j+1+i])){
                    cuentab+=1;
                }
                //Revisamos las diagonales de derecha a izquierda
                //Revisamos las diagonales de arriba hacia abajo desde la esquina superior derecha
                if(juegos[i+j].getDado(matriz[i+j][casillas-j-1]) == juegos[i+j+1].getDado(matriz[i+j+1][casillas-(j+1)-1])){
                    cuentac+=1;
                }
                //Revisamos las diagonales de derecha a izquierda desde la esquina superior derecha
                if(juegos[j].getDado(matriz[j][casillas-j-i-1]) == juegos[j+1].getDado(matriz[j+1][casillas-i-(j+1)-1])){
                    cuentad+=1;
                }
            }
            if(n>=2 && (cuentaa==n || cuentab==n || cuentac==n || cuentad==n)){
                ans=true;
            }
        }
        return ans;
    }

    //Miniciclo2
    
    /**
     * Este metodo permite jugar con una maquina especifica (linea horizontal de la DiamondSlotMachine)
     *
     * @param  2<=maquina<=casillas-1, es la maquina (fila) de la DiamondSlotMachine con la que se desea jugar
     */
    public void pullN(int maquina)
    {  
        if(maquina <= 0 || maquina > casillas){
            JOptionPane.showMessageDialog(null,"La maquina no existe");
        }
        else if(maquina==1 || maquina == casillas){
            JOptionPane.showMessageDialog(null,"La máquina solo tiene 1 casilla y es invalida para jugar");
        }
        else{
            int n=maquina-1;
            juegosTotalesMaquina[n]+=1;
            juegos[n].pull();
            if(isWinningStateN(maquina)){
                juegosGanadosMaquina[n]+=1;
                Toolkit.getDefaultToolkit().beep();	
            }
        }
        
    }

    /**
     * Este metodo permite jugar con una maquina especifica (linea horizontal de la DiamondSlotMachine) 
     * un numero consecutivo de veces
     *
     * @param  1<=maquina<=casillas, la maquina con la que se desea jugar   
     *          0<=times, el numero de veces que se desea jugar
     */
    public void pullN(int maquina, int times)
    {  
        if(maquina <= 0 || maquina > casillas){
            JOptionPane.showMessageDialog(null,"La maquina no existe");
        }
        else if(times < 0){
            JOptionPane.showMessageDialog(null,"El numero de intentos debe ser mayor o igual a 0");
        }
        else{
            for(int i=0; i<times; i++){
                pullN(maquina);
            }   
        }
        
    }

    /**
     * Determina si una maquina especifica se encuentra en un estado de victoria
     *
     * @param  1<=maquina<=casillas, la maquina de la que se desea determinar el estado
     * @return     true si se encuentra en un estado de victoria, de lo contrario retorna false
     */
    public boolean isWinningStateN(int maquina)
    {
        if(maquina <= 0 || maquina > casillas){
            JOptionPane.showMessageDialog(null,"La maquina no existe");
            return false;
        }
        else{
            return juegos[maquina-1].isWinningState();
        }
    }
    //Miniciclo 3
    /**
     * Este metodo restaura todos los valores del juego DiamondSlotMachine
     */
    public void reset()
    {
        isVisible = false;
        juegosTotales = 0;
        juegosGanados = 0;
        Arrays.fill(juegosTotalesMaquina,0);
        Arrays.fill(juegosGanadosMaquina,0);
        for(int i = 0; i < casillas; i++){
            juegos[i].reset();
        }
        makeInvisible();
        move(-x,-y);
        makeVisible();
    }

    /**
     *Este metodo calcula el porcentaje de victoria de toda las jugadas utilizando la maquina completa
     *
     * @return     el porcentaje evaluado
     */
    public int percentageWinningStatesAll()
    {
        double tmp = 0.0;
        if(juegosTotales >= 1){
            tmp =(((double)juegosGanados/(double)juegosTotales) * 100.00);
        }
        int porcentaje = (int) tmp;
        return porcentaje;
    }

    /**
     * Este metodo calcula el porcentaje de victoria de solo una maquina utilizando el numero de juegos
     * realizados en esa maquina en especifico
     *
     * @param  1<=maquina<=casillas, la maquina que se quiere determinar el porcentaje de victorias 
     * @return  el porcentaje de victoria evaluado
     */
    public int percentageWinningStatesN(int maquina)
    {
        if(maquina <= 0 || maquina > casillas){
            JOptionPane.showMessageDialog(null,"La maquina no existe");
            return -1;
        }
        else{
            double tmp = 0.0;
            if(juegosTotalesMaquina[maquina-1] >= 1){
                tmp =(((double)juegosGanadosMaquina[maquina-1]/(double)juegosTotalesMaquina[maquina-1]) * 100.00);
            }
            int porcentaje = (int) tmp;
            return porcentaje;
        }
        
    }

    
    //Miniciclo4
    
    /**
     * Este metodo hace visible la DiamondSlotMachine
     */
    public void makeVisible()
    {
        isVisible = true;
        for(int i = 0; i < casillas; i++){
            juegos[i].makeVisible();
        }
    }
    
    /**
     * Este metodo hace invisible la DiamondSlotMachine
     */
    public void makeInvisible()
    {
        isVisible = false;
        for(int i = 0; i < casillas; i++){
            juegos[i].makeInvisible();
        }
    }

    /**
     * Este metodo mueve el DiamondSlotMachine
     *
     * @param  vertical, los pixeles que se mueve la DiamondSlotMachine verticalmente  
     * horizontal, los pixeles que se mueve la DiamondSlotMachine horizontalmente
     */
    public void move(int vertical, int horizontal)
    {
        if(horizontal>0){
            for (int i=0;i<horizontal;i++){
                for (int j=0;j<casillas;j++){
                    juegos[j].move(0,1);
                }
            }
        }else{
            for (int i=0;i>horizontal;i--){
                for (int j=0;j<casillas;j++){
                    juegos[j].move(0,-1);
                }
            }
        }
        if (vertical>0){
            for (int i=0;i<vertical;i++){
                for (int j=0;j<casillas;j++){
                    juegos[j].move(1,0);
                }
            }
        }else{
            for (int i=0;i>vertical;i--){
                for (int j=0;j<casillas;j++){
                    juegos[j].move(-1,0);
                }
            }
        }
        x+=horizontal;
        y+=vertical;
    }

}

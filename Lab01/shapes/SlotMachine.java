import java.util.*;
import javax.swing.*;
/**
 * Una maquina de juegos que consiste en sacar de las n casillas el mismo objeto para poder ganar
 * 
 * @author Julian Devia - Hugo Alvarez
 * @version 001
 */
public class SlotMachine
{
    // variables de presentacion
    
    private Rectangle marco;
    private Rectangle[] cubiculo;
    private Circle[] opcion;
    private Circle ganador;
    private static final String[] colores = {"red","yellow","blue","green","magenta","black"};
    
    // variables del programa
    
    private int x;
    private int y;
    private int juegosTotales;
    private int juegosGanados;
    private boolean isVisible;
    private int[] dados;
    private int frutas;
    private int casillas;
    private Random rnd;

    
    /**
     * Crea el Slot Machine con n casillas y m "frutas"
     * @param  n > 0 el numero de casillas de la maquina    0 <= m <= n+1 <=6 el numero de "frutas"
     */
    public SlotMachine(int n, int m){
        // programa
        x = 0;
        y = 0;
        juegosTotales = 0;
        juegosGanados = 0;
        isVisible = false;
        dados = new int[n];
        frutas = m;
        casillas = n;
        random(casillas,frutas);
        // presentacion
        marco = new Rectangle();
        opcion = new Circle[n];
        cubiculo = new Rectangle[n];
        presentacion();
    }

    /**
     * Este metodo ubica los componentes del SlotMachine en sus ubicaciones por defecto
     */
    private void presentacion()
    {
        marco.changeColor("black");
        marco.changeSize(50,50*casillas);
        marco.moveHorizontal(5);
        marco.moveVertical(5);
        /*ganador.moveVertical(10);
        ganador.moveHorizontal(10);
        ganador.moveHorizontal(50*(casillas/2));
        ganador.moveVertical(50);*/
        for(int i = 0; i < casillas; i++){
            cubiculo[i] = new Rectangle();
            opcion[i] = new Circle();
        }
        for(int i = 0; i < casillas; i++){
            cubiculo[i].changeSize(40,40);
            cubiculo[i].changeColor("white");
            cubiculo[i].moveHorizontal(10);
            cubiculo[i].moveVertical(10);
            cubiculo[i].moveHorizontal(50*i);
            opcion[i].changeSize(40);
            opcion[i].moveHorizontal(10);
            opcion[i].moveVertical(10);
            opcion[i].moveHorizontal(50*i);
            opcion[i].changeColor(colores[dados[i]]);
        }
    }

    /**
     * Este metodo retorna el número que se encuentra en el dado i
     *
     * @param  i es el dado del que se quiere conocer valor
     * @return     el elemento que se encientra en el dado i, o -1 si el dado no existe
     */
    public int getDado(int i)
    {
        if(i < 0 || i >= casillas){
            return -1;
        }else{
            return dados[i];
        }
    }

    /**
     * Este metodo retorna la cantidad de casillas del SlotMachine
     *
     * @return     casillas, el numero de casillas de la SlotMachine
     */
    public int getCasillas()
    {
        return casillas;
    }

    /**
     * Este metodo cambia el color de cada casilla del SlotMachine de acuerdo con su respectivo dado
     */
    private void changeColor(){
        for(int i = 0; i < casillas; i++){
            opcion[i].changeColor(colores[dados[i]]);
        }
    }

    /**
     * Este metodo realiza la funcion de jugabilidad de la maquina de manera aleatoria
     */
    private void random(int n, int m){
        rnd = new Random();
        for(int i = 0; i < n; i++){
            dados[i] = rnd.nextInt(m);
        }
    }
    // miniciclo 1
    /**
     * El metodo pull es el que realiza el juego y evalua si gana o pierde
     */
    public void pull(){
        juegosTotales += 1;
        random(casillas,frutas);
        changeColor();
        if(isWinningState()){
            juegosGanados += 1;
            /*JOptionPane.showMessageDialog(null,"Usted ha ganado");*/
        }
        
    }
    
    /**
     * Este metodo permite jugar n veces consecutivas
     *
     * @param  intentos que es el numero de veces que va a realizar la funcion pull()
     */
    public void pull(int intentos){
        for(int i = 0; i < intentos; i++){
            pull();
        }
    }

    /**
     * Este metodo revisa si el jugador gano o no
     *
     * @return     true si todos los elementos son iguales, false si alguno es diferente
     */
    public boolean isWinningState(){
        boolean ans = casillas > 1;
        for(int i = 0; i < casillas-1 && ans; i++){
            if(dados[i] != dados[i+1]){
                ans = false;
            }
        }
        return ans;
    }
    // pruebas
    /**
     * Este metodo retorna el numero de juegos jugados desde la creacion o el ultimo reset
     * 
     * @return     el numero de juegos jugados
     */
    public int getJuegos()
    {
        return juegosTotales;
    }

    /**
     * Este metodo retorna el numero de juegos ganados desde la creacion o el ultimo reset
     *
     * @return     el numero de juegos ganados
     */
    public int getGanados()
    {
        return juegosGanados;
    }

    // miniciclo 2
    /**
     * Este metodo reinicia todos los datos y genera un random en cada casilla
     */
    public void reset(){
        juegosTotales = 0;
        juegosGanados = 0;
        isVisible = false;
        random(casillas,frutas);
    }

    /**
     * Este metodo calcula el porcentaje de victorias cuando el numero de juegos sea mayor o igual a 1 desde la creacion o el ultimo reset
     *
     * @return     el porcentaje de victorias
     */
    public int percentageOfWinningStates(){
        double tmp = 0.0;
        if(juegosTotales >= 1){
            tmp =(((double)juegosGanados/(double)juegosTotales) * 100.00);
        }
        int porcentaje = (int) tmp;
        return porcentaje;
    }
    // pruebas
    
    // miniciclo 3
    /**
     * Este metodo hace visible la SlotMachine
     */
    public void makeVisible()
    {
        isVisible = true;
        marco.makeVisible();
        /*ganador.makeVisible();*/
        for(int i = 0; i < casillas; i++){
            cubiculo[i].makeVisible();
            opcion[i].makeVisible();
        }
    }
    
    /**
     * Este metodo hace invisible la SlotMachine
     */
    public void makeInvisible()
    {
        isVisible = false;
        marco.makeInvisible();
        /*ganador.makeVisible();*/
        for(int i = 0; i < casillas; i++){
            cubiculo[i].makeInvisible();
            opcion[i].makeInvisible();
        }
    }


    /**
     * Este metodo mueve la SlotMachine las posiciones indicadas
     *
     * @param  vertical, los pixeles que se mueve la SlotMachine verticalmente   horizontal, los pixeles que se mueve la SlotMachine horizontalmente
     */
    public void move(int vertical,int horizontal)
    {
        if(horizontal>0){
            for (int i=0;i<horizontal;i++){
                marco.moveHorizontal(1);
                for (int j=0;j<casillas;j++){
                    cubiculo[j].moveHorizontal(1);
                    opcion[j].moveHorizontal(1);
                }
            }
        }else{
            for (int i=0;i>horizontal;i--){
                marco.moveHorizontal(-1);
                for (int j=0;j<casillas;j++){
                    cubiculo[j].moveHorizontal(-1);
                    opcion[j].moveHorizontal(-1);
                }
            }
        }
        if (vertical>0){
            for (int i=0;i<vertical;i++){
                marco.moveVertical(1);
                for (int j=0;j<casillas;j++){
                    cubiculo[j].moveVertical(1);
                    opcion[j].moveVertical(1);
                }
            }
        }else{
            for (int i=0;i>vertical;i--){
                marco.moveVertical(-1);
                for (int j=0;j<casillas;j++){
                    cubiculo[j].moveVertical(-1);
                    opcion[j].moveVertical(-1);
                }
            }
        }
        x+=horizontal;
        y+=vertical;
    }

}

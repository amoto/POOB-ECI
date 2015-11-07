package Shapes;
/**
 * Abstract class Shape - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Shape
{
    // instance variables - replace the example below with your own
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;

    /**
     * Construye una figura en la posicion determinada, con el color determinado
     * @param  x,  es la posicion x de la figura
     * @param  y,  es la posicion y de la figura
     * @param color, es el color de la figura
     * @return  una figura con su color y posicion determinada
     */
    public Shape(int x, int y, String color)
    {
        xPosition = x;
        yPosition = y;
        this.color = color;
        isVisible = false;
    }
    
    /**
     * Acerta o aleja la figura de acuerdo al signo que diga el usuario
     * @param  sign, es un signo '+' o '-' que acerca o aleja la figura
     */
    abstract void zoom(char sign);
    
    /**
     * Hace Visible la figura
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace Invisible la figura
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Mueve la figura a la derecha
     */
    public void moveRight(){
        moveHorizontal(20);
    }
    
    /**
     * Mueve la figura a la izquierda
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }
    
    /**
     * Mueve la figura arriba
     */
    public void moveUp(){
        moveVertical(-20);
    }
    
    /**
     * Mueve la figura abajo
     */
    public void moveDown(){
        moveVertical(20);
    }
    
    /**
     * Mueve la figura horizontalmente
     * @param distance, la distancia positiva o negativa que va a mover la figura
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }
    
    /**
     * Mueve la figura verticalmente
     * @param distance, la distancia positiva o negativa que va a mover la figura
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
    
    /**
     * Mueve la figura lentamente horizontal 
     * @param distance, la distancia positiva o negativa que va a mover la figura
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }
    
    /**
     * Mueve la figura lentamente vertical
     * @param distance, la distancia positiva o negativa que va a mover la figura
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Cambia de color
     * @param new color, los colores son "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /**
     * Dibuja la figura
     */
    protected abstract void draw();

    /**
     * Borra la figura
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

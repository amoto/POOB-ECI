package shapes;
import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Shapes{

    public static double PI=3.1416;
    
    private int diameter;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Circle(){
        super(0,0,"blue");
        diameter = 30;
    }
    
    /**
     * calcula el area del circulo
     * @return     el area del circulo
     */
    public double area()
    {
        double areaa=((diameter*diameter)/4)*PI;
        return areaa;
    }

    public void zoom(char sign)
    {
        erase();
        if (sign=='+'){
            diameter+=1;
        }else if (sign=='-'){
            diameter-=1;
        }
        draw();
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }
    
    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

}

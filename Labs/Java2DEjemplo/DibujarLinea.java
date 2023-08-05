package Ejemplos;
import java.awt.*;
import javax.swing.*;

public class DibujarLinea extends JFrame{
public DibujarLinea(){
        super("Dibujar Linea");//usamos super para llamar al constructor de la clase padre JFrame
        setSize(400, 300); //es el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que se cierre la ventana
        setVisible(true); //para que se vea la ventana
    }

    public void paint(Graphics g){ //paint es un metodo de la clase JFrame que se encarga de dibujar y graphics es un objeto que se encarga de dibujar
        super.paint(g);//super es para llamar al metodo paint de la clase padre JFrame
        g.setColor(Color.BLUE);
        //x1 es la posicion en x del punto inicial, y1 es la posicion en y del punto inicial,
        //x2 es la posicion en x del punto final, y2 es la posicion en y del punto final
        g.drawLine(100, 100, 200, 200);//DrawLine dibuja una linea
    }

    public static void main(String[] args) {
        DibujarLinea ventana = new DibujarLinea(); //creamos un objeto de la clase DibujarLinea
    }
}

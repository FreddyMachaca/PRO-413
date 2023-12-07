package Ejemplos;
import java.awt.*;
import javax.swing.*;

public class DibujarRectangulo extends JFrame{ //extends es para heredar de la clase JFrame
    public DibujarRectangulo(){
        super("Dibujar Rectangulo");//usamos super para llamar al constructor de la clase padre JFrame
        setSize(400, 300); //es el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que se cierre la ventana
        setVisible(true); //para que se vea la ventana
    }

    public void paint(Graphics g){ //paint es un metodo de la clase JFrame que se encarga de dibujar y graphics es un objeto que se encarga de dibujar
        super.paint(g);//super es para llamar al metodo paint de la clase padre JFrame
        g.setColor(Color.BLUE);
        //x es la posicion en x del rectangulo, y es la posicion en y del rectangulo,
        //width es el ancho del rectangulo, height es el alto del rectangulo
        g.fillRect(100, 100, 200, 200); //fillRect dibuja un rectangulo
    }

    public static void main(String[] args) {
        DibujarRectangulo ventana = new DibujarRectangulo(); //creamos un objeto de la clase DibujarRectangulo
    }

}

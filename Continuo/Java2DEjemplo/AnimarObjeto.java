package Ejemplos;
import java.awt.*;
import javax.swing.*;

public class AnimarObjeto extends JFrame implements Runnable{
    private int x = 50;
    private int y = 50;

    public AnimarObjeto(){
        super("Animar Objeto");//usamos super para llamar al constructor de la clase padre JFrame
        setSize(400, 300); //es el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que se cierre la ventana
        setVisible(true); //para que se vea la ventana
        new Thread(this).start(); //inicia el hilo de ejecucion
    }
    public void paint(Graphics g){ //paint es un metodo de la clase JFrame que se encarga de dibujar y graphics es un objeto que se encarga de dibujar
        super.paint(g);//super es para llamar al metodo paint de la clase padre JFrame
        g.setColor(Color.BLUE);
        //x es la posicion en x del rectangulo, y es la posicion en y del rectangulo,
        //width es el ancho del rectangulo, height es el alto del rectangulo
        g.fillOval(x, y, 50, 50); //fillOval dibuja un ovalo
    }
    public void run(){
        while(true){
            x++;
            y++;
            repaint(); //repaint es un metodo de la clase JFrame que se encarga de llamar al metodo paint
            try{
                Thread.sleep(10); //duerme el hilo de ejecucion por 10 milisegundos
            }catch(Exception e){}
        }
    }
    public static void main(String[] args) {
        AnimarObjeto ventana = new AnimarObjeto(); //creamos un objeto de la clase AnimarObjeto
    }
}

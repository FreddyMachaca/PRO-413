import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CirculoAnimado extends JPanel implements Runnable{
    private int x;
    private int y;
    private int radio;
    private int dx;

    public CirculoAnimado(){
        x=0;
        y=100;
        radio=50;
        dx=5;

        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
        new Thread(this).start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;
        ((Graphics2D) g2).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLUE);
        Ellipse2D circulo = new Ellipse2D.Double(x-radio, y-radio, radio*2, radio*2);
        ((Graphics2D) g2).fill(circulo);
    }
    @Override
    public void run(){
        while(true){
            x+=dx;
            if(x+radio>=getWidth()){
                x=getWidth()-radio;
                dx=-dx;
            }else if (x-radio<0){
                x=radio;
                dx=-dx;
            }
            repaint();
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circulo Animado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setResizable(false);
        CirculoAnimado circuloAnimado = new CirculoAnimado();
        frame.add(circuloAnimado);
        frame.setVisible(true);
    }
}

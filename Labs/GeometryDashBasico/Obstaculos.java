package GeometryDash;
import java.awt.*;
public class Obstaculos {
    int x, y, size, speed; //x y y son las coordenadas, size es el tamaño y speed es la velocidad
    public Obstaculos(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }
    public void mover() {
        x -= speed;
        if (x < -size) {
            x = 800 + (int) (Math.random() * 800); //generar la posicion en x del obstaculo aleatoriamente
        }
    }
    public void dibujar(Graphics g) {
        g.setColor(Color.RED); //cambiar el color
        g.fillRect(x, y, size, size); //dibujar el obstaculo en rectangulo
    }
}

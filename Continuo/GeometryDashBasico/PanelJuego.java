package GeometryDash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

//actionlistener sirve para que el panel se actualice constantemente
//keylistener sirve para que el panel escuche las teclas que se presionan
public class PanelJuego extends JPanel implements ActionListener, KeyListener {
    Timer tiempo;
    int JugadorX = 100, JugadorY = 200;
    int JugadorTamaño = 30;
    int velocidad = 5;
    int gravedad = 2;
    int fuerzaSalto = -16;
    int velocidadJugadorY = 0;
    boolean juegoActivo = true;
    int puntaje = 0;
    ArrayList<Obstaculos> obstaculos; //arraylist de obstaculos

    public PanelJuego() {
        obstaculos = new ArrayList<>(); //inicializar el arraylist
        generarObstaculos();//generar los obstaculos
    }

    public void iniciar() {
        //delay de 1000/60 porque 1000 es un segundo y 60 es el numero de frames por segundo, this es para que el panel se actualice constantemente
        tiempo = new Timer(1000 / 60, this);
        tiempo.start(); //inicia el timer
    }

    @Override
    protected void paintComponent(Graphics g) { //metodo que se ejecuta constantemente
        super.paintComponent(g); //llamar al metodo de la clase padre para que se dibuje el panel

        //Dibujar fondo
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 400);

        //Dibujar suelo
        g.setColor(Color.BLACK);
        g.fillRect(0, 230, 800, 10);

        //Dibujar jugador
        g.setColor(Color.BLUE);
        g.fillRect(JugadorX, JugadorY, JugadorTamaño, JugadorTamaño); //dibujar el jugador en rectangulo

        //Dibujar obstaculos
        for (Obstaculos obstaculo : obstaculos) { //for each para recorrer el arraylist
            obstaculo.dibujar(g); //dibujar los obstaculos
        }

        //Dibujar puntuacion
        g.setFont(new Font("Arial", Font.PLAIN, 20)); //cambiar la fuente
        g.setColor(Color.BLACK); //cambiar el color
        g.drawString("Puntuacion: " + puntaje, 600, 30); //dibujar el texto
    }

    @Override
    public void actionPerformed(ActionEvent e) { //actionevent e es para que el metodo se ejecute constantemente y no solo una vez
        //Si el juego esta activo
        if(!juegoActivo){
            return;
        }

        for(Obstaculos obstaculo : obstaculos){ //for each para recorrer el arraylist
            obstaculo.mover(); //mover los obstaculos
        }

        JugadorY += velocidadJugadorY; //sumar la velocidad del jugador a la posicion del jugador
        velocidadJugadorY += gravedad; //sumar la gravedad a la velocidad del jugador

        if(JugadorY >200){ //si el jugador esta mas abajo del suelo
            JugadorY = 200; //posicionarlo en el suelo
            velocidadJugadorY = 0; //poner la velocidad del jugador en 0
        }

        Rectangle jugadorRect = new Rectangle(JugadorX, JugadorY, JugadorTamaño, JugadorTamaño); //crear un rectangulo para el jugador
        for(Obstaculos obstaculo : obstaculos){ //for each para recorrer el arraylist de obstaculos
            Rectangle obstaculoRect = new Rectangle(obstaculo.x, obstaculo.y, obstaculo.size, obstaculo.size); //crear un rectangulo para el obstaculo
            if(jugadorRect.intersects(obstaculoRect)){ //si el jugador colisiona con el obstaculo
                tiempo.stop(); //detener el timer
                juegoActivo = false; //desactivar el juego
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea reiniciar el juego?", "Juego terminado", JOptionPane.YES_NO_OPTION);
                if(opcion == JOptionPane.YES_OPTION){
                    reiniciarJuego();
                }else{
                    System.exit(0);
                }
            }
        }

        puntaje++; //sumar 1 al puntaje
        repaint(); //repintar el panel
    }
    private void reiniciarJuego(){ //metodo para reiniciar el juego
        //Reiniciar jugador
        JugadorY = 200; //posicionar al jugador en el suelo
        velocidadJugadorY = 0; //poner la velocidad del jugador en 0
        puntaje = 0;
        //Reiniciar obstaculos
        generarObstaculos();
        //Reiniciar juego
        juegoActivo = true;
        tiempo.start();
    }

    private void generarObstaculos() {
        obstaculos.clear(); //limpiar el arraylist de obstaculos
        Random random = new Random(); //crear un objeto de la clase random para generar numeros aleatorios
        for (int i = 0; i < 3; i++) { //generar 3 obstaculos
            int obstaculoX = 900 + (i * 800+random.nextInt(300)); //generar la posicion en x del obstaculo
            int obstaculoY = 200; //generar la posicion en y del obstaculo
            int obstaculoTamaño = 30; //generar el tamaño del obstaculo
            obstaculos.add(new Obstaculos(obstaculoX, obstaculoY, obstaculoTamaño, velocidad)); //agregar el obstaculo al arraylist
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && JugadorY==200){ //si se presiona la tecla espacio y el jugador esta en el suelo
            velocidadJugadorY = fuerzaSalto; //poner la velocidad del jugador en la fuerza del salto

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

package GeometryDash;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater() es un método que permite ejecutar un código en el hilo de la interfaz gráfica de usuario (GUI) de Java.
        SwingUtilities.invokeLater(() -> {
            // Para crear una ventana, se crea un objeto de la clase JFrame. El constructor de JFrame recibe como parámetro el título de la ventana.
            JFrame frame = new JFrame("Juego demo de Geometry Dash");
            // El método setDefaultCloseOperation() permite indicar qué debe hacer la ventana cuando se le da click en el botón de cerrar.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // El método setSize() permite indicar el tamaño de la ventana.
            frame.setSize(800, 400);
            // El método setResizable() permite indicar si la ventana puede ser redimensionada o no.
            frame.setResizable(false);
            //gamePanel es un objeto de la clase GamePanel, que es la clase que contiene el código del juego.
            PanelJuego gamePanel = new PanelJuego();
            // El método add() permite agregar un componente a la ventana.
            frame.add(gamePanel);
            // El método addKeyListener() permite agregar un objeto que escuche los eventos de teclado.
            frame.addKeyListener(gamePanel);
            // El método setVisible() permite indicar si la ventana debe ser visible o no.
            frame.setVisible(true);
            // El método start() permite iniciar el juego.
            gamePanel.iniciar();
        });
    }

}

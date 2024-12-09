package GUI.extras;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectButton extends JButton {
    private BufferedImage imagen;

    // Constructor
    public SelectButton(String rutaImagen) {
        try {
            // Cargar la imagen desde la ruta especificada
            imagen = ImageIO.read(new File(rutaImagen));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }

        // Hacer que el botón no tenga bordes ni contenido extra
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagen != null) {
            // Escalar la imagen al tamaño del botón
            int ancho = getWidth();
            int alto = getHeight();
            g.drawImage(imagen, 0, 0, ancho, alto, this);
        }
    }
}

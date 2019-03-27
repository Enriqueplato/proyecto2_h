/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author enrique
 */
public class Disco extends JPanel{
    
    public Disco(int x, int y, int ancho, int alto) {
        Random rand = new Random();

        //tres colores bases
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        
        Color colorAnillo = new Color(r, g, b);
        
        this.setBackground(colorAnillo);
        this.setBounds(x, y, ancho, alto);
    }    
}

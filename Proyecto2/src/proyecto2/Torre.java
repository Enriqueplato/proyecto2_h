/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author enrique
 */
public class Torre extends JPanel{

    public Torre() {
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        g.setColor(Color.BLUE);
        
        //base
        g.fillRect(10, 270, 200,10);
        
        //asta
        g.fillRect(110, 30, 5, 240);
        
    }  
}

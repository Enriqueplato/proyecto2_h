/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import proyecto2.TableroJuego;

/**
 *
 * @author enrique
 */
public class Torre extends TableroJuego implements MouseMotionListener{

    TableroJuego ventana = new TableroJuego();
    JButton boton = new JButton();
   
    public Torre() {
        add(boton);
        boton.setLocation(500, 300);
        boton.setVisible(true);
        
        ventana.add(boton);
        
        addMouseMotionListener(this);
    }
    
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
            boton.setText("Hola");
            boton.setLocation(e.getPoint());
            boton.repaint();
            boton.setBackground(Color.green);
            boton.setBorder(null);
        }
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {    
        
    }
    
    
    
    /*public void torresHanoi(int discos, int torre1, int torre2, int torre3){
        //caso base
        if(discos == 1){
            System.out.println("Mover disco de torre " + torre1 + "a torre" + torre3);
        }else{
            //dominio
            torresHanoi(discos-1, torre1, torre3, torre2);
            System.out.println("Mover disco de torre " + torre1 + "a torre" + torre3);
            torresHanoi(discos-1, torre2, torre1, torre3);
        }
        
    }*/
}

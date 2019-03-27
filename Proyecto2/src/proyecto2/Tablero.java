/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author enrique
 */
public class Tablero extends JPanel implements ActionListener, MouseListener{
    Pila pila1 = new Pila();
    Pila pila2 = new Pila();
    Pila pila3 = new Pila();

    Proyecto2 hanoi;

    int Discos; //Numero de discos con los que se va a jugar

    int arosJuego; //Numero de discos con los que se esta jugando
    
    //torres
    Torre torre1;
    Torre torre2;
    Torre torre3;

    JButton iniciar; //boton jugar

    JComboBox numeroAros; //seleccion de numero de discos
    
    //etiquetas
    JLabel labelPasos;
    JLabel numeroDeAros;
    JLabel nombreTorre1;
    JLabel nombreTorre2;
    JLabel nombreTorre3;

    //para mover el aro
    boolean seleccionado;
    Disco aroMover;

    public Tablero() throws IOException {
        Discos = 3;
        aroMover = null;
        seleccionado = false;
        hanoi = new Proyecto2();
        hanoi.hanoi(Discos, 1, 2, 3);
        arosJuego=3;
        
        this.setBackground(Color.WHITE);

        this.setLayout(null);

        //Mostramos el numero de movimientos minimos que debe de hacer
        labelPasos = new JLabel(" " + hanoi.getContador());
        labelPasos.setBounds(110, 405, 90, 10);
        labelPasos.setForeground(Color.red);
        this.add(labelPasos);


        //torre1
        torre1 = new Torre();
        torre1.setBounds(0, 10, 220, 300);
        torre1.addMouseListener(this);
        agregarAros(3);
        this.add(torre1);

        nombreTorre1 = new JLabel("TORRE UNO");
        nombreTorre1.setBounds(80, 0, 150, 10);
        this.add(nombreTorre1);

        //torre2
        torre2 = new Torre();
        torre2.setBounds(220, 10, 220, 300);
        torre2.addMouseListener(this);
        this.add(torre2);

        nombreTorre2 = new JLabel("TORRE DOS");
        nombreTorre2.setBounds(300, 0, 150, 10);
        this.add(nombreTorre2);

        //torre3
        torre3 = new Torre();
        torre3.setBounds(440, 10, 220, 300);
        torre3.addMouseListener(this);
        this.add(torre3);

        nombreTorre3 = new JLabel("TORRE TRES");
        nombreTorre3.setBounds(520, 0, 150, 10);
        this.add(nombreTorre3);

        //boton jUGAR
        iniciar = new JButton("JUGAR");
        iniciar.setBounds(10, 510, 90, 30);
        iniciar.addActionListener(this);
        this.add(iniciar);

        //JcomboBox
        numeroAros = new JComboBox();
        //llenar el jcomboBox
        for (int i = 3; i <= 15; i++) {
            numeroAros.addItem(i);
        }
        numeroAros.setBounds(150, 340, 50, 30);
        this.add(numeroAros);
    }

    public void agregarAros(int n) {
       
        //String nombreAro = "aro";
        int x=40, y=250, ancho=150, alto=20, aux = 20;
        Disco aro = new Disco(x,y,ancho,alto);
        aro.setBounds(90, 250, 45, 20);
        aro.addMouseListener((MouseListener) this);
        torre1.add(aro);
        torre1.updateUI();

        for (int i = 1; i <= n; i++) {
            torre1.add(new Disco(x,y,ancho,alto));
            torre1.getComponent(i).addMouseListener(this);
        
            Disco disco = new Disco(x-10,y,ancho+20,alto);
            Nodo nodo = new Nodo();
            nodo.setDiscos(disco);
            //nodo.setTam(n);
            pila1.Insertar(nodo);
            torre1.add(disco);
            torre1.repaint();
            y-=alto;
            x+=10;
            ancho-=aux;
        }

        torre1.updateUI();

    }


    @Override
    public void actionPerformed(ActionEvent e) { //Boton Jugar                 
                        
        if (e.getSource() == iniciar) {
            try {
                int n = (int) numeroAros.getSelectedItem();
                arosJuego=n;
                hanoi.setContador(0);
                Discos = n;
                hanoi.setContador(0);
                //calcula la solucion optima para resolver el juego
                hanoi.hanoi(n, 1, 2, 3);
                
                int pasosMinimos = hanoi.getContador();

                //actualiza el Label minimo de pasos
                labelPasos.setText(" " + pasosMinimos);

                //eliminar los aros de las torres
                torre1.removeAll();
                torre2.removeAll();
                torre3.removeAll();

                //redibujar las torres
                torre1.updateUI();
                torre2.updateUI();
                torre3.updateUI();

                agregarAros(n);

            } catch (IOException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    //Metodos para mover los discos de una torre a otra
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
  
    
}

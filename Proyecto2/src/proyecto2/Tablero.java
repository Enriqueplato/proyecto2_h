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
import javax.swing.JOptionPane;
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

        String nombreAro = "aro";

        Disco aro = new Disco();
        aro.setBounds(90, 250, 45, 20);
        aro.addMouseListener((MouseListener) this);
        torre1.add(aro);
        torre1.updateUI();

        for (int i = 1; i <= n - 1; i++) {
            torre1.add(new Disco());
            torre1.getComponent(i).addMouseListener(this);
        }

        organizar(n);

        torre1.updateUI();

    }

    //Nos sirve para poner los discos de mayor a menor
    public void organizar(int n) {
        if (n >= 0) {
            for (int j = 1; j <= n - 1; j++) {

                //panel Anterior
                JPanel anterior = (JPanel) torre1.getComponent(j - 1);
                //posiciones y tamaño del aro anterior
                int x = anterior.getX();
                int y = anterior.getY();
                int w = anterior.getWidth();
                int h = anterior.getHeight();

                //Panel que se va a modificar
                JPanel aroA = (JPanel) torre1.getComponent(j);
                aroA.setBounds(x, y - h, w, h);
                anterior.setBounds(x - 10, y, w + 20, h);
                torre1.setComponentZOrder(aroA, j);
                torre1.setComponentZOrder(anterior, j - 1);

            }
            organizar(n - 1);
        }

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

        //mover de la torre 1 a otra torre
        if (torre1.getComponentCount() > 0) {
            if (e.getSource() == torre1.getComponent(torre1.getComponentCount() - 1) && seleccionado == false) {
                //aro que voy a mover
                seleccionado = true;
                //elimino el aro de la torre
                aroMover = (Disco) torre1.getComponent(torre1.getComponentCount() - 1);
                torre1.remove(torre1.getComponentCount() - 1);
                torre1.updateUI();
            }
        }
        //mover de la torre 2 a otra torre
        if (torre2.getComponentCount() > 0) {
            if (e.getSource() == torre2.getComponent(torre2.getComponentCount() - 1) && seleccionado == false) {

                //aro que voy a mover
                aroMover = (Disco) torre2.getComponent(torre2.getComponentCount() - 1);
                seleccionado = true;
                //elimino el aro de la torre
                torre2.remove(torre2.getComponentCount() - 1);
                torre2.updateUI();
            }
        }

        //mover de la torre 3 a otra torre
        if (torre3.getComponentCount() > 0) {
            if (e.getSource() == torre3.getComponent(torre3.getComponentCount() - 1) && seleccionado == false) {
                //aro que voy a mover
                aroMover = (Disco) torre3.getComponent(torre3.getComponentCount() - 1);
                seleccionado = true;
                //elimino el aro de la torre
                torre3.remove(torre3.getComponentCount() - 1);
                torre3.updateUI();
            }
        }

        //para soltar un aro en la torre 1 una vez echo click en un aro de otra torre
        if (e.getSource() == torre1 && seleccionado == true) {
            if (aroMover != null) {
                //guardar el ancho y alto para acomodar
                int x = aroMover.getX();//posicion en x
                int h = aroMover.getHeight();//altura
                int w = aroMover.getWidth();//anchura
                if (torre1.getComponentCount() == 0) {

                    //acomodar el aro que entra
                    aroMover.setBounds(x, 250, w, h);

                    torre1.add(aroMover);
                    torre1.updateUI();
                    aroMover = null;
                    seleccionado = false;

                } else {

                    //ultimo aro que hay en la torre
                    Disco aroPresente = (Disco) torre1.getComponent(torre1.getComponentCount() - 1);
                    //si retorna true
                    if (verificar(aroPresente, aroMover)) {

                        aroMover.setBounds(x, 250 - (20 * torre1.getComponentCount() - 1), w, 20);
                        torre1.add(aroMover);
                        torre1.updateUI();
                        aroMover = null;
                        seleccionado = false;

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento inavlido");
                    }
                }
            }
        }

        //para soltar un aro en la torre 2 una vez echo click en un aro de otra torre
        if (e.getSource() == torre2 && seleccionado == true) {
            if (aroMover != null) {
                //guardar el ancho y alto para acomodar
                int x = aroMover.getX();//posicion en x
                int w = aroMover.getWidth();//anchura
                if (torre2.getComponentCount() == 0) {

                    //acomodar el aro que entra
                    aroMover.setBounds(x, 250, w, 20);

                    torre2.add(aroMover);
                    torre2.updateUI();
                    aroMover = null;
                    seleccionado = false;

                } else {
                    //ultimo aro que hay en la torre
                    Disco aroPresente = (Disco) torre2.getComponent(torre2.getComponentCount() - 1);

                    //si retorna true
                    if (verificar(aroPresente, aroMover)) {

                        aroMover.setBounds(x, 250 - (20 * torre2.getComponentCount() - 1), w, 20);
                        torre2.add(aroMover);
                        torre2.updateUI();
                        aroMover = null;
                        seleccionado = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento invalido");
                    }
                }
            }
        }

        //para soltar un aro en la torre 3 una vez echo click en un aro de la otra torre
        if (e.getSource() == torre3 && seleccionado == true) {
            if (aroMover != null) {

                //guardar el ancho y alto para acomodar
                int x = aroMover.getX();//posicion en x
                int h = aroMover.getHeight();//altura
                int w = aroMover.getWidth();//anchura
                if (torre3.getComponentCount() == 0) {

                    //acomodar el aro que entra
                    aroMover.setBounds(x, 250, w, h);

                    torre3.add(aroMover);
                    torre3.updateUI();
                    aroMover = null;
                    seleccionado = false;

                } else {

                    //ultimo aro que hay en la torre
                    Disco aroPresente = (Disco) torre3.getComponent(torre3.getComponentCount() - 1);
                    //si retorna true
                    if (verificar(aroPresente, aroMover)) {

                        aroMover.setBounds(x, 250 - (20 * torre3.getComponentCount() - 1), w, 20);
                        torre3.add(aroMover);
                        torre3.updateUI();
                        aroMover = null;
                        seleccionado = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento inavlido");
                    }
                }
            }
        }

        if (e.getSource() == torre3) {
            if (hanoi.verificarFinalJuego(Discos, torre3.getComponentCount()) == true) {
                JOptionPane.showMessageDialog(null, "Felicitaciones termino el juego");
            }
        }
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

    /**
     * Meotodo para verificar si el aro que se va a poner es mas pequeño que el
     * que hay en la torre
     *
     * @param aroPresente
     * @param aroAMover
     * @return
     */
    public boolean verificar(Disco aroPresente, Disco aroAMover) {
        //ancho del aro que hay en la torre
        int w = aroPresente.getWidth();
        //ancho del aro que se quiere poner
        int w2 = aroAMover.getWidth();

        if (w > w2) {
            return true;
        } else {
            return false;
        }
    }   
    
}

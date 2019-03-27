/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author enrique
 */
public class Proyecto2 implements Serializable{

    
    String cadena;
    //guarda el numero de pasos completos
    int contador;

    Proyecto2() {
        cadena = "";
        contador=0;
    }

    
    public void hanoi(int num, int inicio, int auxiliar, int fin) throws IOException {
        if (num == 1) {
            System.out.println("Mover de torre "+ inicio+ " a la torre"+ fin);
            contador++;
        } else {
            hanoi(num - 1, inicio, fin, auxiliar);
            System.out.println("Mover de torre "+ inicio+ " a la torre"+ fin);
            contador++;
            hanoi(num - 1, auxiliar, inicio, fin);
        }
    }


    public int getContador() {
        return contador;
    }


    public void setContador(int contador) {
        this.contador = contador;
    }
    
}

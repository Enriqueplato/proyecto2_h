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
    ArrayList<String> cadenas;
    //guarda el numero de pasos completos
    int contador;

    Proyecto2() {
        cadena = "";
        cadenas= new ArrayList<>();
        contador=0;
    }

    
    public void hanoi(int num, int inicio, int auxiliar, int fin) throws IOException {
        if (num == 1) {
            //cadena = "Mover de la torre " + inicio + " a la torre " + fin;
            contador++;
            //cadenas.add(cadena);
            
        } else {
            hanoi(num - 1, inicio, fin, auxiliar);
            //cadena = "Mover de la torre " + inicio + " a la torre " + fin;
            contador++;
            //cadenas.add(cadena);
            
            hanoi(num - 1, auxiliar, inicio, fin);
        }
    }
   
    public boolean verificarFinalJuego(int n, int numeroArosTorre3){
        
        return n==numeroArosTorre3;
    }


    public int getContador() {
        return contador;
    }


    public void setContador(int contador) {
        this.contador = contador;
    }
    
}

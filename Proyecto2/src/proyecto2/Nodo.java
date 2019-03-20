/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author enrique
 */
public class Nodo {
    private Nodo sig;
    Disco discos;
    
    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    public Disco getDiscos() {
        return discos;
    }

    public void setDiscos(Disco discos) {
        this.discos = discos;
    }
    
    
}

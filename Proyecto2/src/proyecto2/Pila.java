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
public class Pila {
    private Nodo tope;
    int size=0;
    public void Insertar(Nodo nuevo){
        if(vacia()){
          tope =  nuevo;
        }
        else{
            Nodo aux = tope;
            nuevo.setSig(aux);
            tope = nuevo;
        }
        size++;
    }

    public Nodo getTope() {
        return tope;
    }

    public void setTope(Nodo tope) {
        this.tope = tope;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void Desapilar(){
        Nodo aux = tope;
       tope = aux.getSig();
       aux = null;
       size--;
    }
    
    public boolean vacia(){
        return(tope==null)?true:false;
    }

}

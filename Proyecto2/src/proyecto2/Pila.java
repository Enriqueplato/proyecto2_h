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
    private int tamanio, cima;
    private Nodo inicio;
   
    public void crear()
    {
        inicio = null;
        tamanio = 0;  
    }
    public boolean es_vacia()
    {
        return inicio == null;
    }
    
    
    public int cima() throws Exception {
        if(!es_vacia()){
            return inicio.getValor();
        }else {
            throw new Exception ("La pila esta vacia");
        } 
    }
    
    
    public void apilar(int valor)
    {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor); // le asignamos un valor al atrivuto    
        if(es_vacia()){
            inicio = nuevo;  
        }else {         // en caso de que la pila este vacia se agrega un nodo
              nuevo.setSiguiente(nuevo);
              inicio = nuevo;
        } 
        tamanio++;
    }
    
    public void desapilar()
    {
        Nodo aux = inicio;
        inicio = inicio.getSiguiente();
        aux = null;
        tamanio--;
    }
    public boolean verificar(int dato)
    {
        boolean x ;
        if(dato%2==0)
            x = true;
        else
            x = false;
        return x;
    }
    public void Visualizar(){
        //Para visualizar la pila creamos una copia de ella
        Nodo aux = inicio;   
        while(aux != null){
            System.out.println("|\t" + aux.getValor() + "\t|");
            System.out.println("-----------------");
            aux = aux.getSiguiente();
        }
    }

}

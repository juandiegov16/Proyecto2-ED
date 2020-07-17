/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Cesar-Home
 */

public class ArbolBehavior <T extends Comparable<T>>{
    private NodoBehavior<T> raiz;
    
    public ArbolBehavior(){
        raiz = null;
    }
    public ArbolBehavior(NodoBehavior<T> raiz){
        this.raiz = raiz;
    }
    
    public NodoBehavior<T>  getRaiz(){
        return this.raiz;
    }
    public void  setRaiz(NodoBehavior<T> nodo){
        this.raiz =nodo;
    }
    public NodoBehavior<T> buscar(T data){
        return buscar(this.raiz, data);
    }
    private NodoBehavior<T> buscar(NodoBehavior<T> nodo, T data){
	if(nodo == null) return null;
	if(data.compareTo(nodo.getData()) == 0) 
            return nodo;
	if(data.compareTo(nodo.getData()) > 0)
            return buscar(nodo.getDer(), data);
	else
            return buscar(nodo.getIzq(), data);
    }

    public void insertar(T data){
        this.raiz = insertar(this.raiz, data);
    }
    private NodoBehavior<T> insertar(NodoBehavior<T> nodo, T nuevo){
        if(nodo == null) 
		return new NodoBehavior(nuevo);
	if (nuevo.compareTo(nodo.getData())== 0) 
		return nodo;
	if (nuevo.compareTo(nodo.getData()) > 0) 
            nodo.setDer(insertar(nodo.getDer(), nuevo));
        else
            nodo.setIzq(insertar(nodo.getIzq(), nuevo));
	return nodo;
    }
    
    public void eliminar(T data) {
        this.raiz = eliminar(raiz, data);
    }
    private NodoBehavior<T> eliminar(NodoBehavior<T> nodo, T data) {
        if(nodo == null) 
            return null;
        if(data.compareTo(nodo.getData())== 0){
            if(nodo.getDer() == null && nodo.getIzq() == null)
                return null;
            if(nodo.getDer() == null)
                return nodo.getIzq();
            if(nodo.getIzq() == null)
                return nodo.getDer();
            nodo.setData(nodoMayor(nodo.getIzq()).getData());
            nodo.setIzq(eliminar(nodo.getIzq(), nodo.getData()));
            return nodo;
        }
        if(data.compareTo(nodo.getData()) > 0)
            nodo.setDer(eliminar(nodo.getDer(), data));
        else
            nodo.setIzq(eliminar(nodo.getIzq(), data));
        return nodo;
    }

    private NodoBehavior<T> nodoMayor(NodoBehavior<T> nodo){
        //nodo no es null
        NodoBehavior<T> temp= nodo;
        while (temp.getDer() != null)            
            temp = temp.getDer();
        return temp;
    }
    

    

}
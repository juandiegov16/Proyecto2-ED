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
public class NodoBehavior<T extends Comparable<T>> {
    private T data;
    private NodoBehavior<T> izq, der;
    private int action;

    public NodoBehavior(T data){
        this.data = data;
        izq = null;
        der = null;
        action = 0;
    }
    public NodoBehavior(T data, int action){
        this.data = data;
        izq = null;
        der = null;
        this.action = action;
    }
    public NodoBehavior(T data, NodoBehavior<T>  izq, NodoBehavior<T>  der){
        this.data = data;
        this.izq = izq;
        this.der = der;
    }

    public T getData(){
        return this.data;
    }
    public int getAction(){
        return this.action;
    }
    public NodoBehavior<T>  getDer(){
        return this.der;
    }
    public NodoBehavior<T>  getIzq(){
        return this.izq;
    }
    public  void setData(T data){
        this.data =data;
    }
    public  void setAction(int action){
        this.action =action;
    }
    public  void setDer(NodoBehavior<T> nodo){
        this.der =nodo;
    }
    public void  setIzq(NodoBehavior<T> nodo){
        this.izq =nodo;
    }
    @Override
    public String toString() {
	return this.data.toString();
    }
}

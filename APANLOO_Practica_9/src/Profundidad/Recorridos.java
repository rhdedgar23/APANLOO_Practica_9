package Profundidad;

import java.util.LinkedList;

public class Recorridos {
	
	/*------Preorden------*/
	public static void preorden(NodoABB raiz) {
		
		if(raiz== null) {
			return;
		}
		else {
			System.out.print(raiz.dato + ",");
			preorden(raiz.izq);
			preorden(raiz.der);
		}
	}
	
	/*------Orden------*/
	public static void orden(NodoABB raiz) {
		
		if(raiz== null) {
			return;
		}
		else {
			orden(raiz.izq);
			System.out.print(raiz.dato + ",");
			orden(raiz.der);
		}
	}
	
	/*------Postorden------*/
	public static void postorden(NodoABB raiz) {
		
		if(raiz== null) {
			return;
		}
		else {
			postorden(raiz.izq);
			postorden(raiz.der);
			System.out.print(raiz.dato + ",");
		}
	}
	
	/*------Nivel------*/
	
	/*Primero, checa si el objeto raiz tiene algun dato. Lo esperado es que si pero se pone el if por si acaso.
	A continuacion, crea la fila en la que se iran poniendo los nodos y agrega la raiz a esa fila. Aqui, fila.size()==1. 
	Luego, mientras la fila tenga nodos, se obtiene al nodo que se encuentra en la posicion 0, se imprime su dato. 
	Despues,checa si ese nodo tiene descendientes izquierdo o derechos. En el caso de que si, se agregan a la fila
	y se elimina de la fila. 
	Se reinicia el ciclo dando paso a los nodos en la fila.*/
	public static void nivel(NodoABB raiz) {
		
		if(raiz== null) {
			System.out.println("Arbol vacio!");
			return;
		}
		else {
			
			LinkedList<NodoABB> fila= new LinkedList<>();
			fila.add(raiz);
			
			while(fila.size()>0) {
				
					 NodoABB nodo= fila.getFirst();
					 System.out.print(nodo.dato + ",");
					 
					 if(nodo.izq != null) {
						 fila.add(nodo.izq);
					 }
					 if(nodo.der != null) {
						 fila.add(nodo.der);
					 }
					 
					 fila.remove();
			}
		}
	}
}

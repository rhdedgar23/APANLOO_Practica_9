package Profundidad;

import java.util.ArrayList;
import java.util.Collections;

public class Principal {
	
	static ArrayList<Integer> numAleatorios;
	static NodoABB abb= null;
	static NodoAVL avl= null;
	
	public static void main(String[] args) {
		
		//Primero, generamos los numeros aleatorios sin repetir del 0 al 1024
		numAleatorios= new ArrayList<>();
		generaNumAleNoRep(0, 1025);
		/*
		numAleatorios.add(68);
		numAleatorios.add(45);
		numAleatorios.add(29);
		numAleatorios.add(75);
		numAleatorios.add(90);
		numAleatorios.add(70);
		numAleatorios.add(34);
		*/
		/*
		numAleatorios.add(10);
		numAleatorios.add(6);
		numAleatorios.add(4);
		numAleatorios.add(9);
		*/
		/*
		numAleatorios.add(10);
		numAleatorios.add(16);
		numAleatorios.add(12);         
		*/
		//Despues, insertamos cada uno de esos numeros en un arbol binario de busqueda
		System.out.println("\n\nSe agregan los numeros a un arbol binario de busqueda.");
		insertaNumsEnABB();
		//desplegamos para verificar el arbol
		System.out.println("Recorrido en orden del arbol BB: ");
		Recorridos.orden(abb);
		System.out.println("\nRecorrido por nivel del arbol BB: ");
		Recorridos.nivel(abb);
		System.out.println("\nAltura: " + OperacionesNucleo.getAltura(abb));
		
		System.out.println("\nAhora, los mismos numeros se agregan a un arbol AVL. ");
		insertaNumsEnAVL();
		System.out.println("Recorrido en orden del arbol AVL: ");
		NodoAVL.orden(avl);
		System.out.println("\nRecorrido por nivel del arbol AVL: ");
		NodoAVL.nivel(avl);
		System.out.println("\nEn este arbol AVL la altura tiene que ser menor o igual que en el arbol ABB. \nAltura: " + NodoAVL.getAltura(avl));
		
		System.out.println("\nEliminamos los primeros 512 numeros del arreglo de numeros aleatorios no repetidos.");
		eliminaNumAleNoRepDeABB(512);
		System.out.println("Altura arbol ABB: " + OperacionesNucleo.getAltura(abb));
		eliminaNumAleNoRepDeAVL(512);
		System.out.println("Altura arbol AVL: " + NodoAVL.getAltura(avl));
		
	}
	
	public static void generaNumAleNoRep(int paramInferior, int paramSuperior) {
		
		//primero, solo metemos los numeros a un arreglo
		for(int i=paramInferior; i<paramSuperior; i++) {
			
			numAleatorios.add(i);
		}
		
		System.out.println("Arreglo de numeros ANTES de revolver: ");
		for(int i=0; i<numAleatorios.size(); i++) {
			
			System.out.print(numAleatorios.get(i) + ",");
		}
		
		//Luego, los revolvemos para crear numeros "aleatorios" no repetidos
		Collections.shuffle(numAleatorios);
		
		System.out.println("\n\nArreglo de numeros DESPUES de revolver: ");
		for(int i=0; i<numAleatorios.size(); i++) {
			
			System.out.print(numAleatorios.get(i) + ",");
		}
	}
	
	public static void eliminaNumAleNoRepDeABB(int cantidad) {
		
		for(int i=0; i<=cantidad; i++) {
			NodoABB.eliminaABB(abb, numAleatorios.get(i));
		}
	}
	
	public static void eliminaNumAleNoRepDeAVL(int cantidad) {
		
		for(int i=0; i<=cantidad; i++) {
			NodoAVL.eliminaAVL(avl, numAleatorios.get(i));
		}
	}
	
	public static void insertaNumsEnABB() {
		
		for(int i=0; i<numAleatorios.size(); i++) {
			abb= NodoABB.insertaABB(abb, numAleatorios.get(i));
		}
	}
	
	public static void insertaNumsEnAVL() {
		
		for(int i=0; i<numAleatorios.size(); i++) {
			avl= NodoAVL.insertaAVL(avl, numAleatorios.get(i));
		}
	}
}



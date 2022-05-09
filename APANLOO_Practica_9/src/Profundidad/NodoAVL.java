package Profundidad;

import java.util.LinkedList;

public class NodoAVL {

	int dato;
	NodoAVL izq;
	NodoAVL der;
	
	/*------Constructores------*/
	public NodoAVL(int dato, NodoAVL izq, NodoAVL der) {
		this.dato= dato;
		this.izq= izq;
		this.der= der;
	}
	
	public NodoAVL(int dato) {
		this.dato= dato;
	}
	
	
	/*------Metodos Principales [Inserta, Elimina, Busca, Max, Min]*/
	
	public static int factorDeBalance(NodoAVL arbol) {
		
		if(arbol==null) {
			return 0;
		}
		else {
			return getAltura(arbol.izq)-getAltura(arbol.der);
		}
	}
	
	public static int getAltura(NodoAVL raiz) {
		
		if(raiz== null) {
			return -1;
		}
		else {
			
			int alturaIzq= getAltura(raiz.izq);
			int alturaDer= getAltura(raiz.der);
			
			int altura= (Math.max(alturaIzq, alturaDer))+1;
			 
			return altura;
		}
	}
	
	public static NodoAVL aplicaRotacion(NodoAVL arbol) {
		
		int balance= factorDeBalance(arbol);
		
		//si el balance del nodo raiz es >1, sabemos que tenemos un arbol con izquierda pesada
		if(balance>1) {
			//si el balance del nodo izquierdo del nodo pasado como parametro es negativo, entonces
			//aplicamos una rotacion izquierda a ese descendiente
			if(factorDeBalance(arbol.izq)< 0) {
				arbol.izq= rotaIzquierda(arbol.izq);
			}
			
			return rotaDerecha(arbol);
		}
		
		//si el balance es <-1, tenemos un arbol con derecha pesada
		if(balance<-1) {
			//si el balance del nodo derecho del nodo pasado como parametro es positivo, entonces
			//aplicamos una rotacion derecha a ese descendiente
			if(factorDeBalance(arbol.der)> 0) {
				arbol.der= rotaDerecha(arbol.der);
			}
			return rotaIzquierda(arbol);
		}
		
		return arbol;
	}
	
	public static NodoAVL rotaDerecha(NodoAVL arbol) {
		//primero guardamos el descendiente izquierdo del nodo raiz en un nodo auxiliar
		NodoAVL nodoIzq= arbol.izq;
		//ahora, guardamos el descendiente derecho de nodoIzq en otro nodo auxiliar
		NodoAVL nodoCentral= nodoIzq.der;
		//Al aplicar la rotacion derecha, el nodo raiz ahora es descendiente derecho de nodoIzq
		nodoIzq.der= arbol;
		//y el nodoCentral es ahora descendiente izquierdo del nodo raiz
		arbol.izq= nodoCentral;
		//Al final, regresamos el valor del nuevo nodo raiz 
		return nodoIzq;
	}
	
	public static NodoAVL rotaIzquierda(NodoAVL arbol) {
		//primero guardamos el descendiente derecho del nodo raiz en un nodo auxiliar
		NodoAVL nodoDer= arbol.der;
		//ahora, guardamos el descendiente derecho de nodoDer en otro nodo auxiliar
		NodoAVL nodoCentral= nodoDer.izq;
		//Al aplicar la rotacion izquierda, el nodo raiz ahora es descendiente izquierdo de nodoDer
		nodoDer.izq= arbol;
		//y el nodoCentral es ahora descendiente derecho del nodo raiz
		arbol.der= nodoCentral;
		//Al final, regresamos el valor del nuevo nodo raiz 
		return nodoDer;
	}
	
	
	/*------Inserta------*/
	
	public static NodoAVL insertaAVL(NodoAVL arbol, int dato){
		
		if(arbol== null) {
			arbol= new NodoAVL(dato, null, null);
		}
		//existe almenos un nodo, entonces se verifica si se insertara en la parte
		//izquierda o derecha
		else {
			if(dato < arbol.dato) { 
				arbol.izq= insertaAVL(arbol.izq, dato);
			}
			else {
				arbol.der= insertaAVL(arbol.der, dato);
			}
		}
		
		return aplicaRotacion(arbol);
	}
	
	/*------Elimina------*/
	
	public static NodoAVL eliminaAVL(NodoAVL arbol, int dato) {
		
		if(arbol== null) {
			return null;
		}
		
		if(dato < arbol.dato) {
			arbol.izq= eliminaAVL(arbol.izq, dato);
		}
		else {
			if(dato > arbol.dato) {
				arbol.der= eliminaAVL(arbol.der, dato);
			}
			
			//si no es ni menor ni mayor, entonces es igual
			else {
				if(dato==arbol.dato) {//lo encontramos
					
					NodoAVL aux= arbol;
					
					if(arbol.izq==null) {//solo tienen un hijo y es derecho (que puede ser numero o nulo)
						
						arbol= arbol.der;
					}
					else {//pudede tener un hijo y es izquierdo o puede tener ambos hijos
						
						if(arbol.der==null) {
							arbol= arbol.izq;
						}
						else {//tiene dos hijos
							arbol.izq= sustMayorDeMenores(arbol.izq, aux);
						}
					}	
				}
			}
		}
		
		return aplicaRotacion(arbol);	
	}

	public static NodoAVL sustMayorDeMenores(NodoAVL arbol, NodoAVL aux) {
		
		if(arbol.der != null) {
			arbol.der= sustMayorDeMenores(arbol.der, aux);
		}
		else {
			aux.dato= arbol.dato;
			arbol= arbol.izq;
		}
		
		return arbol;
	}
	
	/*------Busca------*/
	
	public static boolean buscaAVL(NodoAVL arbol, int dato) {
		
		if(arbol != null) {
			
			if(arbol.dato== dato) {
				return true;
			}
			else {
				
				if(dato < arbol.dato) {
					
					return buscaAVL(arbol.izq, dato);
				}
				else {
					
					return buscaAVL(arbol.der, dato);
				}
			}
		}
		else {
			return false;
		}
	}
	
	/*------Max y Min------*/
	public static int encuentraMax(NodoAVL arbol) {
		
		if(arbol != null) {
			
			if(arbol.der== null) {
				return arbol.dato;
			}
			else {
				return encuentraMax(arbol.der);
			}
		}
		else {
			return -1;
		}
	}
	
	public static int encuentraMin(NodoAVL arbol) {
		
		if(arbol != null) {
			
			if(arbol.izq== null) {
				return arbol.dato;
			}
			else {
				return encuentraMin(arbol.izq);
			}
		}
		else {
			return -1;
		}
	}
	
	
	/*------Recorridos------*/
	
	/*------Orden------*/
	public static void orden(NodoAVL raiz) {
		
		if(raiz== null) {
			return;
		}
		else {
			orden(raiz.izq);
			System.out.print(raiz.dato + ",");
			orden(raiz.der);
		}
	}
	
	/*------Nivel------*/
	public static void nivel(NodoAVL raiz) {
		
		if(raiz== null) {
			System.out.println("Arbol vacio!");
			return;
		}
		else {
			
			LinkedList<NodoAVL> fila= new LinkedList<>();
			fila.add(raiz);
			
			while(fila.size()>0) {
				
					 NodoAVL nodo= fila.getFirst();
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

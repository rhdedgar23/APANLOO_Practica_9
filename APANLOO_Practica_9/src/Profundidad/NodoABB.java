package Profundidad;

public class NodoABB {

	int dato;
	NodoABB izq;
	NodoABB der;
	
	public NodoABB(int dato, NodoABB izq, NodoABB der) {
		this.dato= dato;
		this.izq= izq;
		this.der= der;
	}
	
	public NodoABB(int dato) {
		this.dato= dato;
	}
	
	public static NodoABB insertaABB(NodoABB arbol, int dato){
		
		if(arbol== null) {
			arbol= new NodoABB(dato, null, null);
		}
		//existe almenos un nodo, entonces se verifica si se insertara en la parte
		//izquierda o derecha
		else {
			if(dato < arbol.dato) { 
				arbol.izq= insertaABB(arbol.izq, dato);
			}
			else {
				arbol.der= insertaABB(arbol.der, dato);
			}
		}
		
		return arbol;
	}
	
	public static boolean buscaABB(NodoABB arbol, int dato) {
		
		if(arbol != null) {
			
			if(arbol.dato== dato) {
				return true;
			}
			else {
				
				if(dato < arbol.dato) {
					
					return buscaABB(arbol.izq, dato);
				}
				else {
					
					return buscaABB(arbol.der, dato);
				}
			}
		}
		else {
			return false;
		}
	}
	
	public static NodoABB eliminaABB(NodoABB arbol, int dato) {
		
		if(arbol== null) {
			return null;
		}
		
		if(dato < arbol.dato) {
			arbol.izq= eliminaABB(arbol.izq, dato);
		}
		else {
			if(dato > arbol.dato) {
				arbol.der= eliminaABB(arbol.der, dato);
			}
			
			//si no es ni menor ni mayor, entonces es igual
			else {
				if(dato==arbol.dato) {//lo encontramos
					
					NodoABB aux= arbol;
					
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
		
		return arbol;	
	}

	public static NodoABB sustMayorDeMenores(NodoABB arbol, NodoABB aux) {
		
		if(arbol.der != null) {
			arbol.der= sustMayorDeMenores(arbol.der, aux);
		}
		else {
			aux.dato= arbol.dato;
			arbol= arbol.izq;
		}
		
		return arbol;
	}
	
	public static int encuentraMax(NodoABB arbol) {
		
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
	
	public static int encuentraMin(NodoABB arbol) {
		
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
}

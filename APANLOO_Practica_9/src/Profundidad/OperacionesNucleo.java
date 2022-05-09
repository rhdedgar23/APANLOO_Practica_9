package Profundidad;

public class OperacionesNucleo{
	
	public  String getRaiz(NodoABB raiz) {
		return String.valueOf(raiz.dato);
	}
	
	public static int getAltura(NodoABB raiz) {
		
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
	
	public static int getNumNodos(NodoABB raiz) {
		
		if(raiz== null) {
			
			return 0;
		}
		else {
			
			int contadorIzq= getNumNodos(raiz.izq);
			int contadorDer= getNumNodos(raiz.der);
			
			int contador= contadorIzq + contadorDer +1;
			
			return contador;
		}
	}
}

import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	boolean flag1 = true;
	int respuesta = 0;
	ArbolB arbolito = new ArbolB();

	while(flag1){
	    System.out.println("--ArbolB.--\n1) Insertar(x).\n2) Buscar(x).\n3) Eliminar(x).\n4) Imprimir el arbol.\n5) Salir.");
	    respuesta = input.nextInt();
	    
	    if(respuesta == 1){
			System.out.println("Ingresa la llave a insertar:");
			int llave = input.nextInt();
			arbolito.insertar(llave);
			System.out.println("elemento guardado con exito");

			


		
	    }else{
		if(respuesta == 2) {
			System.out.println("Ingresa la llave a buscar:");
			int llave2 = input.nextInt();
			if(arbolito.buscar(llave2)){
				System.out.println("el elemento se encuentra en el árbol");

			} else {
				System.out.println("el elemento NO se encuentra en el árbol");
			}
			   
		}else{
		    if(respuesta == 3){
				System.out.println("Ingresa la llave a eliminar:");
				int llave3 = input.nextInt();
				arbolito.eliminar(llave3);
				System.out.println("elemento eliminado con exito");

		    }else{
			if(respuesta == 4) {
				System.out.println("El árbol (por niveles) es:");
				arbolito.imprimirPorNiveles();
			    
			}else{
			    if(respuesta == 5) {
				System.out.println("Gracias vuelva pronto!!!  :)");
				flag1 = false;
			    }else{
				System.out.println("Elige una opcion valida!!! :( ");
			    }
			}
		    }
		}
	    }
	}
    }
}

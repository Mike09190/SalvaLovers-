import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	boolean flag1 = true;
	int respuesta = 0;

	while(flag1){
	    System.out.println("--ArbolB.--\n1) Insertar(x).\n2) Buscar(x).\n3) Eliminar(x).\n4) Imprimir el arbol.\n5) Salir.");
	    respuesta = input.nextInt();
	    
	    if(respuesta == 1){
		
	    }else{
		if(respuesta == 2) {
		    
		}else{
		    if(respuesta == 3){
			
		    }else{
			if(respuesta == 4) {
			    
			}else{
			    if(respuesta == 5) {
				System.out.println("Gracias vuelva pronto!!!");
				flag1 = false;
			    }else{
				System.out.println("Elige una opcion valida!!!");
			    }
			}
		    }
		}
	    }
	}
    }
}

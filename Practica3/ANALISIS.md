#Paso 1 - Inicial

##Preguntas
**¿Qué hace el método agregarArchivo?**
El método agregarArchivo se encarga de agregar archivos de tipo PDF o TXT dentro de la carpeta indicada en sus atributos.

** ¿Qué hace el método obtenerTamanio?
El método obtenerTamanio se encarga de calcular la suma del tamaño de una carpeta iterando en sus archivos y en sus subcarpetas, utilizando el mismo método dentro de estas últimas.

**¿Qué hace el método enviarResultado?**
El método enviarResultado se encarga de crear un nuevo objeto de CorreoLegacy para mandar el correo  al destino con el tamaño total de la carpeta de los atributos utilizando el método anterior.

##Problemas concretos
1.Métodos en el main. El main principalmente se encarga del algoritmo del problema, no es en sí una solución concreta y colocar los métodos principales dentro de este se considera una mala práctica y mala organización del código, remarcando el hecho de que se rompe el concepto de POO.
2. Archivo nuevo que no sea PDF o TXT. Puede que el usuario quiera pasar un nuevo archivo (como un .jpg) pero este no existe en los subtipos de Archivo, por lo cual es otro error a indicar.
3. Clase Archivo abstracta, no tiene método abstracto, entonces no puede ser llamada como una clase abstracta.
4. Clase Carpeta y clase Archivo no tienen sus atributos privados o protegidos, rompiendo el concepto de encapsulación y seguridad.
5. El método send_email en la clase CorreoLegacy se debe de adaptar (con una interfaz) para que los proveedores no tengan problemas al utilizarlo.

# Práctica 3: Refactorización y diseño modular

## Descripción

Programa en Java que calcula el tamaño total de una carpeta con archivos y subcarpetas y muestra el resultado mediante un correo simulado.

La versión final se encuentra en `Paso5` y aplica los patrones:

- **Composite:** archivos y carpetas comparten la interfaz `Elemento`.
- **Factory Method:** la creación de archivos se realiza mediante `CreadorPDF` y `CreadorTexto`.
- **Adapter:** `AdaptadorCorreo` conecta la interfaz `Notificador` con `CorreoLegacy`.

## Requisitos

Tener instalado un JDK de Java y disponer de los comandos `javac` y `java`.

Para comprobar la instalación:

```sh
javac -version
java -version
```

No se requieren bibliotecas externas ni un servicio de correo real.

## Compilar y ejecutar la versión final

Desde la carpeta `Practica3`, ejecutar:

```sh
cd Paso5
javac -encoding UTF-8 *.java
java Main
```

El comando `javac` compila todos los archivos Java de `Paso5`. El comando `java Main` ejecuta el ejemplo y las pruebas.

Las carpetas contienen versiones distintas con clases del mismo nombre, por lo que deben compilarse por separado. El `Main.java` de la raíz no corresponde a la versión final.

## Ejemplo principal

El programa crea:

- Un archivo PDF de tamaño 120.
- Un archivo de texto de tamaño 80.
- Una subcarpeta con un archivo de texto de tamaño 50.

El tamaño total esperado es `120 + 80 + 50 = 250`.

La salida obtenida fue:

```text
250
Para: profesor@universidad.edu
Tamanio total: 250
```

El correo es simulado: solo imprime el destinatario y el mensaje.

## Resultados de las pruebas finales

Las cinco pruebas están incluidas en `Paso5/Main.java` y se ejecutan junto con el ejemplo principal.

| Prueba | Entrada | Esperado | Obtenido | Resultado |
| --- | --- | ---: | ---: | --- |
| 1 | Carpeta sin archivos ni subcarpetas | 0 | 0 | OK |
| 2 | Carpeta con un PDF de tamaño 120 | 120 | 120 | OK |
| 3 | Carpeta con un PDF de 120 y un texto de 80 | 200 | 200 | OK |
| 4 | Carpeta con PDF de 120, texto de 80 y una subcarpeta con texto de 50 | 250 | 250 | OK |
| 5 | Carpeta con un archivo de tamaño cero | 0 | 0 | OK |

También se comprobó el ejemplo principal, cuyo tamaño esperado y obtenido fue 250.

### Evidencia de ejecución

La compilación mediante `javac -encoding UTF-8 *.java` terminó sin mostrar errores.

Al ejecutar `java Main`, se obtuvo:

```text
250
Para: profesor@universidad.edu
Tamanio total: 250
OK: Prueba archivos del ejemplo

 Prueba 1:

OK: Carpeta vacia

 Prueba 2:

OK: Prueba2

 Prueba 3:

OK: Prueba3

 Prueba 4:

OK: Prueba4

 Prueba 5:

OK: Prueba5
```

El método de comprobación imprime `OK` cuando el valor obtenido coincide con el esperado. Los valores de la tabla corresponden a esas comparaciones.

Estos resultados verifican los cinco escenarios solicitados; no garantizan la ausencia de errores en todos los casos posibles.

## Análisis y diagrama

El diagnóstico del diseño inicial, la explicación de los cambios, las responsabilidades finales y el diagrama se encuentran en [ANALISIS.md](ANALISIS.md).

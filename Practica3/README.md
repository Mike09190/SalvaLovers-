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

## Compilación y ejecución

Cada versión debe compilarse por separado porque las carpetas contienen clases con los mismos nombres.

### Versión inicial: Paso2

Desde la carpeta `Practica3`, ejecutar:

```sh
cd Paso2
javac -encoding UTF-8 *.java
java Main
```

### Versión final: Paso5

Desde la carpeta `Practica3`, ejecutar:

```sh
cd Paso5
javac -encoding UTF-8 *.java
java Main
```

El comando `javac` compila los archivos Java de la carpeta seleccionada. El comando `java Main` ejecuta el ejemplo principal y las pruebas.

## Ejemplo principal

El programa crea:

- Un archivo PDF de tamaño 120.
- Un archivo de texto de tamaño 80.
- Una subcarpeta con un archivo de texto de tamaño 50.

El tamaño total esperado es `120 + 80 + 50 = 250`.

Ambas versiones muestran:

```text
250
Para: profesor@universidad.edu
Tamanio total: 250
```

El correo es simulado: imprime el destinatario y el mensaje en la terminal.

## Pruebas iniciales y finales

Las pruebas se encuentran en los archivos `Main.java` de `Paso2` y `Paso5`.

Cada prueba prepara una carpeta con los elementos indicados, calcula su tamaño y compara el resultado con el valor esperado.

| Prueba | Entrada | Esperado | Obtenido en Paso2 | Resultado inicial | Obtenido en Paso5 | Resultado final |
| --- | --- | ---: | ---: | --- | ---: | --- |
| 1 | Carpeta sin archivos ni subcarpetas | 0 | 0 | OK | 0 | OK |
| 2 | Carpeta con un PDF de tamaño 120 | 120 | 120 | OK | 120 | OK |
| 3 | Carpeta con un PDF de 120 y un texto de 80 | 200 | 200 | OK | 200 | OK |
| 4 | Carpeta con PDF de 120, texto de 80 y una subcarpeta con texto de 50 | 250 | 250 | OK | 250 | OK |
| 5 | Carpeta con un archivo de tamaño cero | 0 | 0 | OK | 0 | OK |

El método de comprobación imprime `OK` cuando el valor obtenido coincide con el esperado. Si no coincide, imprime `FALLO` junto con ambos valores.

### Evidencia de ejecución de Paso2

La compilación terminó sin errores. Al ejecutar `java Main`, se obtuvo:

```text
250
Para: profesor@universidad.edu
Tamanio total: 250

 Prueba 1:

OK: Carpeta vacia

 Prueba 2:

OK: Prueba2

 Prueba 3:

OK: Prueba3

 Prueba 4:

OK: Prueba4

 Prueba 5:

OK: Carpeta5
```

### Evidencia de ejecución de Paso5

La compilación terminó sin errores. Al ejecutar `java Main`, se obtuvo:

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

### Resultado de la comparación

Las versiones inicial y final obtuvieron los tamaños esperados en los cinco casos: 0, 120, 200, 250 y 0.

Ambas conservaron el total de 250 y el mismo destinatario y mensaje del correo simulado.

La versión final también incluye una comprobación del ejemplo principal, que muestra `OK: Prueba archivos del ejemplo`.

## Análisis y diagrama

El diagnóstico del diseño inicial, la explicación de la refactorización, las responsabilidades finales y el diagrama se encuentran en [ANALISIS.md](ANALISIS.md).
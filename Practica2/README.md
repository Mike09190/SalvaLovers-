# Práctica 3 — Árbol B

Implementación de un **Árbol B ** utilizando Java, con el propósito de estudiar el almacenamiento ordenado de llaves, la búsqueda por intervalos y el balanceo de la estructura mediante divisiones, redistribuciones y fusiones.

---

## Información general

| Característica | Descripción |
|---|---|
| **Lenguaje** | Java |
| **Estructura principal** | Árbol B acotado |
| **Orden del árbol** | `m = 4` |
| **Máximo de llaves por nodo** | `3` |
| **Máximo de hijos por nodo** | `4` |
| **Tipo de llaves** | Números enteros |
| **Operaciones principales** | Insertar, buscar, eliminar e imprimir |
| **Forma de interacción** | Menú interactivo |

---

## Estructura del programa

El programa se basa principalmente en las clases `Nodo` y `ArbolB`.

La clase `Nodo` representa cada uno de los nodos que forman el árbol. Cada nodo contiene una lista de **llaves enteras ordenadas** y una lista de referencias a sus **hijos**. También conserva una referencia a su nodo padre, un indicador para saber si es una hoja y contadores para registrar la cantidad de llaves e hijos.

La clase `ArbolB` se encarga de administrar la raíz y las operaciones principales de la estructura. En ella se realiza la búsqueda del intervalo correcto, la inserción de nuevas llaves, la división de nodos desbordados y la eliminación con reparación de subocupaciones.

De forma general, un nodo se representa de la siguiente manera:

```text
Nodo
├── llaves  → [k1, k2, k3]
├── hijos   → [P0, P1, P2, P3]
├── padre   → nodo padre o null
└── esHoja  → true o false
```

Las llaves siempre deben permanecer ordenadas. Además, un nodo interno que contiene `r` llaves debe tener exactamente `r + 1` hijos.

Por ejemplo:

```text
        [15 | 30]
        /   |   \
      P0   P1   P2
```

El nodo contiene dos llaves y, por lo tanto, necesita tres hijos.

---

## Instrucciones para ejecutar el programa

Para ejecutar el programa se debe utilizar la clase `Main.java`, ya que en ella se encuentra el menú interactivo y el flujo principal para utilizar las operaciones del árbol.

Desde la carpeta de la práctica se pueden compilar y ejecutar los archivos con los siguientes comandos:

```bash
javac *.java
java Main
```

Si se utiliza un entorno de desarrollo, basta con ejecutar directamente la clase `Main`.

---

## Menú interactivo y pruebas

La comprobación del funcionamiento se realiza mediante un **menú interactivo**. Al ejecutar la clase `Main` se muestran las siguientes opciones:

```text
1) Insertar(x)
2) Buscar(x)
3) Eliminar(x)
4) Imprimir el árbol
5) Salir
```

Desde este menú se pueden utilizar las operaciones implementadas en el árbol y observar el resultado después de cada acción.

Para verificar el programa se recomienda probar desde el menú:

- la inserción de varias llaves en distinto orden;
- la búsqueda de llaves existentes e inexistentes;
- la impresión del árbol para observar sus niveles;
- la inserción de suficientes llaves para provocar una división;
- la eliminación de llaves que no produzcan subocupación;
- la eliminación de llaves que requieran redistribución o fusión;
- el intento de insertar una llave repetida, comprobando que el árbol no cambie.

Después de cada operación se puede imprimir el árbol y comprobar que las llaves de cada nodo continúen ordenadas, que ningún nodo conserve más de tres llaves y que todas las hojas permanezcan en el mismo nivel.

---

## Orden del árbol

En esta práctica el orden del árbol está fijado como:

```text
m = 4
```

El valor de `m` representa la cantidad máxima de hijos que puede tener un nodo. Como un nodo con `m` hijos puede contener como máximo `m - 1` llaves:

```text
m - 1 = 4 - 1 = 3
```

Por esta razón, cada nodo puede contener como máximo **tres llaves** y **cuatro hijos**.

La aparición de una cuarta llave se permite únicamente como un estado temporal durante la inserción. En ese momento el nodo se encuentra desbordado y debe dividirse.

Para los nodos distintos de la raíz, el mínimo de llaves es:

```text
q = ceil(m / 2) - 1
q = ceil(4 / 2) - 1
q = 1
```

Por lo tanto, un nodo que no sea la raíz debe conservar al menos una llave. Si queda con cero llaves después de una eliminación, se produce una subocupación que debe repararse.

---

## Búsqueda y elección del hijo

Para buscar una llave se comienza desde la raíz y se comparan, en orden, las llaves del nodo actual.

- Si existe una coincidencia, la búsqueda termina correctamente.
- Si no existe una coincidencia y el nodo es una hoja, la llave no se encuentra en el árbol.
- Si el nodo tiene hijos, las comparaciones permiten identificar el intervalo correspondiente y continuar únicamente por ese hijo.

Por ejemplo, el nodo:

```text
[20 | 40 | 70]
```

divide los valores en los siguientes intervalos:

| Hijo | Intervalo |
|---|---|
| `P0` | `x < 20` |
| `P1` | `20 < x < 40` |
| `P2` | `40 < x < 70` |
| `P3` | `x > 70` |

Si se busca la llave `45`, se continúa por `P2`, ya que se encuentra entre `40` y `70`.

---

## Inserción y división de un nodo

Para insertar una nueva llave, primero se busca la hoja correspondiente. La llave se agrega y se ordena junto con las demás llaves del nodo.

Mientras el nodo tenga como máximo tres llaves, no es necesario modificar la estructura. Cuando alcanza cuatro llaves ocurre un **desbordamiento** y se realiza una división o `split`.

La convención utilizada en esta práctica establece que, al tener cuatro llaves ordenadas:

```text
[k1 | k2 | k3 | k4]
```

- se promueve `k3`, que es la tercera llave;
- el nodo izquierdo conserva `[k1 | k2]`;
- el nodo derecho conserva `[k4]`.

Por ejemplo:

```text
Antes del split:

[10 | 20 | 30 | 40]

Después del split:

             [30]
            /    \
     [10 | 20]   [40]
```

La llave `30` se promueve al padre. Si el padre también alcanza cuatro llaves, el proceso se repite hacia arriba. Cuando el nodo dividido es la raíz, se crea una nueva raíz y aumenta la altura del árbol.

Si el nodo dividido no es una hoja, también deben repartirse sus hijos de acuerdo con los intervalos de los dos nuevos nodos.

---

## Eliminación de una llave

La eliminación comienza buscando la llave dentro del árbol.

Si la llave se encuentra en una hoja, puede eliminarse directamente. Después se comprueba que el nodo conserve al menos una llave.

Si la llave se encuentra en un nodo interno, se intenta reemplazar por su **predecesor**. Cuando no es posible utilizarlo, se intenta reemplazar por su **sucesor**. Si ninguno de los dos hijos correspondientes tiene una llave de sobra, se realiza una fusión y la eliminación continúa en el nodo fusionado.

Si un nodo distinto de la raíz queda con cero llaves, se produce una subocupación. Para corregirla se utiliza redistribución o fusión.

### Redistribución

La redistribución se realiza cuando uno de los hermanos tiene más de una llave y puede prestar una sin quedar subocupado.

El movimiento debe pasar por el padre:

```text
hermano → padre → nodo subocupado
```

Una llave del hermano sube al padre y la llave separadora del padre baja al nodo que se está reparando. De esta manera se conservan correctamente los intervalos del árbol.

Primero se intenta redistribuir con el hermano izquierdo y, si no es posible, con el hermano derecho.

### Fusión

La fusión se utiliza cuando ninguno de los hermanos puede prestar una llave. En este caso se combinan:

```text
nodo izquierdo + llave separadora del padre + nodo derecho
```

La fusión provoca que el padre pierda una llave y un hijo. Si el padre también queda subocupado, la reparación continúa hacia arriba.

Cuando la raíz queda vacía y tiene un único hijo, ese hijo se convierte en la nueva raíz y la altura del árbol disminuye.

---

## Preguntas a responder

### 1. ¿Por qué al insertar una llave nueva no podemos decidir el hijo únicamente comparando con la primera llave del nodo?

No podemos decidir el hijo comparando únicamente con la primera llave porque un nodo puede contener varias llaves y cada una participa en la separación de los intervalos.

En este árbol de orden `m = 4`, un nodo puede contener hasta tres llaves y separar hasta cuatro intervalos diferentes. Comparar solamente con la primera llave permite saber si el nuevo valor se encuentra antes o después de ella, pero no permite identificar en cuál de los demás intervalos debe continuar.

Por lo tanto, la nueva llave debe compararse en orden con todas las llaves necesarias hasta encontrar su intervalo exacto. Después se continúa por el hijo correspondiente hasta llegar a la hoja donde debe insertarse.

### 2. ¿Por qué una búsqueda no debe recorrer todos los hijos de un nodo?

Una búsqueda no debe recorrer todos los hijos porque las llaves ordenadas del nodo permiten identificar el único intervalo en el que podría encontrarse el valor buscado.

Los demás hijos contienen valores que pertenecen a intervalos diferentes, por lo que recorrerlos sería innecesario y haría que la búsqueda fuera menos eficiente.

Al comparar primero la llave buscada con las llaves del nodo y continuar únicamente por el hijo correspondiente, se aprovecha el orden del árbol y se reduce la cantidad de nodos que deben visitarse.

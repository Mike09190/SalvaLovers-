# Practica 1 — Tablas Hash

Implementación de una **Tabla Hash** utilizando Java, con el propósito de estudiar el funcionamiento de las funciones hash, la distribución de elementos dentro de una tabla y el manejo de colisiones.

---

## Información general

| Característica | Descripción |
|---|---|
| **Lenguaje** | Java |
| **Estructura principal** | Tabla Hash |
| **Estructura de almacenamiento** | `ArrayList` |
| **Estructura para las cubetas** | `LinkedList` |
| **Elemento almacenado** | `Nodo` |
| **Función Hash** | Módulo `% 7` |
| **Manejo de colisiones** | Encadenamiento |

---

## Estructura del programa

El programa se basa principalmente en una clase llamada `Nodo`, la cual representa cada uno de los elementos que se almacenan dentro de la tabla hash. Cada nodo contiene una **llave** y un **valor**, que permiten identificar y almacenar la información correspondiente.

La clase `HashTable` se encarga de administrar la tabla hash y sus diferentes operaciones. Para representar la tabla se utiliza un `ArrayList`, el cual funciona como una estructura dinámica que contiene las diferentes **cubetas** de la tabla.

Cada una de estas cubetas está representada mediante una `LinkedList`. Esto permite que una misma cubeta pueda contener varios objetos de tipo `Nodo`, lo cual es necesario para poder manejar las colisiones que se producen cuando diferentes llaves generan la misma posición mediante la función hash.

---

### Instrucciones para ejecutar el programa

Para ejecutar el programa se debe utilizar la clase `Interfaz.java`, ya que en ella se encuentra el flujo principal del programa y la interacción necesaria para utilizar las operaciones implementadas en la tabla hash.

---

### Funcion Hash

La funcion hash utilizada en esta practica se basa en el **operador** de modulo `%`

La posicion de una llave se obtiene utilizando.
```text
hash(llave) = llave % 7

Por ejemplo:

llave = 15
15 % 7 = 1

Por lo tanto, la llave 15 será almacenada en la cubeta 1.

```

---

### Manejo de Colisiones 

- Las colisiones se manejan mediante encadenamiento.
- Cada posición de la tabla contiene una `LinkedList`, lo que permite almacenar varios nodos dentro de una misma cubeta.
- Cuando una nueva llave produce una posición que ya contiene elementos, el nuevo nodo se agrega a la lista.

```text
Cubeta 1

┌───────────────────────────────┐
│ Nodo(15) → Nodo(22) → Nodo(29)│
└───────────────────────────────┘

Las llaves 15, 22 y 29 producen la misma posicion:
15 % 7 = 1
22 % 7 = 1
29 % 7 = 1
```
Por lo tanto, las tres terminan almacenadas en la misma `LinkedList`.

---

### Preguntas a responder

- 1.¿Por qué tener una colisión no significa que la tabla hash esté implementada incorrectamente?

Una colisión **no significa que una tabla hash esté implementada incorrectamente** porque las colisiones son un comportamiento normal en este tipo de estructuras de datos.

Esto ocurre porque existe un **número limitado de cubetas** y pueden existir **muchas llaves diferentes**. Por lo tanto, es posible que distintas llaves produzcan la misma posición mediante la función hash.

Lo importante **no es evitar completamente las colisiones**, sino **manejarlas correctamente**.

En esta práctica se utiliza el método de **encadenamiento**, mediante el cual los elementos que producen la misma posición se almacenan dentro de la `LinkedList` correspondiente.

Por lo tanto, una colisión por sí misma no representa un error; el problema existiría si la implementación no fuera capaz de manejarla correctamente.
    1
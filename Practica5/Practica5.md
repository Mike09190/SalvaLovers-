# Reporte de Desarrollo Guiado por Pruebas (TDD)
**Proyecto:** Cálculo del Costo de Envío  
**Herramienta de Pruebas:** Pytest  

---

## Preguntas Teóricas sobre TDD

### 1. ¿Qué significa que una prueba esté en estado **Rojo**?
Significa que la prueba fue creada pero falla debido a que la funcionalidad aún no existe o no ha sido implementada correctamente.

### 2. ¿Qué debe hacerse para pasar a estado **Verde**?
Escribir únicamente el código mínimo necesario para hacer que la prueba pase exitosamente.

### 3. ¿Qué diferencia existe entre agregar funcionalidad y refactorizar?
* **Agregar funcionalidad:** Introduce un nuevo comportamiento o regla de negocio al sistema.
* **Refactorizar:** Mejora la estructura, legibilidad o rendimiento del código sin alterar su comportamiento externo, asegurando que las pruebas se mantengan en **Verde**.

### 4. ¿Por qué en TDD no conviene implementar todos los requisitos desde el inicio?
Porque el enfoque TDD busca desarrollar únicamente el código necesario para satisfacer el requisito actual, evitando construir funcionalidades complejas o no solicitadas que aún no han sido validadas por una prueba.

### Durante el desarollo.
## Ciclo 1
1. **¿Qué prueba escribiste?**
   ```python
   def test_envio_estandar():
       assert calcular_envio(3, "estandar") == 50
2. ¿Por qué falló inicialmente?
La funcion no implementaba ninguna logica y no devolvia el valor
3. ¿Qué cambio mínimo hiciste?
Hice que regresara 50
4. ¿Todas las pruebas anteriores continuaron pasando?
Si
5. ¿Realizaste alguna refactorización?
No
---
## Ciclo 2
1. **¿Qué prueba escribiste?**
   ```python
   def test_envio_mayor_50():
        assert calcular_envio(7,"estandar") == 70
2. ¿Por qué falló inicialmente?
La funcion solo regresaba un valor constante.
3. ¿Qué cambio mínimo hiciste?
Implemente toda la logica necesaria para considerar el caso del sobrepeso
4. ¿Todas las pruebas anteriores continuaron pasando?
Si
5. ¿Realizaste alguna refactorización?
No
---
## Ciclo 3
1. **¿Qué prueba escribiste?**
   ```python
    def test_envio_express():
        assert calcular_envio(5,"express") == 100
    def test_envio_express_mayor_50():
        assert calcular_envio(7,"express") == 140
2. ¿Por qué falló inicialmente?
La funcion solo consideraba el caso del envio estandar
3. ¿Qué cambio mínimo hiciste?
Implemente toda la logica necesaria para considerar el caso del envio express
4. ¿Todas las pruebas anteriores continuaron pasando?
Si
5. ¿Realizaste alguna refactorización?
Si
---
## Ciclo 4
1. **¿Qué prueba escribiste?**
   ```python
    def test_envio_peso_0():
        with pytest.raises(ValueError):
            calcular_envio(0, "estandar")

    def test_envio_express_menor_0():
        with pytest.raises(ValueError):
            calcular_envio(-2, "express")
2. ¿Por qué falló inicialmente?
La funcion no consideraba el caso cuando el peso era cero o menor a cero
3. ¿Qué cambio mínimo hiciste?
Implemente el caso de la excepcion dentro del codigo
4. ¿Todas las pruebas anteriores continuaron pasando?
Si
5. ¿Realizaste alguna refactorización?
No
---
## Ciclo 5
1. **¿Qué prueba escribiste?**
   ```python
    def test_envio_tipo_invalido():
        with pytest.raises(ValueError):
            calcular_envio(3, "urgente")
2. ¿Por qué falló inicialmente?
La funcion no consideraba la validacion de la cadena correspondiente con el tipo
3. ¿Qué cambio mínimo hiciste?
Implemente el caso de la excepcion dentro del codigo
4. ¿Todas las pruebas anteriores continuaron pasando?
Si
5. ¿Realizaste alguna refactorización?
No
---

## Registro de Ciclos de Desarrollo (TDD)

| Ciclo | Prueba Agregada | Cambio Realizado en `envio.py` | Resultado |
| :---: | :--- | :--- | :---: |
| **1** | ```python<br>def test_envio_estandar():<br>    assert calcular_envio(3, "estandar") == 50<br>``` | Se implementó la función base retornando el valor directo para un envío estándar.<br>```python<br>def calcular_envio(peso, tipo):<br>    return 50<br>``` | PASSED 🟢 |
| **2** | ```python<br>def test_envio_mayor_50():<br>    assert calcular_envio(7, "estandar") == 70<br>``` | Se agregó el cálculo adicional para pesos mayores a 5 kg.<br>```python<br>def calcular_envio(peso, tipo):<br>    if peso > 5:<br>        return 50 + (peso - 5) * 10<br>    return 50<br>``` | PASSED 🟢 |
| **3** | ```python<br>def test_envio_express():<br>    assert calcular_envio(5, "express") == 100<br><br>def test_envio_express_mayor_50():<br>    assert calcular_envio(7, "express") == 140<br>``` | Se incluyó el soporte y la tarifa para el tipo de envío `"express"`. | PASSED 🟢 |
| **4** | ```python<br>def test_envio_peso_0():<br>    with pytest.raises(ValueError):<br>        calcular_envio(0, "estandar")<br><br>def test_envio_express_menor_0():<br>    with pytest.raises(ValueError):<br>        calcular_envio(-2, "express")<br>``` | Se añadió la validación para asegurar que el peso sea mayor a cero.<br>```python<br>if peso <= 0:<br>    raise ValueError("El peso debe ser mayor a cero")<br>``` | PASSED 🟢 |
| **5** | ```python<br>def test_envio_tipo_invalido():<br>    with pytest.raises(ValueError):<br>        calcular_envio(3, "urgente")<br>``` | Se añadió la validación de tipos de envío permitidos (`estandar` y `express`).<br>```python<br>if tipo not in ["estandar", "express"]:<br>    raise ValueError("Tipo no válido")<br>``` | PASSED 🟢 |


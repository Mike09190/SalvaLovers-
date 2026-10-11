
import pytest

from estacionamiento import Estacionamiento


@pytest.fixture
def sistema():
    """Preparación reutilizable para las pruebas."""
    return Estacionamiento()


# 12 CASOS EFECTIVOS

#CASOS NORMALES
def test_menos_de_15_minutos(sistema):
    assert sistema.calcular_total(0, "normal", False) == 0.0

def test_mas_de_tarifa_normal(sistema):
    assert sistema.calcular_total(17, "normal", False) == 20.0

#CASOS FRONTERA
def test_de_15_minutos(sistema):
    assert sistema.calcular_total(15, "normal", False) == 0.0

def test_de_mas_de_15_minutos(sistema):
    assert sistema.calcular_total(16, "normal", False) == 20.0

def test_cero_minutos(sistema):
    assert sistema.calcular_total(0, "normal", False) == 0.0

def test_de_60_minutos(sistema):
    assert sistema.calcular_total(60, "normal", False) == 20.0

def test_mas_de_60_minutos(sistema):
    assert sistema.calcular_total(61, "normal", False) == 35.0

def test_de_120_minutos(sistema):
    assert sistema.calcular_total(120, "normal", False) == 35.0

def test_mas_de_120_minutos(sistema):
    assert sistema.calcular_total(121, "normal", False) == 50.0

 #Prueba con pytest.approx
def test_descuento_con_decimales(sistema):
    #Tarifa base $35.0 con 10% de descuento = $32.50
    resultado = sistema.calcular_total(61, "frecuente", False)
    assert resultado == pytest.approx(32.5)


#Prueba con pytest.raises
def test_minutos_negativos(sistema):
    with pytest.raises(ValueError):
        sistema.calcular_total(-50, "normal", False)

def test_cliente_no_valido(sistema):
    with pytest.raises(ValueError):
        sistema.calcular_total(50, "cliente", False)



#Prueba con pytest.parametrize
@pytest.mark.parametrize(
    "minutos, tipo_cliente, boleto_perdido, esperado",
    [
        (10, "normal", False, 0.0),
        (40, "normal", False, 20.0),
        (45, "frecuente", False, 18.0),
        (180, "frecuente", False, 44.0),
    ],
)
def test_ConParametrize(sistema, minutos, tipo_cliente, boleto_perdido, esperado):
    assert sistema.calcular_total(minutos, tipo_cliente, boleto_perdido) == esperado


 #INTERACCIÓN ENTRE REGLAS
def test_boleto_perdido_frecuente(sistema):
    assert sistema.calcular_total(10, "frecuente", True) == 300.0

def test_boleto_perdido_normal(sistema):
    assert sistema.calcular_total(120, "normal", True) == 300.0
    

# TODO:
# 1. Agregue casos normales.
# 2. Agregue casos frontera.
# 3. Agregue entradas inválidas con pytest.raises.
# 4. Agregue casos parametrizados con @pytest.mark.parametrize.
# 5. Use pytest.approx cuando el resultado esperado tenga decimales.
# 6. Pruebe interacciones entre reglas.

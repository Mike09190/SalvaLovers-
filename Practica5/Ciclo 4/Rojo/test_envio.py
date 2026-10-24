import pytest
from envio import calcular_envio

def test_envio_estandar():
    assert calcular_envio(3,"estandar") == 50

def test_envio_mayor_50():
    assert calcular_envio(7,"estandar") == 70

def test_envio_express():
    assert calcular_envio(5,"express") == 100

def test_envio_express_mayor_50():
    assert calcular_envio(7,"express") == 140

def test_envio_peso_0():
    with pytest.raises(ValueError):
        calcular_envio(0, "estandar")

def test_envio_express_menor_0():
    with pytest.raises(ValueError):
        calcular_envio(-2, "express")
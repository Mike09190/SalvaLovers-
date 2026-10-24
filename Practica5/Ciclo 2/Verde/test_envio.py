from envio import calcular_envio

def test_envio_estandar():
    assert calcular_envio(3,"estandar") == 50

def test_envio_mayor_50():
    assert calcular_envio(7,"estandar") == 70
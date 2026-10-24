def calcular_envio(peso,tipo):
    if peso > 5:
        return 50 + (peso-5)*10
    else:
        return 50
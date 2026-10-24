def calcular_envio(peso,tipo):
    envio = 0
    if peso > 5:
        costo = 50 + (peso-5)*10
    else:
        costo = 50

    if tipo == "express":
        return 2*costo
    else:
        return costo
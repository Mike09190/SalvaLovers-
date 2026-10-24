def calcular_envio(peso,tipo):
    
    if peso <= 0:
        raise ValueError("El peso debe ser mayor que cero")

    if tipo not in ["estandar", "express"]:
        raise ValueError("Tipo de envio invalido")
        
    envio = 0
    if peso > 5:
        costo = 50 + (peso-5)*10
    else:
        costo = 50

    if tipo == "express":
        return 2*costo
    else:
        return costo
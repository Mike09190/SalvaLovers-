# SalvaLovers 🐀🧀

Repositorio en sucio de **Modelado y Programación**.

---

## Reglas para no romper el repo

Como todos vamos a estar metiendo mano directo en la rama `main`, hay que llevar cierto orden para no encimar cambios ni borrarle el trabajo a los demás:

1. **Hagan `pull` antes de empezar:** Siempre que abran la terminal para ponerse a programar, bájense lo último que hayan subido los demás.
2. **Hagan `pull` antes de subir:** No hagan `push` a lo loco sin haber hecho `pull` primero, si no Git les va a rebotar los cambios.
3. **No hacerle caso a los comentarios del Furroide a la hora de hacer su programa porque seguro está mal. En caso de que te ataque lanzarle queso

---

## Pasos para subir tu código desde la terminal

Cada vez que vayan a subir sus avances, sigan este orden de comandos directo en la terminal:

```bash
# 1. Guardar tus cambios locales
git add .
git commit -m "Explicación rápida de lo que hiciste"

# 2. Bajar lo que subieron los demás (IMPORTANTISIMO)
git pull origin main

# 3. Subir tu código a GitHub
git push origin main

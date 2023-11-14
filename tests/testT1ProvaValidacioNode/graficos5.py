import json
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import rcParams
from datetime import datetime

# Agafem les dades dels productes x comanda
with open('log.json', 'r') as file:
    dataComandes = json.load(file)


# Crear un diccionario para contabilizar els cops que s'ha demanat cada producte
mes = str(datetime.now().month)
if (len(mes) != 2):
    mes = "0" + mes
dies = {}
for comanda in dataComandes:
    if(comanda['FechaPedido'][5:7] == mes):
        dies[comanda['FechaPedido'][0:10]] = 0

for comanda in dataComandes:
    if(comanda['FechaPedido'][5:7] == mes):
        dies[comanda['FechaPedido'][0:10]] = dies[comanda['FechaPedido'][0:10]] + float(comanda['Total'])

nomDies = list(dies.keys())
diners = list(dies.values())

fig, ax = plt.subplots()
ax.plot(nomDies, diners)

ax.set(xlabel='Dies', ylabel='Diners (€)',
       title='Ingressos Mensuals')
ax.grid()
plt.xticks(rotation=90)
plt.tight_layout()
fig.savefig("./estadisticas_mensuales.jpeg")

import json
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib import rcParams
from collections import Counter
data = {}
with open('./data/1.json', 'r') as file:
    data = json.load(file)
    data = data["data"]
with open('./data/2.json', 'r') as file:
    data = data + json.load(file)["data"]
with open('./data/3.json', 'r') as file:
    data = data + json.load(file)["data"]
with open('./data/4.json', 'r') as file:
    data = data + json.load(file)["data"]
with open('./data/5.json', 'r') as file:
    data = data + json.load(file)["data"]
with open('./data/6.json', 'r') as file:
    data = data + json.load(file)["data"]
with open('./data/7.json', 'r') as file:
    data = data + json.load(file)["data"]


task_time = {}
user_time = {}
for algo in data:
    task_time[algo["task"]] = 0
    user_time[algo["user"]] = 0

for algo in data:
    task_time[algo["task"]] = task_time[algo["task"]] + algo["time_spent"]
    user_time[algo["user"]] = user_time[algo["user"]]  + algo["time_spent"]

tasques = list(task_time.keys())
temps = list(task_time.values())

users = list(user_time.keys())
tempsu = list(user_time.values())

fig, ax = plt.subplots()
ax.bar(tasques, temps)

ax.set(xlabel='Tasques', ylabel='Temps',
       title='Temps x tasca global')

fig, algo = plt.subplots()
algo.plot(users,tempsu)
algo.set(xlabel='User', ylabel='Temps',
       title='Temps x Usuari global')
plt.xticks(rotation=90)
plt.tight_layout()
plt.show()


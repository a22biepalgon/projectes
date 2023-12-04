import express from 'express';
import cors from 'cors';
import { createServer } from 'node:http';
import { Server } from 'socket.io';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
//Permetem que el servidor pugui rebre peticions de qualsevol origen
app.use(cors());

//Creem el servidor de Socket.io especificant que pot accedir qualsevol client
const server = createServer(app);
const io = new Server(server, {
  cors: {
    origin: 'http://localhost:3000',
    methods: ['GET', 'POST'],
    credentials: true
  }
});

app.get("/votacions", (req, res) => {
  fs.readFile('votos.json', 'utf8', (err, data) => {
    if (err) throw err;

    res.send(JSON.parse(data));
  });
})

app.get("/", (req, res) => {
  res.sendFile(__dirname + "/dist/index.html");
});
io.on('connection', (socket) => {
  socket.on('votacio', (msg) => {
    // Llegim l'arxiu de votacions
    console.log("arriba una votacio")
    console.log(msg)
    fs.readFile('votos.json', 'utf8', (err, data) => {
      if (err) throw err;

      // Parsejem el JSON
      const votos = JSON.parse(data);
      console.log(votos)
      // Actualistem el rempte de vots
      votos[msg]++;

      // Guardem el JSON actualitzat
      fs.writeFile('votos.json', JSON.stringify(votos), (err) => {
        if (err) throw err;

        // Emitim el JSON actualitzat
        io.emit('actualizacioVotacions', votos);
        console.log("S'ha emes una actualitzacio")
        console.log(votos)
      });
    });
  });
});

//recordeu de canviar el port del socket!!!
server.listen(3473, () => {
  console.log('Server running at http://localhost:3473');
});


import history from "connect-history-api-fallback"

const staticFieldMiddleware = express.static("dist");


app.use(staticFieldMiddleware);
app.use(
  history({
    disableDotRules: true,
    verbose: true,
  })
);
app.use(staticFieldMiddleware);

import express from 'express';
import cors from 'cors';
import { createServer } from 'node:http';
import { Server } from 'socket.io';
import fs from 'fs';

const app = express();
//Permetem que el servidor pugui rebre peticions de qualsevol origen
app.use(cors());

//Creem el servidor de Socket.io especificant que pot accedir qualsevol client
const server = createServer(app);
const io = new Server(server,{
  cors: {
    origin: 'http://localhost:3000', 
    methods: ['GET', 'POST'],
    credentials: true
  }
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
server.listen(3999, () => {
  console.log('Server running at http://localhost:3999');
});

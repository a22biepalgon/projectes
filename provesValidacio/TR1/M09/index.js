//Varios requires
const express = require("express");
const bodyParser = require("body-parser");
const http = require("node:http");
const cors = require("cors");
// const session = require("express-session");
// const cookieParser = require("cookie-parser");
let tasques = [
    {
        "titol": "Tasca1",
        "durada": 2,
        "id": 1
    },
    {
        "titol": "Tasca2",
        "durada": 4,
        "id": 2
    }
];

//Definir variables per la creació del servidor
const app = express();
const PORT = 3000;
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));



//Sockets
//Aixó es per sockets
const server = http.createServer(app);
const { Server } = require("socket.io");

const io = new Server(server, {
  cors: {
    origin: "http://localhost:3001",
    credentials: true,
  },
});



//Definir CORS per a sockets en localhost
app.use(cors({
    origin: "http://localhost:3001",
    credentials: true,
})
);

//Rutes per accedir i editar tasques 
app.get("/tasques", (req, res) => {
    let resposta = tasques
    res.send(resposta)
})

app.get("/tasca/:id", (req, res) => {
    resultat = null;
    tasques.forEach(tasca => {
        if (tasca.id == req.params.id) {
            resultat = tasca
        }
    });
    res.send(resultat)
})

app.post("/afegirTasca", (req, res) => {
    tascaNova = req.body;
    tasques.push(tascaNova)
    res.send("Afegit correctament")
})



//Accions socket


//Sockets
io.on("connection", (socket) => { //Definim la connexió del socket
    console.log("a user connected");
    io.emit("connectat", "connectat");

    socket.on("chat message", (msg) => {
        console.log(msg)
        io.emit("chat message", msg);
    });

    socket.on("disconnect", () => { //Codi per desconectar
        console.log("user disconnected");
    });

});

app.get("/python", (req,res)=>{
    var { spawn } = require("child_process");
    var proceso = spawn("python", ["./prova.py", "3"]);
    resultat = ""; 

    proceso.stdout.on("data", (data) => {
      resultat = data.toString();
      res.send(resultat);
    });

    //console.log(resultat)

    proceso.on("close", (code) => {
      if (code === 0) {
        //console.log("El script de Python se ha ejecutado correctamente.");
      } else {
        console.error(
          `El script de Python ha finalizado con código de salida ${code}.`
        );
      }

    }); 
    // Maneja los errores estándar de Python (stderr)
    proceso.stderr.on("data", (data) => {
      console.error(`Errores estándar de Python: ${data}`);
    });

    
})


app.get("/pythonM10", (req,res)=>{
    var { spawn } = require("child_process");
    var proceso = spawn("python", ["./prova.py", "3"]);
    resultat = ""; 

    proceso.stdout.on("data", (data) => {
      resultat = data.toString();
      res.send(resultat);
    });

    //console.log(resultat)

    proceso.on("close", (code) => {
      if (code === 0) {
        //console.log("El script de Python se ha ejecutado correctamente.");
      } else {
        console.error(
          `El script de Python ha finalizado con código de salida ${code}.`
        );
      }

    }); 
    // Maneja los errores estándar de Python (stderr)
    proceso.stderr.on("data", (data) => {
      console.error(`Errores estándar de Python: ${data}`);
    });

    
})

//Enviar html de socket chat
app.get('/', (req, res) => {
    res.sendFile(__dirname + "/index.html");
})
//Encenem Servidor
server.listen(PORT, () => {
    console.log(`Listening at port ${PORT}`);
})

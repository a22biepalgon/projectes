//Varios requires
const express = require("express");
const bodyParser = require("body-parser");
const http = require("node:http");
const cors = require("cors");
const session = require("express-session");
const cookieParser = require("cookie-parser");

//Definir variables per la creació del servidor
const app = express();
const PORT = 3000;
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

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



//SESSION
app.use(session({
  secret: 'my-secret',  // a secret string used to sign the session ID cookie
  resave: false,  // don't save session if unmodified
  saveUninitialized: false  // don't create session until something stored
}))

var usuari = "biel"
var psw = "1234"
app.post("/user", (req, res) => {
  if (req.session.user) {
    //console.log("AAAAAAAAa")
    res.setHeader('Content-Type', 'text/html')
    res.write('<p>Hola: ' + req.session.user + '</p>')
    res.end();
  } else {
    if (req.body.username == usuari && req.body.password == psw) {
      req.session.user = req.body.username
      console.log("CORRECTE")
      res.send("RECARREGA")
    } else {
      res.send(req.body.username + ', aquest usuari es incorrecte, aqui no entres')
    }
    //console.log("REQ=>", req.body)
  }
})

app.get("/accedit", (req,res) =>{
  if(req.session.user){
    res.setHeader('Content-Type', 'text/html')
    res.write('<p>Has accedit: ' + req.session.user + '</p>')
    res.end();
  }else{
    res.send("GILIPOLLAS")
  }
})





//Sockets
io.on("connection", (socket) => { //Definim la connexió del socket
  //console.log("a user connected");
  io.emit("connectat", "connectat");

  socket.on("valor", () => {
    //Codi a executar quan es rep el socket

    //Per si es vol fer un emitx
    io.emit("comandaNova");
  });

  socket.on("disconnect", () => { //Codi per desconectar
    //console.log("user disconnected");
  });

});





//Rutes
// app.get("/", (req, res) => {
//     res.sendFile(__dirname + "./index.html");
//   });






//Python
app.get("/python", async (req, res) => {
  try {
    await selectPedidos(); //Funcio per guardar les dades al .json que accedira el python dsps
    await mostrarGraficaEstado(); //Funcio que executa el python
    res.sendFile(__dirname + "/grafico2.jpeg"); //Un cop s'ha executat el python enviem el fitxer que ha creat
  } catch (error) {
    console.error("Error al mostrar el gráfico de estados:", error);
    // Controlar l'error
    res.status(500).send("Error al mostrar el gráfico de estados");
  }
});
function selectPedidos() {
  return new Promise((resolve, reject) => {
    const sql = "SELECT * FROM Pedido";
    conn.query(sql, (err, result) => {
      if (err) {
        console.error("Error al cargar pregunta: ", err);
        cerrarServidor();
        reject(err);
      } else {
        const resultJson = JSON.stringify(result, null, 2);

        fs.writeFile("log.json", resultJson, (err) => {
          if (err) {
            console.error("error al escribir los resultados");
            reject(err);
          } else {
            //console.log("escrito con éxito");
            resolve();
          }
        });
      }
    });
  });
}

function mostrarGraficaEstado() {
  return new Promise((resolve, reject) => {
    let { spawn } = require("child_process");
    let proceso = spawn("python3", ["./grafico2.py"]);

    proceso.on("close", (code) => {
      if (code === 0) {
        //console.log("El script de Python se ha ejecutado correctamente.");
        resolve();
      } else {
        console.error(
          `El script de Python ha finalizado con código de salida ${code}.`
        );
        reject(
          `El script de Python ha finalizado con código de salida ${code}.`
        );
      }
    });
  });
}



//Definir Redirecció de rutes Vue Router
//Recordar posar al final de tot del index, després d'haver fet totes les rutes de node ja
let history = require("connect-history-api-fallback"); //require tontorron
const staticFieldMiddleware = express.static("public");//Carpeta a on hem posat els fitxers de /dist
app.use(staticFieldMiddleware);
app.use(
  history({
    disableDotRules: true,
    verbose: true,
  })
);
app.use(staticFieldMiddleware);





//Funció per executar servidor sense socket
// app.listen(PORT, () => {
//     console.log(`Listening at port ${PORT}`);
// })



app.get('/', (req, res) => {
  res.sendFile(__dirname + "/public/index.html");
})
//Funcions per executar servidor amb socket
server.listen(PORT, () => {
  console.log(`listening at http://localhost:${PORT}`);
});

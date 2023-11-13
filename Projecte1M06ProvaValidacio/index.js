const { Console } = require("console");
const express = require("express");

const app = express();
var bodyParser = require('body-parser')
var jsonParser = bodyParser.json()

app.listen(3000, () =>{
    console.log("Listening at port 3000")
})

app.get("/getLlibres", (req, res) =>{
    var sql = "SELECT * FROM LIBRO JOIN AUTOR WHERE AUTOR.codigo = LIBRO.codigo";
  conn.query(sql, (err, result) => {
    if (err) console.error(err);
    res.send(result);
  });
})

app.post("/insertLlibre",jsonParser, (req, res)=>{
    var sql = `INSERT INTO LIBRO VALUES (${req.body.codi},'${req.body.titol}',${req.body.isbn},'${req.body.editorial}', ${req.body.autor})`;
    conn.query(sql, (err, result) => {
        if (err) console.error(err);
        res.send(result);
      });
})
app.post("/insertAutor",jsonParser, (req, res)=>{
    var sql = `INSERT INTO AUTOR VALUES (${req.body.codi},'${req.body.nom}')`;
    conn.query(sql, (err, result) => {
        if (err) console.error(err);
        res.send(result);
      });
})


const mysql = require("mysql2");

var conn = mysql.createPool({
  host: "dam.inspedralbes.cat",
  user: "a22biepalgon_user_2",
  password: "Prova1234",
  database: "a22biepalgon_projecte1validacio",
  connectionLimit: 20,
  queueLimit: 5,
  waitForConnections: true,
});

conn.getConnection((err, connection) => {
    if (err) {
      console.error("ERROR=> " , err);
      process.exit(1)
    } else {
      console.log("Connected to database!");
    }
  });



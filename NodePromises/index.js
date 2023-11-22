
const mysql = require("mysql2");
const { setTimeout } = require("timers/promises");

var conn = mysql.createPool({
    host: "dam.inspedralbes.cat",
    user: "a22biepalgon_user_2",
    password: "Prova1234",
    database: "a22biepalgon_projecte1validacio",
    connectionLimit: 20,
    queueLimit: 5,
    waitForConnections: true,
});

const connection = new Promise ((resolve, reject) =>{
    conn.getConnection((err, connection) => {
    if (err) {
        reject(err);
        console.error("ERROR=> ", err);
        process.exit(1)
    } else {
        resolve(connection)
        console.log("Connected to database!");
    }
});
});


async function select() {
    var sql = "SELECT * FROM LIBRO JOIN AUTOR WHERE AUTOR.codigo = LIBRO.codigo";
    var sql2 = "SELECT * FROM AUTOR";
    var results = null;
    var results2 = null;
    db = await connection;
    const p = new Promise((resolve, reject) => {
        db.query(sql, (err, result) => {
            if (err){
                reject(err)
            }else{
                setTimeout(resolve(result), 3000);
                 }               
        })
    })
    const p2 = new Promise((resolve, reject) => {
        db.query(sql2, (err, result) => {
            if (err){
                reject(err)
            }else{
                setTimeout(     resolve(result) , 5000);
           }               
        })
    })
    const p3 = new Promise((resolve, reject) => {
        db.query(sql2, (err, result) => {
            if (err){
                reject(err)
            }else{
                setTimeout(resolve(result) ,5000);
                 }               
        })
    })
    const p4 = new Promise((resolve, reject) => {
        db.query(sql2, (err, result) => {
            if (err){
                reject(err)
            }else{
                setTimeout(resolve(result) , 5000);
                }               
        })
    })
    const p5 = new Promise((resolve, reject) => {
        db.query(sql2, (err, result) => {
            if (err){
                reject(err)
            }else{
                setTimeout(resolve(result) , 5000);
                }               
        })
    })
    await p
    await p2
    await p3
    await p4
    await p5
    console.log("done")
}


select();

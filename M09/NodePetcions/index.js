
const mysql = require("mysql2");

const conn = mysql.createPool({
    host: "dam.inspedralbes.cat",
    user: "a22biepalgon_user_2",
    password: "Prova1234",
    database: "a22biepalgon_projecte1validacio",
    connectionLimit: 20,
    queueLimit: 5,
    waitForConnections: true,
    keepAliveInitialDelay: 10000,
    enableKeepAlive: true
});


const connection = new Promise((resolve, reject) => {
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

async function exec(){
    var sql2 = "SELECT * FROM AUTOR";
    let db = await connection;
    console.log("1")
    db.query(sql2, (err, result) => {
        if (err) {
            reject(err)
        } else {
            console.log(result)
        }
    })
    console.log("2")
}


exec()
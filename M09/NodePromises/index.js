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




async function select() {
    let db = await connection;

    var sql = "SELECT * FROM LIBRO JOIN AUTOR WHERE AUTOR.codigo = LIBRO.codigo";
    var sql2 = "SELECT * FROM AUTOR";

    

    async function cridarp() {
        return new Promise((resolve, reject) => {
            db.query(sql2, (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    setTimeout(() => { console.log("Resolving p"); resolve(result) }, 5000)
                }
            })
        }
        );
    }

    async function cridarp2() {
        return new Promise((resolve, reject) => {
            db.query(sql, (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    setTimeout(() => { console.log("Resolving p2"); resolve(result) }, 5000)
                }
            })

        }
        );
    }

    async function cridarp3() {
        return new Promise((resolve, reject) => {
            db.query(sql, (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    setTimeout(() => { console.log("Resolving p3"); resolve(result) }, 5000)
                }
            })

        }
        );
    }

    async function cridarp4() {
        return new Promise((resolve, reject) => {
            db.query(sql2, (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    setTimeout(() => { console.log("Resolving p4"); resolve(result) }, 4000)
                }
            })

        }
        )
    }
    async function cridarp5() {
        return new Promise((resolve, reject) => {

            db.query(sql2, (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    setTimeout(() => { console.log("Resolving p5"); resolve(result) }, 8000)
                }
            })

        }
        )
    }

    let resultat = await cridarp()
    console.log(resultat);
    let resultat2 = await cridarp2()
    console.log(resultat2);
    let resultat3 = await cridarp3()
    console.log(resultat3);
    let resultat4 = await cridarp4()
    console.log(resultat4);
    let resultat5 = await cridarp5()
    console.log(resultat5);
    console.log("done")

}


select();
// async function prova() {
//     db = await connection;
//     db.query("SELECT * FROM AUTOR", (err, result) => {
//         if (err) {
//             reject(err)
//         } else {
//             console.log("p5")
//             console.log(result)
//         }
//     })
// }
// prova()
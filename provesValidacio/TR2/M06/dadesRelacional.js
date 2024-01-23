
const mysql = require("mysql2");

var conn = mysql.createConnection({
    host: "dam.inspedralbes.cat",
    user: "a22biepalgon_user5",
    password: "Bielpg04",
    database: "a22biepalgon_validacioTr2",
});

module.exports = {
    connect: function () {
        return new Promise((resolve, reject) => {
            conn.connect((err) => {
                if (err) {
                    reject(err);
                } else {
                    resolve();
                }
            });
        })

    },

    inserirPregunta: function (pregunta) {
        return new Promise((resolve, reject) => {
            conn.query('INSERT INTO preguntes (pregunta) VALUES (?)', [pregunta], (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    resolve(result.insertId)
                }
            })
        })
    },
    inserirResposta: function (resposta, idPregunta) {
        return new Promise((resolve, reject) => {
            conn.query('INSERT INTO respostes (resposta1, idPregunta) VALUES (?, ?)', [resposta, idPregunta], (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    resolve()
                }
            })
        })
    },
    consultPreguntes: function () {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM preguntes', (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    resolve(result);
                }
            })
        })
    },
    consultRespostes: function () {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM respostes', (err, result) => {
                if (err) {
                    reject(err)
                } else {
                    resolve(result);
                }
            })
        });
    },

    close: function () {
        return new Promise((resolve, reject) => {
            conn.end((err) => {
                if (err) {
                    reject(err)
                } else { resolve() }
            })
        })
    }
}





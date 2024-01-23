const { MongoClient } = require('mongodb');

// Connection URL and database name
const url = 'mongodb+srv://a22biepalgon:Grupo1t2@mathbattles.ncbsbss.mongodb.net/';
const dbName = 'provaValidacio';
let collection;
let db;
let client;



module.exports = {
    connect: function () {
        return new Promise((resolve, reject) => {
            // Create a new MongoClient
            client = new MongoClient(url);
            client.connect();
            resolve();
        });
    },



    inserirPregunta: function (pregunta) {
        return new Promise((resolve, reject) => {
            db = client.db(dbName);
            collection = db.collection('prova');
            collection.insertOne(pregunta).catch(err => {
                reject(err)
            }).finally(() => {
                resolve();
            });
        })
    },

    consultPreguntes: function () {
        return new Promise((resolve, reject) => {
            var documents = null
            try {
                documents = collection.find().toArray();
            } catch (e) {
                reject(e);
            }
            resolve(documents);

        })
    },
    close: function () {
        return new Promise((resolve, reject) => {
            client.close((err) => {
                if (err) {
                    reject(err);
                } else {
                    resolve();
                }
            });
        })
    }
}





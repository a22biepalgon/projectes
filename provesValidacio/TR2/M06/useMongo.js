const { MongoClient } = require('mongodb');

// Connection URL and database name
const url = 'mongodb+srv://a22biepalgon:Grupo1t2@mathbattles.ncbsbss.mongodb.net/';
const dbName = 'provaValidacio';

// Create a new MongoClient
const client = new MongoClient(url);

async function connect() {
    await client.connect();
}

async function close() {
    await client.close();
}

// Connect to the MongoDB server
connect()
    .then(async () => {
        try {
            console.log('Connected to MongoDB');

            // Now you can perform operations on the database
            const db = client.db(dbName);
            const collection = db.collection('prova');

            //INSERT
            await collection.insertOne({
                nom: "Biel",
                cognoms: "Palomar Gonzàlez",
                edat: "19",
                dni: "45153541X"
            }).catch(err => {
                console.err("Error inesrint: ", err)
            });
            console.log("Document inserted");


            //SELECT
            const documents = await collection.find().toArray();
            console.log("Found documents:", documents);


            //UPDATE
            await collection.updateOne({
                nom: "Biel"
            }, {
                $set: {
                    cognoms: "Palomo"
                }
            });
            console.log("Document updated");

            //DELETE
            await collection.deleteMany({ nom: "Biel" });
            console.log("Document deleted");


        } finally {
            // Close the connection when done
            await close();
            console.log("Connection closed");
        }
    })
    .catch(err => {
        console.error('Error connecting to MongoDB:', err);
    });

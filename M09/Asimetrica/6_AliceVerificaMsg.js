const fs = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();

try {
    const publicKey = fs.readFileSync("BobClauPublica.pub", 'utf-8');
    key.importKey(publicKey, 'public');

    const msg = fs.readFileSync("5_BobMsgSenseSignar.txt", 'utf-8');
    const signature = fs.readFileSync("5_BobSignatura.sig", 'base64');

    const isSignat = key.verify(msg, signature, 'utf-8', 'base64');
    if (isSignat) {
        console.log("Si que està signat");
        console.log("Msg:" + msg);
    } else {
        console.log("No verificat");
    }

} catch (error) {
    console.error('Error:', error.message);
}
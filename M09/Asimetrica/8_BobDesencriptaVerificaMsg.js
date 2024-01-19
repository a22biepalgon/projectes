const fs = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();


const json = fs.readFileSync("7_EncriptatSignat.txt", 'utf-8');
const bobPrivateKey = fs.readFileSync("BobClauPrivada.pem", 'utf-8').trim();
key.importKey(bobPrivateKey, 'private');
var decrypt = key.decrypt(json, 'utf-8');

const alicePublicKey = fs.readFileSync("AliceClauPublica.pub", 'utf-8');
key.importKey(alicePublicKey, 'public');

var data = JSON.parse(decrypt);
const verificat = key.verify(data.msg, data.sign, 'utf-8', 'base64');
if (verificat) {
    console.log("VERIFICAT");
    console.log("MSG: " + data.msg);
}
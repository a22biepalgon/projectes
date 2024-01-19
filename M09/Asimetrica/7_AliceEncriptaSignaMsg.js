const fs = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();
const msg = fs.readFileSync("7_AliceMsgSenseRes.txt", 'utf-8');

const bobPublicKey = fs.readFileSync("BobClauPublica.pub", 'utf-8');
const alicePrivateKey = fs.readFileSync("AliceClauPrivada.pem", 'utf-8');
key.importKey(alicePrivateKey, 'private');


const signatura = key.sign(msg, 'base64');
const json = {msg: msg, sign: signatura}
key.importKey(bobPublicKey, 'public');

const encriptat = key.encrypt(JSON.stringify(json), 'base64');
console.log(encriptat)

fs.writeFileSync("7_EncriptatSignat.txt", encriptat, 'utf-8');
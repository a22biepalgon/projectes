const fs  = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();
key.generateKeyPair(2048);
const publicKey = key.exportKey('public');
const privateKey = key.exportKey('private');

const fitxerSortida1 = "AliceClauPublica.pub";
const fitxerSortida2 = "AliceClauPrivada.pem";

fs.writeFileSync(fitxerSortida1, publicKey);
fs.writeFileSync(fitxerSortida2, privateKey);


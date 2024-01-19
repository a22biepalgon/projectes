const fs = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();

const msg = fs.readFileSync("5_BobMsgSenseSignar.txt", 'utf-8');
const privateKey = fs.readFileSync("BobClauPrivada.pem", 'utf-8');
key.importKey(privateKey, 'private');
const signat = key.sign(msg, 'base64');

fs.writeFileSync("5_BobSignatura.sig", signat, 'base64');
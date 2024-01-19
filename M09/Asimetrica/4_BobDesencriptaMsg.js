const fs = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();

const privateKey = fs.readFileSync("BobClauPrivada.pem");
const msg = fs.readFileSync("3_AliceMsgEncriptat.txt", 'utf-8');
key.importKey(privateKey, 'private');
const decrypted = key.decrypt(msg, 'utf-8');
fs.writeFileSync("4_MissatgeDesencriptat.txt", decrypted, 'utf-8');
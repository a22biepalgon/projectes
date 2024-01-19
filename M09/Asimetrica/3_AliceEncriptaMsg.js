const fs = require('fs');
const NodeRSA = require('node-rsa');

const key = new NodeRSA();


const msg = fs.readFileSync("3_AliceMsg.txt");
const publicKey = fs.readFileSync("BobClauPublica.pub");  

key.importKey(publicKey, 'public');
const encryptedMsg = key.encrypt(msg, 'base64');

fs.writeFileSync("3_AliceMsgEncriptat.txt", encryptedMsg, 'utf-8');
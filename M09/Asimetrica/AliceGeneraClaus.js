const fs  = require('fs');
const JSEncrypt = require('jsencrypt');
const crypt = new JSEncrypt({ default_key_size: 2048 });
const publicKey = crypt.getPublicKey();
const privateKey = crypt.getPrivateKey();

const fitxerSortida1 = "AliceClauPublica";
const fitxerSortida2 = "AliceClauPrivada";

fs.writeFileSync(fitxerSortida1, publicKey);
fs.writeFileSync(fitxerSortida2, privateKey);


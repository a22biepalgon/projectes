const fs = require('fs');
const CryptoJS = require('crypto-js')

const fitxerOutput = "result.txt";
const fitxerInput = "data.encrypted";
const password = "1234";

var data = fs.readFileSync(fitxerInput, 'utf-8');
var decrypted = CryptoJS.AES.decrypt(data, password).toString(CryptoJS.enc.Utf8);

fs.writeFileSync(fitxerOutput, decrypted);
console.log("Done!");
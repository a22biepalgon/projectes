const fs = require('fs');
const CryptoJS = require('crypto-js')


const fitxerInput = "data.txt";
const fitxerOutput = "data.encrypted";
const password = "1234";

var data = fs.readFileSync(fitxerInput, 'utf-8');

var encrypted = CryptoJS.AES.encrypt(data, password);

fs.writeFileSync(fitxerOutput, encrypted.toString());
console.log("Done!")
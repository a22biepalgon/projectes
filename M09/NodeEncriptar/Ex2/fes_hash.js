const fs = require('fs');
const CryptoJS = require('crypto-js')


const fitxerInput = "password.txt";
const fitxerOutput = "hash.txt";

var data = fs.readFileSync(fitxerInput, 'utf-8');
var hash = CryptoJS.MD5(data);

fs.writeFileSync(fitxerOutput, hash.toString());
console.log("Done!")
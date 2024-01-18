const fs = require('fs');
const CryptoJS = require('crypto-js')


const fitxerInput = "password.txt";
const fitxerInput2 = "hash.txt";

var data = fs.readFileSync(fitxerInput, 'utf-8');
var hash2 = fs.readFileSync(fitxerInput2, 'utf-8');
var hash1 = CryptoJS.MD5(data);

if(hash1.toString() == hash2.toString()){
    console.log("Contrasenya Correcte!");
}else{
    console.log("Incorrecte");
}


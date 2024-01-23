const bd = require('./mongoDBOperacions.js');

async function init() {
    try {
        console.log("PROGRAMA DE PROVA DE VALIDACIÓ");

        console.log("CONNEXIO");
        try {
            var connectar = await bd.connect();
        } catch (err) {
            console.error(err);
        } finally {
            console.log("OK");
        }


        console.log("");
        console.log("INSERT DE PREGUNTA");
        var inserirPregunta = null;
        try {
            inserirPregunta = await bd.inserirPregunta({
                pregunta: "Com em dic?",
                resposta1: "Biel",
                resposta2: "Bielsito",
                resposta3: "Bili",
                resposta4: "Totes les anteriors",
                respostaCorrecte: 4
            });
        } catch (err) {
            console.error(err);
        } finally {
            console.log("OK");
        }
        
        console.log("");
        console.log("SELECT DE PREGUNTES")
        try {
            var veurePreguntes = await bd.consultPreguntes();
            console.log(veurePreguntes);
        } catch (err) {
            console.error(err);
        }
       
    } finally {
        console.log("TANCAR CONNEXIO");
        await bd.close();
        console.log("TANCAT")
    }
}

init();
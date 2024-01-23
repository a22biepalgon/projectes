const bd = require('./dadesRelacional.js');

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
            inserirPregunta = await bd.inserirPregunta("Que és mes gran?");
        } catch (err) {
            console.error(err);
        } finally {
            console.log("OK");
        }


        console.log("");
        console.log("INSERT DE RESPOSTES");
        try {
            var inserirResposta1 = await bd.inserirResposta("Un ocell", inserirPregunta);
            var inserirResposta2 = await bd.inserirResposta("Un vaixell", inserirPregunta);
            var inserirResposta3 = await bd.inserirResposta("Una casa", inserirPregunta);
            var inserirResposta4 = await bd.inserirResposta("Una flor", inserirPregunta);
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
        console.log("");
        console.log("SELECT DE RESPOSTES");


        try {
            var veureRespostes = await bd.consultRespostes();
            console.log(veureRespostes);
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
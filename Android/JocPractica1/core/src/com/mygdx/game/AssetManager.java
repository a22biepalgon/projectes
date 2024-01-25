package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {


    private static Texture fonsPantalla;  // Exemple de textura del jugador carregada
    private static Texture playerImage;

    private static Texture fonsJoc;

    private static Texture ballImage;

    private static Texture coneImage;

    private static Sound pilotaSo;

    private static Sound gameOver;


    public static void load() {
        // Carregar els assets, com ara la textura del jugador
        fonsPantalla = new Texture("fons.jpg") ;
        playerImage =  new Texture("player.png") ;
        fonsJoc = new Texture("fonsJoc.jpg");
        ballImage = new Texture("ball.png");
        coneImage = new Texture("cono.png");
        pilotaSo = Gdx.audio.newSound(Gdx.files.internal("pilota.mp3"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("gameOver.mp3"));
    }

    public static void dispose() {
        // Eliminar els assets quan ja no es necessitin
        fonsPantalla = null;
        playerImage = null;
        fonsJoc = null;
        ballImage = null;
        coneImage = null;
        pilotaSo = null;
        gameOver = null;
    }


    //GETTERS
    public static Texture getFonsPantalla() {
        return fonsPantalla;
    }

    public static Texture getPlayerImage() {
        return playerImage;
    }

    public static Texture getFonsJoc() {
        return fonsJoc;
    }

    public static Texture getBallImage() {
        return ballImage;
    }

    public static Texture getConeImage() {
        return coneImage;
    }

    public static Sound getPilotaSo() {
        return pilotaSo;
    }

    public static Sound getGameOver() {
        return gameOver;
    }
}

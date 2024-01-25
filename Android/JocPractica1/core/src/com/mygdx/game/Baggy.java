package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Baggy extends com.badlogic.gdx.Game {
    private SpriteBatch batch;
    private BitmapFont bitmap;

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getBitmap() {
        return bitmap;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        bitmap = new BitmapFont();
        setScreen(new MainMenuScreen(this));
    }
}

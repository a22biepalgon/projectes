package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen implements Screen {
    private int punts;
    private Baggy game;
    final BitmapFont font;
    final SpriteBatch batch;
    OrthographicCamera camera;

    public EndScreen(Baggy game, int punts) {
        this.punts = punts;
        this.game = game;
        this.font = game.getBitmap();
        this.batch = game.getBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 480);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

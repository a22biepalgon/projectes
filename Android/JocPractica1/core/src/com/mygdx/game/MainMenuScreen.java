package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final Baggy game;
    final BitmapFont font;
    final SpriteBatch batch;
    OrthographicCamera camera;
    public MainMenuScreen(Baggy game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getBitmap();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        batch.begin();
        //Definim el fons de pantalla
        Texture backgroundTexture = new Texture("background.jpg"); // Replace with your image file
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(game.getBatch(), "Welcome to Drop!!! ", 100, 150);
        font.draw(game.getBatch(), "Tap anywhere to begin!", 100, 100);
        batch.end();

        //if (Gdx.input.isTouched()) {
          //  game.setScreen(new MyGdxGame(this.game));
           // dispose();
        //}
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

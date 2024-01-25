package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {
    private int punts;
    private Baggy game;
    final BitmapFont font;
    final SpriteBatch batch;
    OrthographicCamera camera;

    private Texture fons;
    private float elapsedTime = 0f; // Variable per seguir el temps transcorregut
    public EndScreen(Baggy game, int punts) {
        this.punts = punts;
        this.game = game;
        this.font = game.getBitmap();
        this.batch = game.getBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 480);

        AssetManager.load();

        fons = AssetManager.getFonsJoc();
        font.getData().setScale(2.0f); // Pots ajustar el factor d'escala com vulguis
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
        String gameOverText = "HAS PERDUT!";
        batch.draw(fons, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        GlyphLayout layout = new GlyphLayout(font, gameOverText);
        float textWidth = layout.width;
        float textHeight = layout.height;

        // Coordenades per dibuixar el text centrada a la pantalla
        float x = (Gdx.graphics.getWidth() - textWidth) / 2 ;
        float y = (Gdx.graphics.getHeight() + textHeight) / 2;

        font.draw(game.getBatch(), gameOverText, 50, y + 50);
        font.draw(game.getBatch(), "La teva puntuació ha sigut de: " + this.punts, 50, y );
        font.draw(game.getBatch(), "Fes clic a qualsevol lloc per tornar a jugar", 50, y - 50);


        // Incrementa el temps transcorregut
        elapsedTime += delta;

        // Espera 1 segon abans de permetre la detecció de clics
        if (elapsedTime >= 3.0f && Gdx.input.isTouched()) {
            game.setScreen(new MyGameScreen(this.game));
            dispose();
        }

        batch.end();

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
        AssetManager.dispose();
    }
}

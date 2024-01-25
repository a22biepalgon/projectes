package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Iterator;

public class MyGameScreen implements Screen {

    final Baggy game;
    final BitmapFont font;
    final SpriteBatch batch;

    private OrthographicCamera camera;
    private Texture ballImage;
    private Texture background;
    private Texture playerImage;
    private Texture coneImage;
    private Sound ballSound;
    private Sound coneSound;
    private Music gameMusic;
    private Rectangle player;
    private Array<Rectangle> balls;
    private Array<Rectangle> cones;
    private long lastItemTime;


    private int punts;
    public MyGameScreen(Baggy game){
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getBitmap();

        //Definim la camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 480);

        //Carreguem els assets
        AssetManager.load();
        background = AssetManager.getFonsJoc();
        playerImage = AssetManager.getPlayerImage();
        ballImage = AssetManager.getBallImage();
        coneImage = AssetManager.getConeImage();
        balls = new Array<Rectangle>();
        spawnItem();

        cones = new Array<Rectangle>();
        spawnItemCone();


        //Definim el quadrat del player
        player = new Rectangle();
        player.x = 0;
        player.y = 20;
        player.width = 80;
        player.height = 100;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        batch.begin();

        //Fem get del fons i el posem
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Dibuixem el player
        batch.draw(playerImage, player.x, player.y, 100, 120);

        //Dibuixem les pilotes
        for(Rectangle ball: balls) {
            batch.draw(ballImage, ball.x, ball.y, 64, 64);
        }
        for(Rectangle cone: cones) {
            batch.draw(coneImage, cone.x, cone.y, 64, 64);
        }
        batch.end();


        //Afegim els controls del jugador
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.y = touchPos.y - 64 / 2;
        }

        //Afegim els controls amb les tecles
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.y -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.y += 200 * Gdx.graphics.getDeltaTime();

        //Afegim els topes del cubell per a que no se surti de la pantalla
        if(player.y < 0) player.y = 0;
        if(player.y > 480 - 120) player.y = 480 - 120;

        //Anem creant items
        if(TimeUtils.nanoTime() - lastItemTime > 1000000000){ spawnItem(); spawnItemCone(); };
        //Fem que les pilotes es vagin movent a una velictat constant
        for (Iterator<Rectangle> iter = balls.iterator(); iter.hasNext(); ) {
            Rectangle ball = iter.next();
            ball.x -= 200 * Gdx.graphics.getDeltaTime();
            if(ball.x + 200 < 0) iter.remove();
            //Definim la acció a fer si es toquen la gota i el cubell
            if(ball.overlaps(player)) {
                punts += 100;
                //ballSound.play();
                iter.remove();
            }

        }
        for (Iterator<Rectangle> iter = cones.iterator(); iter.hasNext(); ) {
            Rectangle cone = iter.next();
            cone.x -= 200 * Gdx.graphics.getDeltaTime();
            if(cone.x + 200 < 0) iter.remove();
            //Definim la acció a fer si es toquen la gota i el cubell
            if(cone.overlaps(player)) {
                //coneSound.play();
                iter.remove();
                game.setScreen(new EndScreen(game, punts));
            }

        }

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

    public void spawnItem(){
        Rectangle ball = new Rectangle();
        ball.y = MathUtils.random(0, 480-64);
        ball.x = 800;
        ball.width = 64;
        ball.height = 64;
        balls.add(ball);
        lastItemTime = TimeUtils.nanoTime();
    }
    public void spawnItemCone(){
        Rectangle cone = new Rectangle();
        cone.y = MathUtils.random(0, 480-64);
        cone.x = 800;
        cone.width = 64;
        cone.height = 64;
        cones.add(cone);
        lastItemTime = TimeUtils.nanoTime();
    }
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyGdxGame implements Screen {
	final Drop game;

	final SpriteBatch batch;
	final BitmapFont font;
	private OrthographicCamera camera;
	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;
	int dropsGathered;
	public MyGdxGame(Drop game) {
		this.game = game;
		this.batch = this.game.getBatch();
		this.font = this.game.getBitmap();


		// load the images for the droplet and the bucket, 64x64 pixels each
		//Definim les textures de la gota i bucket
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("waterDroplet.mp3"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		//Creem la camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// ... more to come ...	}

		//Definim el quadrat del cubo
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		//definim les gotes
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
//Definim el color de fons
		ScreenUtils.clear(0, 0, 0.02f, 1);

		//Fem el update de la camera
		camera.update();

		//Fem el render del bucket
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//posem el contador
		font.draw(batch, "Drops Collected: " + dropsGathered, 0, 480);
		//Dibuixem el cubell
		batch.draw(bucketImage, bucket.x, bucket.y);
		//Dibuixem les gotes
		for(Rectangle raindrop: raindrops) {
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		batch.end();

		//Afegim els controls del cubell
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}

		//Afegim els controls amb les tecles
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

		//Afegim els topes del cubell per a que no se surti de la pantalla
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;

		//Anem creant gotes
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		//Fem que les gotes es vagin movent a una velictat constant
		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) iter.remove();
			//Definim la acció a fer si es toquen la pilota i el jugador
			if(raindrop.overlaps(bucket)) {
				dropsGathered++;
				dropSound.play();
				iter.remove();
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

	//Netejem el codi
	@Override
	public void dispose () {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}



	//Funcio per a crear les gotes random
	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

}

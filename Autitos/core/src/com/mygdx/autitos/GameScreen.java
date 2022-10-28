package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final Autitos game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Ferrari ferrari;
	private Carrera carrera;
	private Texture carretera;

	   
	//boolean activo = true;

	public GameScreen(final Autitos game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  ferrari = new Ferrari(new Texture(Gdx.files.internal("Autol2.png")),hurtSound);
         
	      // load the drop sound effect and the rain background "music" 
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("carCrash.wav"));
        
	     Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
         carrera = new Carrera(dropSound, rainMusic);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      ferrari.crear();
	      
	      // creacion de la lluvia
	      carrera.crear();
		}
		
	

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		carretera = new Texture(Gdx.files.internal("Carretera.png"));
		batch.draw(carretera, 0, 0); 
		
		//dibujar textos
		font.draw(batch, "Gotas totales: " + ferrari.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + ferrari.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!ferrari.estaHerido()) {
			// movimiento del tarro desde teclado
	        ferrari.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!carrera.actualizarMovimiento(ferrari)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<ferrari.getPuntos())
	    		  game.setHigherScore(ferrari.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		ferrari.dibujar(batch);
		carrera.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  carrera.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		carrera.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      ferrari.destruir();
      carrera.destruir();

	}

}

package com.mygdx.autitos;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;



public class Autitos extends ApplicationAdapter {
       private OrthographicCamera camera;
	   private SpriteBatch batch;	   
	   private BitmapFont font;
	   private Ferrari ferrari;
	   private Carrera carrera;
	   private Texture carretera;
	@Override
	public void create () {
		 font = new BitmapFont(); // use libGDX's default Arial font
		 
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("CarCrash.wav"));
		  ferrari = new Ferrari(new Texture(Gdx.files.internal("Autol2.png")),hurtSound);
          
	      // load the drop sound effect and the rain background "music" 
          Texture gota = new Texture(Gdx.files.internal("drop.png"));
          Texture gotaMala = new Texture(Gdx.files.internal("moneda.png"));
          
          Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
         
	      Music music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
          carrera = new Carrera(gota, gotaMala, dropSound, music);
          crearCarretera();
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      
	      // creacion de la lluvia
	      carrera.crear();
	}
	
	public void crearCarretera() {
		carretera = new Texture(Gdx.files.internal("Carretera.png"));
	}
	


	@Override
	public void render () {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(carretera, 0, 0); 
		
		//dibujar textos
		font.draw(batch, "Puntos totales: " + ferrari.getPuntos() + "km", 10, 475);
		font.draw(batch, "Vidas : " + ferrari.getVidas(), 720, 475);
		//Controlador de vidas
		if(ferrari.getVidas()<0 ) {}
		
		if (!ferrari.estaHerido()) {
			// movimiento del tarro desde teclado
	        ferrari.actualizarMovimiento();        
			// caida de la lluvia 
	        carrera.actualizarMovimiento(ferrari);	   
		}
		ferrari.dibujar(batch);
		carrera.actualizarDibujoLluvia(batch);
		batch.end();	
	}
	
	@Override
	public void dispose () {
	      ferrari.destruir();
          carrera.destruir();
	      batch.dispose();
	      font.dispose();
	}
	
	public void createSounds() {
		
	}
}


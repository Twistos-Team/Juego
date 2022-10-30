package com.mygdx.autitos;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;



public class Autitos extends ApplicationAdapter {
       private OrthographicCamera camera;
	   private SpriteBatch batch;	   
	   private BitmapFont font;
	   private Carrera carrera;
	   private Texture gos;

	@Override
	public void create () {
		  font = new BitmapFont(); // use libGDX's default Arial font
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      carrera = new Carrera();
	      carrera.crear();
	      gos = new Texture(Gdx.files.internal("DS3.jpg"));
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
		
		//dibujar textos
		
		
		//Controlador de vidas
		if (!carrera.estadoCarrera(batch)) {
			batch.draw(gos, 0, 0);
		}
		else 
			font.draw(batch, "Vidas : " + carrera.getVidasF(), 720, 475);
		
		font.draw(batch, "Puntos totales: " + carrera.getPuntosF(), 20, 475);
		batch.end();	
	}
	
	@Override
	public void dispose () {
          carrera.destruir();
	      batch.dispose();
	      font.dispose();
	}

	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public BitmapFont getFont() {
		return font;
	}
}


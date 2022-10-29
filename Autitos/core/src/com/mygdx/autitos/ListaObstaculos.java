package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ListaObstaculos {
	private Array<Obstaculo> lista;
	private Array<Texture> sprites;
	private Array<Sound> sounds;
	
	public ListaObstaculos() {
		lista = new Array<Obstaculo>();
		importarSprites();
		importarSonidos();
	}
	
	public void clear() {
		lista.clear();
		sprites.clear();
		sounds.clear();
	}
	
	public void importarSprites() {
		sprites = new Array<Texture>();
		sprites.add(new Texture(Gdx.files.internal("Heart.png")));
		sprites.add(new Texture(Gdx.files.internal("moneda.png")));
		sprites.add(new Texture(Gdx.files.internal("BlueCar48x105.png")));
		sprites.add(new Texture(Gdx.files.internal("YCar48x105.png")));
		sprites.add(new Texture(Gdx.files.internal("BBCar48x105.png")));
		sprites.add(new Texture(Gdx.files.internal("Truck70x176.png")));
	}
	public void importarSonidos() {
		sounds = new Array<Sound>();
		sounds.add(Gdx.audio.newSound(Gdx.files.internal("KH-SFX.mp3")));
		sounds.add(Gdx.audio.newSound(Gdx.files.internal("ULTRAKILL Coin.mp3")));
		sounds.add(Gdx.audio.newSound(Gdx.files.internal("CarCrash.wav")));
	}
	
	public void crearObstaculo() {
		int rd = MathUtils.random(0,50);
		// Corazon 0, Moneda 1, Enemigo 2
		/*
		Enemigos:
		2 - Azul
		3 - Amarillo
		4 - Negro
		5 - Camion
		 */
		
		if (rd > 18) rd = 2;
		else if (rd != 0) rd = 1;
		Sound sd = sounds.get(rd);
		
		if (rd == 2) {
			rd = MathUtils.random(2,5);
		}
		
		Obstaculo oo = new Obstaculo(sprites.get(rd), sd, rd);
		oo.crear();
		lista.add(oo);
	}
	
	public void actualizarMovimiento(Ferrari ferrari) {
		// revisar si las gotas cayeron al suelo o chocaron con el tarro
		   for (int i=0; i < lista.size; i++ ) {
			  Obstaculo oo = lista.get(i);
			  Rectangle hbox = oo.getHitbox();
			  
			  hbox.y -= 300 * Gdx.graphics.getDeltaTime();
		      //cae al suelo y se elimina
		      if(hbox.y + 180 < 0) {
		    	  lista.removeIndex(i); 
		      }
		      if(hbox.overlaps(ferrari.getArea())) { //la gota choca con el tarro

		    	if(oo.getTipo() == 0) ferrari.aumentarVida();
		      		
		    	else if (oo.getTipo() == 1) ferrari.sumarPuntos(25);
		    	
		    	else ferrari.dañar();
		    	
		    	oo.getSound().play();
		    	lista.removeIndex(i);
		      }
		   }   
	}
	
	public void actualizarDibujoObstaculos(SpriteBatch batch) {
		for (int i=0; i < lista.size; i++ ) {
			  Obstaculo oo = lista.get(i);
		      batch.draw(oo.getSprite(), oo.getHitbox().x, oo.getHitbox().y); 
		   }
	}
}

package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Carrera {
	private Array<Obstaculo> obstaculos;
	private Array<Texture> sprites;

	private int dificultad;
	private int nivel;
	private int tiempo;
	
    private long lastDropTime;
    private Sound dropSound;
    private Music music;
	   
	public Carrera(Sound ss, Music mm) {
		dificultad = 999999999;
		nivel = 0;
		tiempo = 50;
		
		music = mm;
		dropSound = ss;
	}
	
	public void crear() {
		obstaculos = new Array<Obstaculo>();
		
		importarSprites();
		crearObstaculo();
	    // start the playback of the background music immediately
	    music.setLooping(true);
	    music.play();
	}
	
	public void importarSprites() {
		sprites = new Array<Texture>();
		sprites.add(new Texture(Gdx.files.internal("drop.png")));
		sprites.add(new Texture(Gdx.files.internal("enemigo.png")));
		sprites.add(new Texture(Gdx.files.internal("moneda.png")));
		// Corazon 0, Enemigo 1, Moneda 2
	}
	
	private void crearObstaculo() {
		int rd = MathUtils.random(0,15);
		
		if (rd > 10) rd = 2;
		else if (rd != 0) rd = 1;
		
		
		Obstaculo oo = new Obstaculo(sprites.get(rd), rd);
		oo.crear();
		obstaculos.add(oo);
		
		lastDropTime = TimeUtils.nanoTime();
	}
	
   public boolean actualizarMovimiento(Ferrari ferrari) { 
	   // generar gotas de lluvia 
	   tiempo -= 1;
	   if (tiempo == 0) {
		   ferrari.sumarPuntos(1);
		   tiempo = 50;
	   }
	   
	   
	   if(TimeUtils.nanoTime() - lastDropTime > dificultad) crearObstaculo();
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < obstaculos.size; i++ ) {
		  Obstaculo oo = obstaculos.get(i);
		  Rectangle hbox = oo.getHitbox();
		  hbox.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(hbox.y + 64 < 0) {
	    	  obstaculos.removeIndex(i); 
	      }
	     
	      if(hbox.overlaps(ferrari.getArea())) { //la gota choca con el tarro
	    	  int variable = oo.getTipo();
	    	  switch(variable) {
	    	  
	    	  case 0:
	    		  ferrari.aumentarVida();
	    		  break;
	    	  case 1:
	    		  ferrari.dañar();
	    		  break;
	    	  case 2:
	    		  ferrari.sumarPuntos(25);
	    		  break;
	    	  }
	    	  obstaculos.removeIndex(i);
	      }
	      
	      int pt = ferrari.getPuntos();
	      if (pt > nivel && pt < 5000) {
	    	  nivel += 500;
	    	  dificultad /= 1.2;
	      }
	   }
	   if(ferrari.getVidas() == 0)return false;
	   else return true;
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	  for (int i=0; i < obstaculos.size; i++ ) {
		  Obstaculo oo = obstaculos.get(i);
		  Rectangle hbox = oo.getHitbox();
		  
		  if(oo.getTipo()==1) // gota dañina
	         batch.draw(oo.getSprite(), hbox.x, hbox.y); 
		  else
			 batch.draw(oo.getSprite(), hbox.x, hbox.y); 
	   }
   }
   public void destruir() {
	      dropSound.dispose();
	      music.dispose();
   }
   public void pausar() {
	  music.stop();
   }
   public void continuar() {
	  music.play();
   }
   
}

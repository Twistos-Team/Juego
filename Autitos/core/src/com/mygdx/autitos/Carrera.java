/*
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
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music music;
	   
	public Carrera(Texture gotaBuena, Texture gotaMala, Sound ss, Music mm) {
		dificultad = 999999999;
		nivel = 0;
		tiempo = 50;
		
		music = mm;
		dropSound = ss;
		this.gotaBuena = gotaBuena;
		this.gotaMala = gotaMala;
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
		sprites.add(new Texture(Gdx.files.internal("enemigo.png")));
		sprites.add(new Texture(Gdx.files.internal("moneda.png")));
	}
	
	private void crearObstaculo() {
		int rd = MathUtils.random(0,3);
		if (rd != 0) rd = 0;
		else rd = 1;
		
		Obstaculo oo = new Obstaculo(sprites.get(rd), rd);
		oo.crear();
		obstaculos.add(oo);
		
		lastDropTime = TimeUtils.nanoTime();
	}
	
   public void actualizarMovimiento(Ferrari ferrari) { 
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
	    	
	    	if(oo.getTipo() == 0) { // gota dañina
	    	  ferrari.dañar();
	    	  obstaculos.removeIndex(i);

	      	}else { // gota a recolectar
	    	  ferrari.sumarPuntos(25);
	          dropSound.play();
	          obstaculos.removeIndex(i);
	      	}
	      }
	      
	      int pt = ferrari.getPuntos();
	      if (pt > nivel && pt < 5000) {
	    	  nivel += 500;
	    	  dificultad /= 1.2;
	      }
	   }   
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
   
}*/

package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Carrera {
	private ListaObstaculos lista;

	private Ferrari ferrari;
	private Camino carretera;
	private int dificultad;
	private int nivel;
    private long lastDropTime;
    private Music music;

	public Carrera() {
		dificultad = 999999999;
		nivel = 0;
		music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
	}

	public void crear() {
		lista = new ListaObstaculos();
		ferrari = new Ferrari();
		carretera = new Camino();
		crearObstaculo();
	    
	    music.setLooping(true);
	    music.play();
	}

	private void crearObstaculo() {
		lista.crearObstaculo();
		lastDropTime = TimeUtils.nanoTime();
	}

   public void actualizarMovimiento() { 

	   if(TimeUtils.nanoTime() - lastDropTime > dificultad) crearObstaculo();
	   lista.actualizarMovimiento(ferrari);
	   
	   int pt = ferrari.getPuntos();
	   if (pt > nivel && pt < 5000) {
		   nivel += 500;
		   dificultad /= 1.2;
	   }   
   }

   public void actualizarDibujoObstaculos(SpriteBatch batch) { 
	  lista.actualizarDibujoObstaculos(batch);
	  ferrari.dibujar(batch);
   }
   
   public boolean estadoCarrera(SpriteBatch batch) {
	   // False - Lose
	   // True - Still Playing
	   int est = ferrari.estado();
	   
	   if (est == 0) {
		   return false;
	   }
	   else if (est == 1) {
		   ferrari.dibujar(batch);
		   actualizarMovimiento();
	   }
	   carretera.actualizarMovimiento(batch);
	   actualizarDibujoObstaculos(batch);
	   return true;
   }
   
   public int getPuntosF() {
	   return ferrari.getPuntos();
   }
   
   public int getVidasF() {
	   return ferrari.getVidas();
   }
   
   public void destruir() {
	      music.dispose();
	      lista.clear();
	      ferrari.destruir();
   }

}

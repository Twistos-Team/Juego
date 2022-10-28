package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Ferrari extends Entidad{
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   public Ferrari(Texture tex, Sound ss) {
		   setSprite(tex);
		   setSound(ss);
		   crear();
	   }
	   
	    public int getVidas() {
			return vidas;
		}
	
		public int getPuntos() {
			return puntos;
		}
		
		public Rectangle getArea() {
			return getHitbox();
		}
		
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		
	
	   public void crear() {
		   	Rectangle hitbox = new Rectangle();
		   	hitbox.x = 800 / 2 - 64 / 2;
		   	hitbox.y = 20;
		   	hitbox.width = 40;
		   	hitbox.height = 82;
		   	setHitbox(hitbox);
	   }
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  getSound().play();
	   }
	   
	   public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   batch.draw(getSprite(), getHitbox().x, getHitbox().y);
		 else {
		
		   batch.draw(getSprite(), getHitbox().x, getHitbox().y+ MathUtils.random(-5,5));
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   } 
	   
	   public void actualizarMovimiento() { 
		   Rectangle hitbox = getHitbox();
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) hitbox.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) hitbox.x += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.UP)) hitbox.y += velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) hitbox.y -= velx * Gdx.graphics.getDeltaTime();
		   
		   // que no se salga de los bordes izq y der
		   if(hitbox.x < 0) hitbox.x = 0;
		   if(hitbox.x > 800 - 64) hitbox.x = 800 - 64;
		   if(hitbox.y < 0) hitbox.y = 0;
		   if(hitbox.y > 800-400) hitbox.y = 800 - 400;
	   }
	    

	public void destruir() {
		    getSprite().dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}

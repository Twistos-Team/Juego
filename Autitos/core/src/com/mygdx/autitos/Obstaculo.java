package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Obstaculo extends Entidad{
	int tipo;
	
	public Obstaculo(Texture tx, Sound sd, int tp){
		setSprite(tx);
		setSound(sd);
	   	this.tipo = tp;
	}
	
	public void crear() {
		int  x, y;
		//0 Corazon, 1 Moneda 
		/*
		Enemigos:
		2 - Azul
		3 - Amarillo
		4 - Negro
		5 - Camion
		 */
		
		switch (tipo) {
		case 0: x=64; y=51; break;
		
		case 1: x=y=50; break;
		
		case 5: x=70; y=176; break;
		
		default: x=48; y= 105;
		}
		
		Rectangle hitbox = new Rectangle();
	   	hitbox.x = MathUtils.random(0, 800-64);
	   	hitbox.y = 480;
	   	hitbox.width = x;
	   	hitbox.height = y;
	   	setHitbox(hitbox);
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public boolean actualizarMovimiento() {
		getHitbox().y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(getHitbox().y + 180 < 0)
	    	  return true; 
	     return false;
	}
	
	public boolean choque(Ferrari ferrari) {
		if(getHitbox().overlaps(ferrari.getArea())) {
			
	    	if(getTipo() == 0)
	    		ferrari.aumentarVida();
	      		
	    	else if (getTipo() == 1)
	    		ferrari.sumarPuntos(25);
	    	
	    	else 
	    		ferrari.dañar();
	    	
	    	getSound().play();
	    	return true;
		}
		return false;
	}
}

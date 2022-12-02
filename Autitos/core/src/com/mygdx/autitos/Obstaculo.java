package com.mygdx.autitos;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Obstaculo extends Entidad{
	int tipo;
	
	public Obstaculo(Rectangle hb, Texture tx, Sound sd, int tp){
		setHitbox(hb);
		setSprite(tx);
		setSound(sd);
	   	this.tipo = tp;
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

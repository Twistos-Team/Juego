package com.mygdx.autitos;

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
		
		/*
		Enemigos:
		2 - Azul
		3 - Amarillo
		4 - Negro
		5 - Camion
		 */
		
		switch (tipo) {
		case 0: x=64; y=51; break;
		
		case 1: x=y=64; break;
		
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
}

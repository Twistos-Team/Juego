package com.mygdx.autitos;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Obstaculo extends Entidad{
	int tipo;
	
	public Obstaculo(Texture tx, int tp){
		setSprite(tx);
	   	this.tipo = tp;
	}
	
	
	public void crear() {
		Rectangle hitbox = new Rectangle();
	   	hitbox.x = MathUtils.random(0, 800-64);
	   	hitbox.y = 480;
	   	hitbox.width = 64;
	   	hitbox.height = 64;
	   	setHitbox(hitbox);
	}
	
	public int getTipo() {
		return tipo;
	}
}

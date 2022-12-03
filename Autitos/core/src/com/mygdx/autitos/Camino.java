package com.mygdx.autitos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import patterndesign.MovementStrat;

public class Camino extends Entidad implements MovementStrat{
	
	
	public Camino(){
		crear();
	}
	
	public void crear() {
		this.setSprite(new Texture(Gdx.files.internal("Carretera.png")));
		Rectangle hbox = crearHitbox();
		this.setHitbox(hbox);
	}

	public Rectangle crearHitbox() {
		Rectangle hb = new Rectangle();
		hb.x = 0;
	   	hb.y = 0;
	   	hb.width = 0;
	   	hb.height = 0;
		return hb;
	}
	
	public void actualizarMovimiento() {
		Rectangle hb = getHitbox();
		hb.y -= 480 * Gdx.graphics.getDeltaTime();
		if (hb.y <= -480)
			hb.y = 0;
	}
	
	public void dibujar(SpriteBatch batch) {
		Rectangle hb = getHitbox();
		batch.draw(getSprite(), hb.x, hb.y);
	}
}

package com.mygdx.autitos;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entidad {
	private Rectangle hitbox;
	private Texture sprite;
	private Sound sound;
	
	
	// ABSTRACTO
	abstract public void crear();
	
	// METODOS
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public Sound getSound() {
		return sound;
	}
	
	public Texture getSprite() {
		return sprite;
	}
	
	public void setSprite(Texture tx) {
		this.sprite = tx;
	}
	
	public void setSound(Sound sd) {
		this.sound = sd;
	}
	
	public void setHitbox(Rectangle hb) {
		this.hitbox = hb;
	}
}

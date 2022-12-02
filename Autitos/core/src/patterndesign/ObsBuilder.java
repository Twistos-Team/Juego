package patterndesign;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.autitos.Obstaculo;

public class ObsBuilder implements Builder{
	private Texture sprite;
	private Sound sound;
	private Rectangle hitbox;
	private int tipo;
	
	
	public void setHitbox(Rectangle hb) {
		this.hitbox = hb;
		
	}

	public void setSound(Sound sd) {
		this.sound = sd;
		
	}
	
	public void setSprite(Texture sp) {
		this.sprite = sp;
		
	}
	
	public void setTipo(int tp) {
		this.tipo = tp;
	}
	
	public Obstaculo getObstaculo() {
		return new Obstaculo(hitbox, sprite, sound, tipo);
	}

}

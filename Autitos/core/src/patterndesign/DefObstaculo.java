package patterndesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class DefObstaculo {
	
	public Rectangle createHb(int tipo) {
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
	   	return hitbox;
	}
	
	
	public void createHeart(ObsBuilder builder) {
		builder.setHitbox(createHb(0));
		builder.setSound(Gdx.audio.newSound(Gdx.files.internal("KH-SFX.wav")));
		builder.setSprite(new Texture(Gdx.files.internal("Heart.png")));
		builder.setTipo(0);
	}
	
	public void createCoin(ObsBuilder builder) {
		builder.setHitbox(createHb(1));
		builder.setSound(Gdx.audio.newSound(Gdx.files.internal("ULTRAKILL Coin.wav")));
		builder.setSprite(new Texture(Gdx.files.internal("moneda.png")));
		builder.setTipo(1);
	}
	
	public void createBlueCar(ObsBuilder builder) {
		builder.setHitbox(createHb(2));
		builder.setSound(Gdx.audio.newSound(Gdx.files.internal("CarCrash.wav")));
		builder.setSprite(new Texture(Gdx.files.internal("BlueCar48x105.png")));
		builder.setTipo(2);
	}
	
	public void createYellowCar(ObsBuilder builder) {
		builder.setHitbox(createHb(3));
		builder.setSound(Gdx.audio.newSound(Gdx.files.internal("CarCrash.wav")));
		builder.setSprite(new Texture(Gdx.files.internal("YCar48x105.png")));
		builder.setTipo(3);
	}
	
	public void createBlackCar(ObsBuilder builder) {
		builder.setHitbox(createHb(4));
		builder.setSound(Gdx.audio.newSound(Gdx.files.internal("CarCrash.wav")));
		builder.setSprite(new Texture(Gdx.files.internal("BBCar48x105.png")));
		builder.setTipo(4);
	}
	
	public void createTruck(ObsBuilder builder) {
		builder.setHitbox(createHb(5));
		builder.setSound(Gdx.audio.newSound(Gdx.files.internal("CarCrash.wav")));
		builder.setSprite(new Texture(Gdx.files.internal("Truck70x176.png")));
		builder.setTipo(5);
	}
}

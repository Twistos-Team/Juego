package patterndesign;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface Builder {
	public void setHitbox(Rectangle hb);
	public void setSound(Sound sd);
	public void setSprite(Texture sp);
	public void setTipo(int tp);
}

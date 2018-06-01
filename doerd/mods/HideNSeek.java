package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.entity.EntityLivingBase;

public class HideNSeek extends Module{

	public HideNSeek() {
		super("Hide-N-Seek", Keyboard.KEY_Y, Category.RENDER);

	}
	
	public void onRender() {
		
		super.onRender();
	}
	
	public void renderPlayer(EntityLivingBase entity){
		float red = 0f;
		float green = 1f;
		float blue = 0f;
		
		double xPos = entity.lastTickPosX + (entity.posX - entity.lastTickPosX)*mc.timer.renderPartialTicks;
	}

}

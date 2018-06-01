package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.utils.RenderUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public class HideNSeek extends Module{

	public HideNSeek() {
		super("Hide-N-Seek", Keyboard.KEY_Y, Category.RENDER);

	}
	
	public void onRender() {
		for(Entity e : mc.theWorld.loadedEntityList){
			if(e instanceof EntityLiving){
				RenderUtils.entityESPBox(e, 0);
			}
		}
		super.onRender();
	}

}

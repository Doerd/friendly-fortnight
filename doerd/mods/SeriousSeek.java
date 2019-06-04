package doerd.mods;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.utils.RenderUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MovingObjectPosition;

public class SeriousSeek extends Module{
	
	public SeriousSeek() {
		super("SERIOUS seek", Keyboard.KEY_EQUALS, Category.RENDER);
	}
	
	public void onDisable(){

	}
	
	public void onUpdate(){
		
	}
	
	public void onRender() {
		for(Entity e : mc.theWorld.loadedEntityList){
			if(e instanceof EntityOtherPlayerMP){
				RenderUtils.entityESPBox(e, 1);
			} else if (e instanceof EntityItem) {
				RenderUtils.entityESPBox(e, 2);
			} else if (e instanceof EntityAnimal) {
				RenderUtils.entityESPBox(e, 3);
			} else if (e instanceof EntityMob) {
				RenderUtils.entityESPBox(e, 0);
			}
		}
		
		super.onRender();
	}
	
	public void parseSettings(){
		
	}

}

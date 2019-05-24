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
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MovingObjectPosition;

public class HideNSeek extends Module{

	private ArrayList<Integer> players;
	
	public HideNSeek() {
		super("Hide-N-Seek", Keyboard.KEY_Y, Category.RENDER);
		players = new ArrayList<Integer>();
	}
	
	public void onDisable(){
		players = new ArrayList<Integer>();
	}
	
	public void onUpdate(){
		for(Entity e : mc.theWorld.loadedEntityList){
			if((e instanceof EntityOtherPlayerMP) && (!players.contains(e.getEntityId()))){
				players.add(e.getEntityId());
			}
		}
		super.onUpdate();
	}
	
	public void onRender() {
		for(Entity e : mc.theWorld.loadedEntityList){
			if((e instanceof EntityFallingBlock) || players.contains(e.getEntityId()) && !(e instanceof EntityOtherPlayerMP)){
				RenderUtils.entityESPBox(e, 0);
			}
			else if(e instanceof EntityOtherPlayerMP){
				RenderUtils.entityESPBox(e, 1);
			}
		}
		
		super.onRender();
	}

}

package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.main.Doerd;
import net.minecraft.entity.Entity;

public class Spectate extends Module{
	
	private Entity spectatingEntity;
	
	private double oldPosX;
	private double oldPosY;
	private double oldPosZ;

	public Spectate() {
		super("Spectate", Keyboard.KEY_P, Category.MOVEMENT);
	}
	
	public void onEnable(){
		super.onEnable();
		
		oldPosX = mc.thePlayer.posX;
		oldPosY = mc.thePlayer.posY;
		oldPosZ = mc.thePlayer.posZ;
		
		Doerd.sendMovePackets = false;
		
	}
	
	public void onUpdate(){
		if(spectatingEntity != null){
			mc.setRenderViewEntity(spectatingEntity);
		}
		/*if(spectatingEntity != null){
			mc.thePlayer.setPositionAndRotation(spectatingEntity.posX, 
				spectatingEntity.posY, spectatingEntity.posZ, spectatingEntity.rotationYaw, 
				spectatingEntity.rotationPitch);
		}*/
	}
	
	public void onDisable(){
		mc.setRenderViewEntity(mc.thePlayer);
		mc.thePlayer.setPosition(oldPosX, oldPosY, oldPosZ);
		
		Doerd.sendMovePackets = true;
	}
	
	public void parseSettings(){
		String name = this.getSettings().replaceAll(" ", "").replaceAll("\n", "");
		for(Entity e : mc.theWorld.getLoadedEntityList()){
			if(e.getName().equals(name)){
				this.spectatingEntity = e;
				break;
			}
		}
	}

}

package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.main.Doerd;

public class Freecam extends Module{

	private double oldPosX;
	private double oldPosY;
	private double oldPosZ;
	
	public Freecam() {
		super("Freecam", Keyboard.KEY_F, Category.RENDER);
	}
	
	public void onEnable(){
		mc.thePlayer.capabilities.allowFlying = true;
		mc.thePlayer.capabilities.isFlying = true;
	}
	
	public void onUpdate(){
		
	}
	
	public void onDisable(){
		if(!mc.thePlayer.capabilities.isCreativeMode){
			mc.thePlayer.capabilities.allowFlying = false;
			mc.thePlayer.capabilities.isFlying = false;
		}
	}
	
}

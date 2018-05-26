package doerd.mods;

import org.lwjgl.input.Keyboard;
import doerd.main.Category;

public class Flight extends Module{

	private float flyHackSpeed;
	
	public Flight(float speed) {
		super("Flight", Keyboard.KEY_F, Category.MOVEMENT);
		this.flyHackSpeed = speed;
	}
	
	public void onDisable(){
		mc.thePlayer.capabilities.isFlying = false;
		super.onDisable();
	}

	public void onUpdate(){
		mc.thePlayer.capabilities.isFlying = true;
		
		if(mc.gameSettings.keyBindJump.isPressed()){
			mc.thePlayer.motionY += 0.2;
		}
		
		if(mc.gameSettings.keyBindSneak.isPressed()){
			mc.thePlayer.motionY -= 0.2;
		}
		
		if(mc.gameSettings.keyBindForward.isPressed()){
			mc.thePlayer.capabilities.setFlySpeed(flyHackSpeed);
		}
	
		super.onUpdate();
	}
	
	public float getFlyHackSpeed() {
		return flyHackSpeed;
	}

	public void setFlyHackSpeed(float flyHackSpeed) {
		this.flyHackSpeed = flyHackSpeed;
	}

}

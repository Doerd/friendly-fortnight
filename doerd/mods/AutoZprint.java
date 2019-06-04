package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;

public class AutoZprint extends Module {
	public AutoZprint() {
		super("AutoZprint", Keyboard.KEY_V, Category.MOVEMENT);
	}
	
	public void onUpdate(){
		if(mc.thePlayer.movementInput.moveForward > 0.8F && !mc.thePlayer.isSprinting()){
			mc.thePlayer.setSprinting(true);
		}
	}
}

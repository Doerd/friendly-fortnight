package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;

public class Brightness extends Module{
	
	private float previousGamma;

	public Brightness() {
		super("Brightness", Keyboard.KEY_B, Category.RENDER);
	}
	
	public void onEnable(){
		previousGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 100;
	}
	
	public void onDisable(){
		mc.gameSettings.gammaSetting = previousGamma;
	}

}

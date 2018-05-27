package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.utils.Vector;

public class InfoDisplay extends Module{

	public InfoDisplay(){
		super("InfoDisplay", Keyboard.KEY_I, Category.RENDER);
	}
	
	public void customOverlay(){
        this.mc.fontRendererObj.drawString("Pitch: " + mc.thePlayer.rotationPitch, 2, 100, 16777215);
        this.mc.fontRendererObj.drawString("Yaw: " + mc.thePlayer.rotationYaw, 2, 110, 16777215);
        Vector direction = new Vector(mc.thePlayer.rotationPitch,mc.thePlayer.rotationYaw);
        this.mc.fontRendererObj.drawString(direction.toString(),2,120,16777215);
	}

}

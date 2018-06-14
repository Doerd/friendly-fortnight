package doerd.mods;

import java.util.Collections;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class MouseTp extends Module{
	
	public static double TP_DISTANCE = 6;

	public MouseTp() {
		super("MouseTp", Keyboard.KEY_Z, Category.MOVEMENT);
	}
	
	public void parseSettings(){
		this.TP_DISTANCE = Double.parseDouble(
				this.getSettings().replaceAll(" ", "").replaceAll("\n", ""));
	}

}

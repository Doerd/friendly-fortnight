package doerd.mods;

import java.util.Collections;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class TpImmune extends Module{
	
	private double changeX;
	private double changeY;
	private double changeZ;
	private boolean stayOnGround;

	public TpImmune() {
		super("TpImmune", Keyboard.KEY_N, Category.MOVEMENT);
	}
	
	public void onUpdate(){
		mc.thePlayer.sendQueue.addToSendQueue(
				new C03PacketPlayer.C04PacketPlayerPosition(
						mc.thePlayer.posX + changeX, mc.thePlayer.getEntityBoundingBox().minY + changeY, mc.thePlayer.posZ + changeZ, this.stayOnGround));
		mc.thePlayer.sendQueue.addToSendQueue(
				new C03PacketPlayer.C04PacketPlayerPosition(
						mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ, true));
	}
	
	public void parseSettings(){

		String[] arr = this.getSettings().split("\n");
		
		this.changeX = Double.parseDouble(arr[0]);
		this.changeY = Double.parseDouble(arr[1]);
		this.changeZ = Double.parseDouble(arr[2]);
		if(this.changeY > 0){
			this.stayOnGround = false;
		}
	}

}

package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.main.Doerd;
import doerd.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;

public class PerfectJump extends Module{
	
	private boolean hasHitGround;
	final double SCALE_FACTOR = -0.01;
	
	public PerfectJump(){
		super("PerfectJump", Keyboard.KEY_J, Category.MOVEMENT);
	}
	
	public void onEnable(){
		hasHitGround = false;
		super.onEnable();
	}
	
	public void onDisable(){
		super.onDisable();
	}
	
	
	public void onUpdate(){
		EntityPlayerSP thePlayer = this.getMc().thePlayer;
		
		Vector direction = new Vector(mc.thePlayer.rotationPitch, mc.thePlayer.rotationYaw);
		double xOffset = direction.getX()*SCALE_FACTOR;
		double zOffset = direction.getZ()*SCALE_FACTOR;
	
		BlockPos playerPos = new BlockPos(thePlayer.posX, thePlayer.posY, thePlayer.posZ);
		BlockPos checkPos = playerPos.add(xOffset, 0, zOffset);
		
		if(thePlayer.onGround && this.getMc().theWorld.isAirBlock(checkPos.down(1))){
			thePlayer.jump();
		}
	}
	
	public void setHasHitGround(boolean g){
		this.hasHitGround = g;
	}
	
	public boolean getHasHitGround(){
		return this.hasHitGround;
	}
}

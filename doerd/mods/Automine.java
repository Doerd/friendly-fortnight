package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;

public class Automine extends Module {
	
	public Automine() {
		super("Automine", Keyboard.KEY_N, Category.OTHER);
	}
	
	public void onEnable(){
		BlockPos blockpos = mc.objectMouseOver.getBlockPos();
		if (blockpos != null) {
	        if (mc.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air) {
	            mc.playerController.clickBlock(blockpos, mc.objectMouseOver.sideHit);
	        }
		}
	}
	
	public void onUpdate(){
		BlockPos blockpos = null;
		try {
			blockpos = mc.objectMouseOver.getBlockPos();
		}
		catch(NullPointerException e) {
			System.out.println("CATCH: Automine Bug");
		}
		if (blockpos != null) {
	        if (mc.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air && mc.playerController.onPlayerDamageBlock(blockpos, mc.objectMouseOver.sideHit))
	        {
	            mc.effectRenderer.addBlockHitEffects(blockpos, mc.objectMouseOver.sideHit);
	            mc.thePlayer.swingItem();
	        }
		}
	}
}

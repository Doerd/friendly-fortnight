package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;

public class ToolSwitch extends Module {

	public ToolSwitch() {
		super("ToolSwitch", Keyboard.KEY_M, Category.OTHER);
	}
	
	public void onUpdate() {
		if(mc.thePlayer.isSwingInProgress){
			if(mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				BlockPos blockPos = mc.objectMouseOver.getBlockPos();
				Block block = mc.theWorld.getBlockState(blockPos).getBlock();
	
				if(block.getMaterial() != Material.air){
					InventoryPlayer inv = mc.thePlayer.inventory;
					
					int bestItem = -1;
					float maxStr = -999;
					boolean sameStr = false;
					
					for(int i = 0; i < 9; i++){
						if(inv.mainInventory[i] != null) {
							float str = inv.mainInventory[i].getStrVsBlock(block);
							if(str > maxStr) {
								maxStr = str;
								bestItem = i;
								sameStr = false;
							} else if(str == maxStr){
								sameStr = true;
							}
						}
					}
					
					if((bestItem != -1) && !sameStr) {
						inv.currentItem = bestItem;
					}
				}
			}
		}
	}
}


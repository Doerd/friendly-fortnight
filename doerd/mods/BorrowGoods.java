package doerd.mods;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BorrowGoods extends Module {
	
	private static final int RADIUS = 3;
	
	private static final int STEAL_DELAY = 1;
	private int stealDelay;
	
	private static final int ESCAPE_DELAY = 20;
	private int escapeDelay;
	
	private boolean stealInProgress;
	private int currentStealIndex;
	
	private static final int TICK_DELAY = 100;
	private int tickDelay = this.TICK_DELAY;
	
	private List<BlockPos> chestsStolenFrom;
	
	public BorrowGoods() {
		super("Borrow the goods!", Keyboard.KEY_O, Category.OTHER);
		this.escapeDelay = 0;
		this.stealDelay = this.STEAL_DELAY;
		this.stealInProgress = false;
		this.currentStealIndex = 0;
	}

	public void onEnable(){
		this.chestsStolenFrom = new ArrayList<BlockPos>();
	}
	
	public void onUpdate(){
		// if we're currently stealing something
		if(this.stealInProgress) {
			if(!this.stealFromCurrentChest()) {
				mc.thePlayer.closeScreen();
				mc.thePlayer.movementInput.moveForward = 1.0F;
				mc.thePlayer.setSprinting(true);
				this.escapeDelay = this.ESCAPE_DELAY;
				this.stealInProgress = false;
				this.currentStealIndex = 0;
				return;
			}
		}
		
		if(this.escapeDelay > 0) {
			mc.thePlayer.movementInput.moveForward = 1.0F;
			if(!mc.thePlayer.isSprinting()){
				mc.thePlayer.setSprinting(true);
			}
			this.escapeDelay--;
			return;
		}
		//getting the chest
		if(mc.thePlayer.openContainer instanceof ContainerPlayer) {
			BlockPos pos = mc.thePlayer.getPosition();
			for(int y = pos.getY() + 1; y >= pos.getY() - 1; y--){
				for(int x = pos.getX() - this.RADIUS; x < pos.getX() + this.RADIUS; x++){
					for(int z = pos.getZ() - this.RADIUS; z < pos.getZ() + this.RADIUS; z++){
					
						BlockPos theBlockPos = new BlockPos(x, y, z);
						IBlockState theBlockState = mc.theWorld.getBlockState(theBlockPos);
						Block theBlock = theBlockState.getBlock();
						// 54 is id for chest
						if(Block.getIdFromBlock(theBlock) == 54){
							// make sure we haven't already looked at this chest
							if(this.chestsStolenFrom.contains(theBlockPos)){
								return;
							}
							BlockChest chestBlock = (BlockChest)theBlock;
							EnumFacing face;
							if(y < pos.getY()){
								face = EnumFacing.UP;
							} else if(y > pos.getY()){
								face = EnumFacing.DOWN;
							} else if(x > z) {
								if(x > -1*z) {
									face = EnumFacing.WEST;
								} else {
									face = EnumFacing.SOUTH;
								} 
							} else {
								if (x > -1*z) {
									face = EnumFacing.NORTH;
								} else {
									face = EnumFacing.EAST;
								}
							}
							mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem(), theBlockPos, face, mc.thePlayer.getPositionVector());
							
							this.chestsStolenFrom.add(theBlockPos);
						}
					}
				}
			}
		} // end containerplayer if statement
		
		// if we're in a chest
		if(mc.thePlayer.openContainer instanceof ContainerChest){
			this.stealInProgress = true;
		}
	}
	
	// returns false if chest steal is completed (true if we need to keep going)
	private boolean stealFromCurrentChest(){
		if(this.stealDelay > 0) {
			this.stealDelay--;
			return true;
		}
		
		this.stealDelay = this.STEAL_DELAY;
		
		ContainerChest chest = (ContainerChest)mc.thePlayer.openContainer;
		while(this.currentStealIndex < chest.getLowerChestInventory().getSizeInventory() && !chest.getSlot(this.currentStealIndex).getHasStack()){
			this.currentStealIndex++;
		}
		if(this.currentStealIndex >= chest.getLowerChestInventory().getSizeInventory()){
			return false;
		}
		// actually steal the item
		mc.playerController.windowClick(chest.windowId, this.currentStealIndex, 0, 1, mc.thePlayer);
		this.currentStealIndex++;
		return true;
	}
		

}

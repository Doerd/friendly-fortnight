package doerd.mods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.main.Doerd;
import doerd.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

public class AntiCAPTCHA extends Module {
	
	private TimeHelper time;
	private boolean toggleAutoattack;
	private boolean wasAtkToggled;
	
	public AntiCAPTCHA(boolean atk) {
		super("AntiCAPTCHA", Keyboard.KEY_C, Category.OTHER);
		
		this.time = new TimeHelper();
		this.time.setRandomParams(3000, 2000);
		this.time.setResetTime(5001);
		
		this.toggleAutoattack = atk;
		this.wasAtkToggled = false;
	}
	
	public void onUpdate() {
		// toggle autoattack off for less suspicion
		if(!(mc.thePlayer.openContainer instanceof ContainerPlayer) && !this.wasAtkToggled) {
			if(this.toggleAutoattack && Doerd.getMod("Autoattack").isToggled()) {
				Doerd.getMod("Autoattack").setToggled(false);
				this.wasAtkToggled = true;
			}
		}
		
		if(mc.thePlayer.openContainer instanceof ContainerPlayer && this.wasAtkToggled && this.toggleAutoattack) {
			Doerd.getMod("Autoattack").setToggled(true);
			this.wasAtkToggled = false;
		}
		
		if((mc.thePlayer.openContainer != null) && 
				(mc.thePlayer.openContainer instanceof ContainerChest) && 
				(this.time.isRandomDelayComplete())){
			
			ContainerChest chest = (ContainerChest)mc.thePlayer.openContainer;
			String name = chest.getLowerChestInventory().getName();
			
			// if its a captcha
			if(name.contains("Click the:")){
				// get the item name we have to click
				String itemName = name.substring(name.indexOf(':') + 4);
				
				// iterate through inventory
				for(int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); i++){
					ItemStack item = chest.getLowerChestInventory().getStackInSlot(i);
					
					// if we have the right name, click the item
					Block toClick = Block.getBlockFromName(this.formatName(itemName));
					if(toClick == null){
						Item.getByNameOrId(this.formatName(itemName));
					}
					if((item != null) && (Item.getIdFromItem(item.getItem()) == Block.getIdFromBlock(toClick))){
						mc.playerController.windowClick(chest.windowId, i, 0, 0, mc.thePlayer);
						time.reset();
					}
				}
			}
		}
	}
	
	public String formatName(String name) {
		return "minecraft:" + name.toLowerCase().replaceAll(" ", "_");
	}
	
}

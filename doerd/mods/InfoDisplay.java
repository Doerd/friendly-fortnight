package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.utils.Vector;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public class InfoDisplay extends Module{

	public InfoDisplay(){
		super("InfoDisplay", Keyboard.KEY_I, Category.RENDER);
	}
	
	public void customOverlay(){
		int i = 0;
        //Position
        double coords[] = {mc.thePlayer.posX,mc.thePlayer.posY,mc.thePlayer.posZ};
        String coordDisplay = String.format("(%.2f,%.2f,%.2f)",coords[0],coords[1],coords[2]);
        this.mc.fontRendererObj.drawStringWithShadow(String.format("%.2f", coords[0]), 2, 100+i, 65280);
        i += 10;
        this.mc.fontRendererObj.drawString(String.format("%.2f", coords[1]), 2, 100+i, 16777215);
        i += 10;
        this.mc.fontRendererObj.drawStringWithShadow(String.format("%.2f", coords[2]), 2, 100+i, 65280);
        i += 10;
        //Direction
        Vector direction = new Vector(mc.thePlayer.rotationPitch,mc.thePlayer.rotationYaw);
        this.mc.fontRendererObj.drawString(direction.toString(),2,100+i,16777215);
        i += 10;
		//Pitch and Yaw
        this.mc.fontRendererObj.drawString("Pitch: " + mc.thePlayer.rotationPitch, 2, 100+i, 16777215);
        i += 10;
        this.mc.fontRendererObj.drawString("Yaw: " + mc.thePlayer.rotationYaw, 2, 100+i, 16777215);
        i += 10;
        //Entity
        Entity hit = mc.objectMouseOver.entityHit;
        if (hit != null) {
	        String entityInfo = String.format("Ent{%s:%d}",hit.getName(),hit.getEntityId());
	        this.mc.fontRendererObj.drawString(entityInfo, 2, 100+i, 16777215);	
	        i += 10;
        }
        if(mc.objectMouseOver.getBlockPos() != null) {
        	BlockPos blockPosition = mc.objectMouseOver.getBlockPos();
        	String blockInfo = mc.theWorld.getBlockState(blockPosition).getBlock().toString();
        	this.mc.fontRendererObj.drawString(blockInfo, 2, 100+i, 16777215);
        	i += 10;
        }
	}

}

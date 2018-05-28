package doerd.mods;

import java.util.List;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.utils.Vector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class RangeLock extends Module{

	public RangeLock() {
		super("RangeLock", Keyboard.KEY_R, Category.ATTACK);
	}
	public void onEnable(){
		super.onEnable();
		EntitiesNearPlayer();
	}
	public List EntitiesNearPlayer() {
		double range = 5;
		double playerPos[] = {mc.thePlayer.posX,mc.thePlayer.posY,mc.thePlayer.posZ};
		AxisAlignedBB aabb = new AxisAlignedBB(playerPos[0]-range,playerPos[1]-range,playerPos[2]-range,
											   playerPos[0]+range,playerPos[1]+range,playerPos[2]+range);
		List entities = mc.theWorld.getEntitiesWithinAABB(EntityLivingBase.class,aabb);
		System.out.println(entities);
		return entities;
	}
	public void chooseEntity(List<EntityLivingBase> entities) {
		Vector playerPos = new Vector(mc.thePlayer.posX,mc.thePlayer.posY,mc.thePlayer.posZ);
		double min = 10000;
		EntityLivingBase entity = null;
		for (EntityLivingBase e: entities) {
			double entityPos[] = {e.posX,e.posY,e.posZ};
			System.out.println(e);
		}
	}
}

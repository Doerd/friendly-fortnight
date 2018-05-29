package doerd.mods;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MovingObjectPosition;

public class LookAssist extends Module{
	private String[] lockEntities;
	private double lockRangeSquared;
	private float moveSpeed;
	
	private boolean lock;
	private int entityID;

	public LookAssist(int range, float speed, String[] ents) {
		super("LookAssist", Keyboard.KEY_L, Category.ATTACK);
		this.lock = false;
		this.lockRangeSquared = range*range;
		this.lockEntities = ents;
		this.moveSpeed = speed;
	}
	
	public void onEnable(){
		super.onEnable();
		this.lock = false;
	}
	
	public void onUpdate(){
		if(!lock && (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)){
			this.decideToLock();
		}
		else{
			try{
				if(lock){
					Entity other = mc.theWorld.getEntityByID(entityID);
					
					double zDiff = mc.thePlayer.posZ - other.posZ;
					double xDiff = mc.thePlayer.posX - other.posX;
					double yDiff = mc.thePlayer.posY - (other.posY - other.height/2);
					double zxDist = Math.sqrt(xDiff*xDiff + zDiff*zDiff);
					double distSquared = yDiff*yDiff + zxDist*zxDist;
					
					float newPitch = (float)(Math.atan2(yDiff, zxDist)*(180/Math.PI));
					float newYaw = (float)(Math.atan2(zDiff, xDiff)*(180/Math.PI)) + 90f;
					if(newYaw < 0){
						newYaw += 360;
					}
					
					float pitchDiff = newPitch - mc.thePlayer.rotationPitch;
					float yawDiff = 0;
					
					float playerYaw360 = mc.thePlayer.rotationYaw%360;
					
					if(playerYaw360 - newYaw > 180){
						playerYaw360 -= 360;
					}else if(playerYaw360 - newYaw < -180){
						playerYaw360 += 360;
					}
					
					if(Math.abs(newYaw - playerYaw360) > 180){
						System.out.println(newYaw);
						yawDiff = newYaw - (360 + playerYaw360);
					}else{
						yawDiff = newYaw - playerYaw360;
					}
					
					
					if(Math.abs(yawDiff) > this.moveSpeed){
						mc.thePlayer.rotationYaw += Math.signum(yawDiff)*this.moveSpeed;
					}else {
						mc.thePlayer.rotationYaw += yawDiff;
					}
					
					if(Math.abs(pitchDiff) > this.moveSpeed){
						mc.thePlayer.rotationPitch += Math.signum(pitchDiff)*this.moveSpeed;
					}else {
						mc.thePlayer.rotationPitch += pitchDiff;
					}
					
					if(!mc.theWorld.getEntityByID(entityID).isEntityAlive() || distSquared > lockRangeSquared){
						lock = false;
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void decideToLock(){
		System.out.println(mc.objectMouseOver.entityHit);
		for(String s : this.lockEntities){
			if(s.equalsIgnoreCase("Player")){
				if(mc.objectMouseOver.entityHit instanceof EntityOtherPlayerMP){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Blaze")){
				if(mc.objectMouseOver.entityHit instanceof EntityBlaze){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Enderman")){
				if(mc.objectMouseOver.entityHit instanceof EntityEnderman){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Creeper")){
				if(mc.objectMouseOver.entityHit instanceof EntityCreeper){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Magma cube")){
				if(mc.objectMouseOver.entityHit instanceof EntityMagmaCube){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Slime")){
				if(mc.objectMouseOver.entityHit instanceof EntitySlime){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Cave spider")){
				if(mc.objectMouseOver.entityHit instanceof EntityCaveSpider){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
			if(s.equalsIgnoreCase("Silverfish")){
				if(mc.objectMouseOver.entityHit instanceof EntitySilverfish){
					lock = true;
					entityID = mc.objectMouseOver.entityHit.getEntityId();
					break;
				}
			}
		}
	}
	
	public void parseSettings(){
		String[] arr = this.getSettings().split("\n");
		
		int range = Integer.parseInt(arr[arr.length - 2]);
		this.lockRangeSquared = range*range;
		this.moveSpeed = Integer.parseInt(arr[arr.length - 1]);
		this.lockEntities = Arrays.copyOfRange(arr, 0, arr.length - 2);
	}
	
}

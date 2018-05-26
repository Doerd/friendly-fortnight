package doerd.mods;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class Autoattack extends Module{

	private int delay;
	private static int HIT_DELAY;
	
	private String[] entitiesToHit;
	
	
	public Autoattack(int d, String[] ents) {
		super("Autoattack", Keyboard.KEY_K, Category.ATTACK);
		HIT_DELAY = d;
		this.delay = HIT_DELAY;
		this.entitiesToHit = ents;
	}
	
	public void onDisable(){
		super.onDisable();
	}
	
	public void onUpdate(){
		boolean yesHit = false;
		if(this.delay <= 0 && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY){
			for(String s : this.entitiesToHit){
				if(s.equalsIgnoreCase("Player")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityOtherPlayerMP);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Blaze")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityBlaze);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Enderman")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityEnderman);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Creeper")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityCreeper);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Magma cube")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityMagmaCube);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Slime")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySlime);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Skeleton")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySkeleton);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Zombie")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityZombie);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Iron golem")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityIronGolem);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Zombie pigman")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityPigZombie);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Cave spider")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityCaveSpider);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Witch")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityWitch);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Spider")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySpider);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Silverfish")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySilverfish);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Giant zombie")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityGiantZombie);
					if(yesHit){break;}
				}
				if(s.equalsIgnoreCase("Snowman")){
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySnowman);
					if(yesHit){break;}
				}
			}
			
			if(yesHit){
				mc.thePlayer.swingItem();
				mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
				this.delay = HIT_DELAY + (int)(Math.random()*3 - 1);
			}
		}
		else{
			this.delay--;
		}
	}
	
	public void parseSettings(){
		String[] arr = this.getSettings().split("\n");
		this.delay = Integer.parseInt(arr[arr.length-1]);
		this.entitiesToHit = Arrays.copyOfRange(arr, 0, arr.length - 1);
	}
	
	

	public String[] getEntitiesToHit() {
		return entitiesToHit;
	}

	public void setEntitiesToHit(String[] entitiesToHit) {
		this.entitiesToHit = entitiesToHit;
	}
}

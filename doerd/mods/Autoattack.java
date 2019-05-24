package doerd.mods;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class Autoattack extends Module{

	private int currentDelay;
	private static int HIT_DELAY;
	
	private String[] entitiesToHit;
	
	
	public Autoattack(int d, String[] ents) {
		super("Autoattack", Keyboard.KEY_K, Category.ATTACK);
		HIT_DELAY = d;
		this.currentDelay = HIT_DELAY;
		this.entitiesToHit = ents;
	}
	
	public void onEnable(){
		super.onEnable();
	}
	
	public void onDisable(){
		super.onDisable();
	}
	
	public void onUpdate(){
		boolean yesHit = false;
		if(this.currentDelay <= 0 && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY){
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
				if (s.equalsIgnoreCase("Bat")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityBat);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Chicken")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityChicken);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Cow")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityCow);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Mooshroom")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityMooshroom);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Ocelot")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityOcelot);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Pig")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityPig);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Rabbit")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityRabbit);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Sheep")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySheep);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Squid")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntitySquid);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Villager")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityVillager);
					if(yesHit){break;}
				}
				if (s.equalsIgnoreCase("Horse")) {
					yesHit = (mc.objectMouseOver.entityHit instanceof EntityHorse);
					if(yesHit){break;}	
				}
			}
			
			if(yesHit){
				mc.thePlayer.swingItem();
				mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
				this.currentDelay = HIT_DELAY + (int)(Math.random()*3 - 1);
			}
		}
		else{
			this.currentDelay--;
		}
	}
	
	public void parseSettings(){
		String[] arr = this.getSettings().split("\n");
		this.currentDelay = Integer.parseInt(arr[arr.length-1]);
		this.entitiesToHit = Arrays.copyOfRange(arr, 0, arr.length - 1);
	}
	
	
	public String[] getEntitiesToHit() {
		return entitiesToHit;
	}

	public void setEntitiesToHit(String[] entitiesToHit) {
		this.entitiesToHit = entitiesToHit;
	}
}

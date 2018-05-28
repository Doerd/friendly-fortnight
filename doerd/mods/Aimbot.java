package doerd.mods;

import java.util.List;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class Aimbot extends Module{
	//figure out how to find entityId of player far away
	
	public Aimbot(String nm, int k, Category c) {
		super("Aimbot", Keyboard.KEY_B, Category.ATTACK);
	}
}

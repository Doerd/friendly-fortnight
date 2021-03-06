package doerd.main;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Point;

import doerd.mods.*;
import doerd.utils.DoublePoint;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;

public class Doerd {
	private static ArrayList<Module> mods;
	public static boolean sendMovePackets;
	
	public Doerd(){
		mods = new ArrayList<Module>();
		sendMovePackets = true;
		
		addMod(new MouseTp());
		addMod(new Brightness());
		addMod(new Freecam());
		addMod(new HideNSeek());
		addMod(new LookAssist(10, 5, new String[]{}));
		addMod(new InfoDisplay());
		addMod(new Autoattack(5, new String[]{}));
		addMod(new Haggle());
		addMod(new PerfectJump());
		addMod(new Automine());
		addMod(new ToolSwitch());
		addMod(new AntiCAPTCHA(true));
		addMod(new BorrowGoods());
		addMod(new AutoZprint());
		addMod(new SeriousSeek());
	}
	
	public void addMod(Module m){
		mods.add(m);
	}
	
	public static ArrayList<Module> getModules(){
		return mods;
	}
	
	public static void onUpdate(){
		for(Module m : mods){
			if(m.isToggled()){
				m.onUpdate();
			}
		}
	}
	
	public static void onRender(){
		for(Module m : mods){
			if(m.isToggled()){
				m.onRender();
			}
		}
	}
	
	public static void onKeyPress(int k){
		for(Module m : mods){
			if(m.getKey() == k){
				m.toggle();
			}
		}
	}
	
	public static Module getMod(String name){
		for(Module m : mods){
			if(m.getName().equals(name)){
				return m;
			}
		}
		
		return null;
	}
}

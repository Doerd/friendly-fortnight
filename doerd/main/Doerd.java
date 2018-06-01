package doerd.main;

import java.util.ArrayList;

import org.lwjgl.util.Point;

import doerd.mods.*;
import doerd.utils.DoublePoint;

public class Doerd {
	private static ArrayList<Module> mods;
	
	public Doerd(){
		mods = new ArrayList<Module>();
		
		addMod(new TpImmune());
		addMod(new LookAssist(10, 5, new String[]{}));
		addMod(new InfoDisplay());
		addMod(new Move(new DoublePoint[]{}, 0f, 0f));
		addMod(new Autoattack(5, new String[]{}));
		//addMod(new Flight(0.1f));
		
		//--testing--
		addMod(new Haggle());
		addMod(new RangeLock());
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
			m.onRender();
		}
	}
	
	public static void onKeyPress(int k){
		for(Module m : mods){
			if(m.getKey() == k){
				m.toggle();
			}
		}
	}
}

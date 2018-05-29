package doerd.mods;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;

public class Haggle extends Module{
	
	public String arr[];
	public String names[];
	public String haggles[];
	public int HAGGLE_DELAY;
	long last_haggle = 0;
	
	public Haggle() {
		super("Haggle", Keyboard.KEY_H, Category.OTHER);
	}
	public void onUpdate() {
		long now = System.currentTimeMillis();
		if (now - last_haggle > HAGGLE_DELAY*1000) {
			last_haggle = now;
			mc.thePlayer.sendChatMessage(makeHaggle());
		}
	}
	public String makeHaggle() {
		String name = names[(int)(Math.random()*names.length)];
		String haggle = haggles[(int)(Math.random()*haggles.length)];
		String ret = haggle.replace("*",name);
		return ret;
	}
	public void parseSettings() {
		arr = this.getSettings().split("\n");
		names = arr[0].split(",");
		HAGGLE_DELAY = Integer.parseInt(arr[1]);
		haggles = Arrays.copyOfRange(arr, 2, arr.length);
	}
}

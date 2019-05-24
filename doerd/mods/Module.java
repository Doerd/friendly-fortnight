package doerd.mods;

import doerd.constant.UserSpecific;
import doerd.main.Category;
import net.minecraft.client.Minecraft;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Module {
	protected Minecraft mc = Minecraft.getMinecraft();
	private String name;
	private int key;
	private boolean toggled;
	private Category category;
	private String settings;
	
	public Module(String nm, int k, Category c){
		name = nm;
		key = k;
		category = c;
		toggled = false;
	}
	
	public void toggle(){
		toggled = !toggled;
		if(toggled){
			onEnable();
		}
		else{
			onDisable();
		}
	}
	
	public void onEnable(){
		this.settingsFromFile();
		this.parseSettings();
	}
	
	public void onDisable(){}
	public void onUpdate(){}
	public void onRender(){}
	public void customOverlay(){}
	
	public void parseSettings(){}
	public void settingsFromFile(){
		String content = "";
        Scanner in = null;
                
        try{
            in = new Scanner(new FileReader(
            		UserSpecific.filePath + this.name.toLowerCase() + ".txt"));
            
            while(in.hasNextLine()){
                content += in.nextLine();
                content += "\n";
            }
            
            in.close();
            
            this.settings = content;
        }catch(FileNotFoundException e){
            this.settings = "";
            System.out.println("no settings file found");
        }catch(Exception e2){
        	e2.printStackTrace();
        }
	}

	public Minecraft getMc() {
		return mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}
	
}

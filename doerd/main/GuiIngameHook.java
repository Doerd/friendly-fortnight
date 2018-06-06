package doerd.main;

import java.awt.Color;

import doerd.mods.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class GuiIngameHook extends GuiIngame{

	private int[] colors;
	
	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
		colors = new int[Doerd.getModules().size()];
		for(int i=0; i<colors.length; i++){
			colors[i] = (int)(Math.random()*16777215);
		}
	}
	
	public void renderGameOverlay(float partialTicks)
    {
		super.renderGameOverlay(partialTicks);
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.mc.entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();
        
        int count = 0;
        
        for(Module m : Doerd.getModules()){
        	if(m.isToggled()){
        		m.customOverlay();
        		this.mc.fontRendererObj.drawString(m.getName(), 2, 2 + count*10, this.colors[Doerd.getModules().indexOf(m)]);
        		count++;
        	}
        }
    }

}

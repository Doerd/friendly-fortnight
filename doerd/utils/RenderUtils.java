package doerd.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class RenderUtils {

	public static void entityESPBox(Entity entity, int mode) {
	        GlStateManager.blendFunc(770, 771);
	        GlStateManager.enableBlend();
	        GlStateManager.disableTexture2D();
	        GlStateManager.disableDepth();
	        GlStateManager.depthMask(false);
	        GlStateManager.resetColor();
	        if(mode == 0)// Red
	        	GlStateManager.color(1.0F, 0, 0, 0.5F);
	        else if(mode == 1)// Blue
	        	GlStateManager.color(0, 0, 1.0F, 0.5F);
	        else if(mode == 2)// Yellow
	        	GlStateManager.color(1.0F, 1.0F, 0, 0.5F);
	        else if(mode == 3)// Green
	        	GlStateManager.color(0, 1.0F, 0, 0.5F);
	        RenderGlobal.func_181561_a(
	            new AxisAlignedBB(
	                entity.boundingBox.minX
	                    - 0.05
	                    - entity.posX
	                    + (entity.posX - Minecraft.getMinecraft()
	                        .getRenderManager().renderPosX),
	                entity.boundingBox.minY
	                    - entity.posY
	                    + (entity.posY - Minecraft.getMinecraft()
	                        .getRenderManager().renderPosY),
	                entity.boundingBox.minZ
	                    - 0.05
	                    - entity.posZ
	                    + (entity.posZ - Minecraft.getMinecraft()
	                        .getRenderManager().renderPosZ),
	                entity.boundingBox.maxX
	                    + 0.05
	                    - entity.posX
	                    + (entity.posX - Minecraft.getMinecraft()
	                        .getRenderManager().renderPosX),
	                entity.boundingBox.maxY
	                    + 0.1
	                    - entity.posY
	                    + (entity.posY - Minecraft.getMinecraft()
	                        .getRenderManager().renderPosY),
	                entity.boundingBox.maxZ
	                    + 0.05
	                    - entity.posZ
	                    + (entity.posZ - Minecraft.getMinecraft()
	                        .getRenderManager().renderPosZ)));
	        GlStateManager.enableTexture2D();
	        GlStateManager.enableDepth();
	        GlStateManager.depthMask(true);
	        GlStateManager.disableBlend();
	}
}
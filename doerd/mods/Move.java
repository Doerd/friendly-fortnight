package doerd.mods;

import org.lwjgl.input.Keyboard;

import doerd.main.Category;
import doerd.utils.DoublePoint;

public class Move extends Module{

	private DoublePoint[] pointSequence;
	private float finalPitch;
	private float finalYaw;
	private int currentIndex = 0;
	
	// add point sequence param
	public Move(DoublePoint[] seq, float fp, float fy) {
		super("Move", Keyboard.KEY_M, Category.MOVEMENT);
		this.pointSequence = seq;
		this.finalPitch = fp;
		this.finalYaw = fy;
	}
	
	public void onEnable(){
		super.onEnable();
	}
	
	public void onUpdate(){
		if(currentIndex == pointSequence.length){
			currentIndex = 0;
			mc.thePlayer.rotationPitch = finalPitch;
			mc.thePlayer.rotationYaw = finalYaw;
			this.toggle();
		}
		else{
			mc.thePlayer.movementInput.moveForward = 1f;
			mc.thePlayer.rotationPitch = 0;
			
			double zDiff = mc.thePlayer.posZ - pointSequence[currentIndex].getY();
			double xDiff = mc.thePlayer.posX - pointSequence[currentIndex].getX();
			
			mc.thePlayer.rotationYaw = (float)(Math.atan2(zDiff, xDiff)*(180/Math.PI)) + 90f;
			
			if(zDiff*zDiff + xDiff*xDiff < 0.2*0.2){
				currentIndex++;
			}
		}
	}
	
	public void parseSettings(){
		String[] splitByLine = this.getSettings().split("\n");
		
		if(splitByLine.length > 1){
			this.pointSequence = new DoublePoint[splitByLine.length - 1];
			
			for(int i=0; i<pointSequence.length; i++){
				String[] bothInts = splitByLine[i].split(" ");
				double x = Double.parseDouble(bothInts[0]);
				double z = Double.parseDouble(bothInts[1]);
				this.pointSequence[i] = new DoublePoint(x, z);
			}
			
			String finalPitchYaw = splitByLine[splitByLine.length - 1];
			String[] splitPY = finalPitchYaw.split("\\*");
			this.finalPitch = Float.parseFloat(splitPY[0]);
			this.finalYaw = Float.parseFloat(splitPY[1]);
		}
		else{
			this.pointSequence = new DoublePoint[]{};
			this.finalPitch = 0F;
			this.finalYaw = 0F;
		}
	}
	
	public void naturalHeadMovement(){
		if(Math.abs(mc.thePlayer.rotationPitch) < 11){
				mc.thePlayer.rotationPitch = mc.thePlayer.rotationPitch + (float)(Math.random()*3 - 1);
			
		}else{mc.thePlayer.rotationPitch = Math.signum(mc.thePlayer.rotationPitch)*10;}
		if(Math.abs(mc.thePlayer.rotationYaw) < 11){
				mc.thePlayer.rotationYaw = mc.thePlayer.rotationYaw + (float)(Math.random()*3 - 1);
			
		}else{mc.thePlayer.rotationYaw = Math.signum(mc.thePlayer.rotationYaw)*10;}
	}

}

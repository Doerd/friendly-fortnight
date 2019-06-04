package doerd.utils;

public class TimeHelper {
	
	private long lastMS = 0;
	private long resetTime = Long.MAX_VALUE;
	
	private long nextRandomDelay = 0L;
	private long randomCenter = 0L;
	private long randomRange = 0L;
	
	public boolean isDelayComplete(long l) {
		this.resetIfTimeElapsed();
		return (getCurrentMS() - this.lastMS) >= l;
	}
	
	public boolean isRandomDelayComplete() {
		if(this.isDelayComplete(this.nextRandomDelay)) {
			this.nextRandomDelay = (long)((Math.random() - 0.5)*2*this.randomRange + this.randomCenter);
			return true;
		}
		return false;
	}
	
	public void reset(){
		this.lastMS = getCurrentMS();
	}
	
	public void resetIfTimeElapsed(){
		if(this.getCurrentMS() - this.lastMS > this.resetTime){
			this.reset();
		}
	}
	
	public void setResetTime(long reset) {
		this.resetTime = reset;
	}
	
	public void setRandomParams(long center, long variation) {
		this.randomCenter = center;
		this.randomRange = variation;
	}
	
	public long getCurrentMS(){
		return System.nanoTime() / 1000000L;
	}
	
}

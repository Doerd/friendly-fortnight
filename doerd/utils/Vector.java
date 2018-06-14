package doerd.utils;

public class Vector {
	double x;
	double y;
	double z;
	public Vector(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
	}
	public Vector(double pitch, double yaw) {
		double theta = toRads(yaw);
		double phi = toRads(pitch);
		this.x = -1*Math.cos(phi)*Math.sin(theta);
		this.y = -1*Math.sin(phi);
		this.z = Math.cos(phi)*Math.cos(theta);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double dotProduct(Vector u) {
		return this.x*u.x+this.y*u.y+this.z*u.z;
	}
	public double magnitude() {
		return Math.sqrt(this.dotProduct(this));
	}
	public String toString() {
		return String.format("<%.2f,%.2f,%.2f>",x,y,z);
	}
	public double toRads(double theta) {
		return theta*(Math.PI/180);
	}
}


public class BouncingBall {
	private double x, vx, y, vy,radius, mass;
	
	public BouncingBall(double x, double vx, double y, double vy, double radius, double mass){
		this.x = x;
		this.vx = vx;
		this.y = y;
		this.vy = vy;
		this.radius = radius;
		this.mass = mass;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public double getVy(){
		return this.vy;
	}
	public double getVx(){
		return this.vx;
	}
	public double getRadius(){
		return this.radius;
	}
	public double getMass(){
		return this.mass;
	}
}

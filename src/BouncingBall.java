
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
	public void setVy(double Vy){ this.vy = Vy; }
	public void setVx(double Vx){ this.vx = Vx; }
	public void setX(double x){this.x = x;}
	public void setY(double y){this.y = y;}
	
	public boolean isColliding(BouncingBall ball2) {
		if (this.x + this.radius + ball2.radius > ball2.x
                && this.x < ball2.x + this.radius + ball2.radius
                && this.y + this.radius + ball2.radius > ball2.y
                && this.y < ball2.y + this.radius + ball2.radius)
		{
			double cathetus1 = Math.pow(this.x - ball2.x,2);
			double cathetus2 = Math.pow(this.y - ball2.y,2);
			double hypotenuse = Math.sqrt(cathetus1 + cathetus2);
			return hypotenuse <= this.radius + ball2.getRadius();
		}
		return false;
	}
	//public String toString(){
		//return ”X: ” + this.x + ” Y: ” + this.y + ” Vx: ” + this.vx + 
		//” Vy: ” + this.vy + ” Radius: ” + this.radius + ” Mass: ” + this.mass;  
	//}

}

import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravity = 1;
	private BouncingBall[] ballList;
	private double dist,angle;

	public DummyModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		//Skapar listan med bollar och addar dem
		ballList = new BouncingBall[2];
		ballList[0] = new BouncingBall(5.0,6.0,5.0,14.0,1.0,20.0);
		ballList[1] = new BouncingBall(10.0,-8.0,10.0,12.0,1.5,30.0);
	}
	
	@Override
	public void tick(double deltaT) {
		boolean isCollided = false;
		for (BouncingBall ball : ballList) {
			ball.setX(ball.getX() + (ball.getVx() * deltaT));
			ball.setY(ball.getY() + (ball.getVy() * deltaT));
		}
		for (int i = 0; i<ballList.length-1; i++) {
			BouncingBall ball1 = ballList[i];
			for (int j = i+1; j<ballList.length; j++) {
				BouncingBall ball2 = ballList[j];
				double xDist = Math.pow(ball1.getX() - ball2.getX(),2);
				double yDist = Math.pow(ball1.getY() - ball2.getY(),2);
				double totDist = Math.sqrt(xDist + yDist);
				if(totDist <= ball1.getRadius() + ball2.getRadius())
				{
					collision(ball1, ball2);
					isCollided = true;
				}
			}
		}
		//Change velocity when colliding with walls
		for (BouncingBall ball : ballList) {
			if (ball.getX() < ball.getRadius() || ball.getX() > areaWidth - ball.getRadius()) {
				ball.setVx(ball.getVx() * -1);
				isCollided = true;
			}
			if (ball.getY() < ball.getRadius() || ball.getY() > areaHeight - ball.getRadius()) {
				ball.setVy(ball.getVy() * -1);
				isCollided = true;
			}
		}
		//If not collided, apply gravity
		if (!isCollided) {
			for (BouncingBall ball : ballList) {
				ball.setVy(ball.getVy() - (ball.getMass() * deltaT * gravity));
			}
		}
	}
	public void collision(BouncingBall ball1, BouncingBall ball2) {
		double deltaX, deltaY, rotAngle, I, R;

		deltaX = ball1.getX() - ball2.getX();
		deltaY = ball1.getY() - ball2.getY();
		rotAngle = Math.atan(deltaY / deltaX);

		rotate(-rotAngle, ball1);
		rotate(-rotAngle, ball2);

		I = ball1.getMass() * ball1.getVx() + (ball2.getMass() * ball2.getVx());
		R = -(ball2.getVx() - ball1.getVx());

		ball1.setVx((I - R * ball2.getMass()) / (ball1.getMass() + ball2.getMass()));
		ball2.setVx(R + ball1.getVx());

		rotate(rotAngle, ball1);
		rotate(rotAngle, ball2);
	}
	public void rotate(double rotateAngle, BouncingBall ball) {
		rectToPolar(ball);
		angle+=rotateAngle;
		polarToRect(ball);
	}

	public void rectToPolar(BouncingBall ball) {
		dist = Math.sqrt(ball.getVx()*ball.getVx()+ball.getVy()*ball.getVy());
		angle= Math.atan(ball.getVy()/ball.getVx());
		if (ball.getVx()<0){
			angle+=Math.PI;
		}
	}

	public void polarToRect(BouncingBall ball) {
		ball.setVx(dist*Math.cos(angle));
		ball.setVy(dist*Math.sin(angle));
	}

	@Override
	public List<Ellipse2D> getBalls() {
		List<Ellipse2D> myBalls = new LinkedList<Ellipse2D>();
		double r;
		double x;
		double y;
		for(BouncingBall ball : ballList){
			r = ball.getRadius();
			x = ball.getX();
			y = ball.getY();
			myBalls.add(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
		}
		return myBalls;
	}
}

import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravity;
	private List<BouncingBall> ballList;
	double r,angle;
	private boolean isCollided;


	public DummyModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		gravity = -9.82;
		//Skapar listan med bollar och addar dem
		ballList = new LinkedList<BouncingBall>();
		ballList.add(new BouncingBall(1.0,1.0,5.3,5.0,1.0,1.0));
		ballList.add(new BouncingBall(5.0,4.0,7.3,5.0,1.0,2.0));
	}


	public void rectToPolar(BouncingBall ball) {
		r= Math.sqrt(ball.getVx()*ball.getVx()+ball.getVy()*ball.getVy());
		angle= Math.atan(ball.getY()/ball.getX());
		if (ball.getVx()<0){
			angle+=Math.PI;
		}
	}

	public void polarToRect(BouncingBall ball) {
		ball.setVx(r*Math.cos(angle));
		ball.setVy(r*Math.sin(angle));
	}

	public void rotate(double rotateAngle, BouncingBall ball) {
		rectToPolar(ball);
		angle+=rotateAngle;
		polarToRect(ball);
	}

	public void collision(BouncingBall ball1, BouncingBall ball2) {
		double deltaX, deltaY, rotAngle, I, R;

		deltaX = Math.abs(ball1.getX() - ball2.getX());
		deltaY = Math.abs(ball1.getY() - ball2.getY());
		rotAngle = Math.atan(deltaY / deltaX);

		rotate(-rotAngle, ball1);
		rotate(-rotAngle, ball2);

		I = ball1.getMass() * ball1.getVx() + ball2.getMass() * ball2.getVx();
		R = -(ball2.getVx() - ball1.getVx());

		ball1.setVx((I - R * ball2.getMass()) / (ball1.getMass() + ball2.getMass()));
		ball2.setVx(R + ball1.getVx());

		rotate(rotAngle, ball1);
		rotate(rotAngle, ball2);
	}

	@Override
	public void tick(double deltaT) {
		isCollided=false;

		for(BouncingBall ball : ballList) {
			ball.setX(ball.getX() + ball.getVx() * deltaT);
			ball.setY(ball.getY() + ball.getVy() * deltaT);
		}
		for(BouncingBall ball : ballList){
			BouncingBall ball1 = ball;
			for(BouncingBall balll : ballList){
				BouncingBall ball2 = balll;
				double deltaX,deltaY,collisionDistance;
				deltaX = Math.abs(ball1.getX() - ball2.getX());
				deltaY = Math.abs(ball1.getY() - ball2.getY());
				collisionDistance = ball1.getRadius() + ball2.getRadius();
				/*if(ball1.getX() + ball1.getRadius() + ball2.getRadius() > ball2.getX()
						&& ball1.getX() < ball2.getX() + ball1.getRadius() + ball2.getRadius()
						&& ball1.getY() + ball1.getRadius() + ball2.getRadius() > ball2.getY()
						&& ball1.getY() < ball2.getY() + ball1.getRadius() + ball2.getRadius())*/
				if ((deltaX * deltaX + deltaY * deltaY <= collisionDistance * collisionDistance) && !isCollided && !ball1.equals(ball2)) {
					collision(ball1, ball2);
					isCollided = true;
				}

			}
		}

		for(BouncingBall ball : ballList){
			if (ball.getX() < ball.getRadius() || ball.getX() > areaWidth - ball.getRadius()) {
				isCollided = true;
				ball.setVx(ball.getVx() * -1);
			}
			if (ball.getY() < ball.getRadius() || ball.getY() > areaHeight - ball.getRadius()) {
				isCollided = true;
				ball.setVy(ball.getVy() * -1);
			}
			else if(!isCollided) {
				ball.setVy(ball.getVy()+deltaT*gravity);
			}
		}
	}
	
	//För alla bollar i listan, lägg dem i myballs med rätt x, y, r.
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

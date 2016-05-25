import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravity;
	private List<BouncingBall> ballList;
	double x,y,r,angle;


	public DummyModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		gravity = -9.82;
		//Skapar listan med bollar och addar dem
		ballList = new LinkedList<BouncingBall>();
		ballList.add(new BouncingBall(1.0,1.0,2.3,1.0,1.0,1.0));
		ballList.add(new BouncingBall(2.0,4.0,2.3,3.0,2.0,2.0));
	}
	



	public void rectToPolar(BouncingBall ball) {
		r= Math.sqrt(ball.getVx()*ball.getVx()+ball.getVy()*ball.getVy());
		angle= Math.atan(y/x);
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

	public void collision() {
		double deltaX, deltaY, collisionDistance, rotAngle, I, R;

		for(int i=0; i<ballList.size(); i++){
			for(int j=0; j<ballList.size(); j++){
				deltaX = ballList.get(i).getX()-ballList.get(j).getX();
				deltaY = ballList.get(i).getY()-ballList.get(j).getY();
				collisionDistance = ballList.get(i).getRadius()+ballList.get(j).getRadius();
				if(deltaX*deltaX + deltaY*deltaY < collisionDistance*collisionDistance) {
					rotAngle=Math.atan(deltaY/deltaX);
					rotate(-rotAngle, ballList.get(i));
					rotate(-rotAngle, ballList.get(j));
					I = ballList.get(i).getMass()*ballList.get(i).getVx()+ballList.get(j).getMass()*ballList.get(j).getVx();
					R = -(ballList.get(j).getVx()-ballList.get(i).getVx());
					ballList.get(i).setVx((I-R*ballList.get(j).getMass()) / (ballList.get(i).getMass()+ballList.get(j).getMass()));
					ballList.get(j).setVx(R+ballList.get(i).getVx());

					rotate(rotAngle, ballList.get(i));
					rotate(rotAngle, ballList.get(j));
				}
			}
		}
	}





	@Override
	public void tick(double deltaT) {
		//collision();
		double r;
		double x;
		double y;
		double vx;
		double vy;
		for(BouncingBall ball : ballList){
			r = ball.getRadius();
			x = ball.getX();
			y = ball.getY();
			vy = ball.getVy();
			vx = ball.getVx();
			if (x < r || x > areaWidth - r) {
				ball.setVx(vx * -1);
			}
			if (y < r || y > areaHeight - r) {
				ball.setVy(vy * -1);
			}
			else {
				ball.setVy(vy+deltaT*gravity);
			}
			ball.setX(x + ball.getVx() * deltaT);
			ball.setY(y + ball.getVy() * deltaT);
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

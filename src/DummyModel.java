import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravity;
	private List<BouncingBall> ballList;

	public DummyModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
<<<<<<< HEAD
		gravity = -9.82;
		//Skapar listan med bollar och addar dem
		ballList = new LinkedList<BouncingBall>();
		ballList.add(new BouncingBall(1.0,1.0,2.3,1.0,1.0,1.0));
	}
	

	@Override
	public void tick(double deltaT) {
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
				vx *= -1;
			}
			if (y < r || y > areaHeight - r) {
				vy *= -1;
			}
			x += vx * deltaT;
			y += vy * deltaT;
=======
		gravity = -9.82;
		//Skapar listan med bollar och addar dem
		ballList = new LinkedList<BouncingBall>();
		ballList.add(new BouncingBall(1.0,1.0,2.3,1.0,1.0));
	}



	@Override
	public void tick(double deltaT) {

		if (x < r || x > areaWidth - r) {
			vx *= -1;
		}
		if (y < r || y > areaHeight - r) {
			vy *= -1;
>>>>>>> origin/master
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

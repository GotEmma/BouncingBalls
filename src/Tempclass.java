/**
 * Created by emmafahlen on 2016-05-25.
 */
public class Tempclass {
    double x,y,r,angle;


    public void rectToPolar() {
        r= Math.sqrt(x*x+y*y);
        angle= Math.atan(y/x);
        if (x<0){
            angle+=Math.PI;
        }
    }

	public void polarToRect() {
		x=r*Math.cos(angle);
		y=r*Math.sin(angle);
	}

	public void rotate(double rotateAngle) {
		rectToPolar();
		angle+=rotateAngle;
		polarToRect();
	}

	public void collision() {
		double deltaX, deltaY;

        deltaX=b1pos.x-b2pos.x;
		deltaY=b1pos.y-b2pos.y;

		collisionDistance=b1radius+b2radius;
		if( deltaX*deltaX + deltaY*deltaY < collisionDistance*collisionDistance) {

			rotAngle=atan(deltaY/deltaX);

			b1vel.rotate(-rotAngle);
			b2vel.rotate(-rotAngle);

			I = b1mass*b1vel.x+b2mass*b2vel.x;
			R = -(b2vel.x-b1vel.x);
			b1vel.x = (I-R*b2mass) / (b1mass+b2mass);
			b2vel.x = R+b1vel.x;

			b1vel.rotate(rotAngle)
			b2vel.rotate(rotAngle);
		}
		*/
}

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
		double deltaX, deltaY, collisionDistance, rotAngle;

        for(int i=0; i<ballList.length(); i++){
            for(int j=0; j<ballList.length(); j++){
                deltaX = ballList.get(i).getX()-ballList.get(j).getX();
                deltaY = ballList.get(i).getY()-ballList.get(j).getY();
                collisionDistance = ballList.get(i).getRadius()+ballList.get(j).getRadius();
                if(deltaX*deltaX + deltaY*deltaY < collisionDistance*collisionDistance) {
                    rotAngle=Math.atan(deltaY/deltaX);
                    b1vel.rotate(-rotAngle);
                    b2vel.rotate(-rotAngle);

                    I = b1mass*b1vel.x+b2mass*b2vel.x;
                    R = -(b2vel.x-b1vel.x);
                    b1vel.x = (I-R*b2mass) / (b1mass+b2mass);
                    b2vel.x = R+b1vel.x;

                    b1vel.rotate(rotAngle)
                    b2vel.rotate(rotAngle);
                }
            }
        }
    }


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

}
import java.awt.Color;
import java.awt.Graphics;

public class Block extends Objects {

	
	boolean isUp = false;
	boolean isDown = false;
	
	Block(int x, int y, int width, int height){
	super (x,y,width,height);
	speed = 5;
	}
	
	void speedPower() {
		speed+=1;
	}
	
	void draw(Graphics g) {
	g.setColor(Color.blue);	
	g.fillRect(x,y,width,height);
	}
	
	void update() {
		super.update();
	}
	
	void boundaries() {
		//keeps you in the screen
		if (y < 0) {
			down();
		}
		if (y> 330) {
			up();
		}
	}
	//movement stuff
	void up() {
		y-= speed;
	}
	
	void down() {
		y+= speed;
	}
	
	
}

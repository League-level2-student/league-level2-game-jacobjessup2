import java.awt.Color;
import java.awt.Graphics;

public class Block extends Objects {

	
	boolean isUp = false;
	boolean isDown = false;
	
	Block(int x, int y, int width, int height){
	super (x,y,width,height);
	speed = 5;
	}
	
	
	
	

	
	
	
	void draw(Graphics g) {
	g.setColor(Color.BLACK);	
	g.fillRect(x,y,width,height);
	}
	
	void update() {
		super.update();
	}
	
	void boundaries() {
		if (y < 0) {
			down();
		}
		if (y> 330) {
			up();
		}
	}
	
	void up() {
		y-= 5;
	}
	
	void down() {
		y+= 5;
	}
	
	
}

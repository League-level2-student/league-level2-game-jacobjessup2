import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Enemies extends Objects {
Random rand = new Random();
	
	Enemies(int x, int y, int width, int height){
		super(x,y,width,height);
		speed = rand.nextInt(5)+5;
	}
	
	void update() {
		x-=speed;
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
	}
}

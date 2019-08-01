import java.awt.Color;
import java.awt.Graphics;

public class PowerUps extends Objects {
Color f = new Color(0, 255, 50);
	PowerUps(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 7;
	}

	
	
	
	void update() {
		x-=speed;
		super.update();
	}
	
	
	
	
	
	
	
	
	
	
	
	void draw(Graphics g) {
		g.setColor(f);
	}
	
	
}

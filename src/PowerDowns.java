import java.awt.Color;
import java.awt.Graphics;

public class PowerDowns extends Objects{
Color DarkGreen = new Color (0, 170, 0);
	PowerDowns(int x, int y, int width, int height) {
		super(x, y, width, height);
		if(Panel.difficulty == Panel.EASY) {
			speed = 5;
		}
		else if(Panel.difficulty == Panel.MEDIUM) {
			speed = 10;
		}
		else if(Panel.difficulty == Panel.IMPOSSIBLE) {
			speed = 15;
		}
	}

	
	void update(){
		x-=speed;
		super.update();
	}
	
	
	
	void draw(Graphics g) {
		g.setColor(DarkGreen);
		g.fillRect(x, y, width, height);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Enemies extends Objects {
	static int enemySpeedDown;
	static int TimeSpeedUp = 0;
	Enemies(int x, int y, int width, int height){
		super(x,y,width,height);
		//difficulty changes speed
		if(Panel.difficulty == Panel.EASY) {
			speed = 5 - enemySpeedDown + TimeSpeedUp;
		}
		else if(Panel.difficulty == Panel.MEDIUM) {
			speed = 10 - enemySpeedDown + TimeSpeedUp;
		}
		else if(Panel.difficulty == Panel.IMPOSSIBLE) {
			speed = 15 - enemySpeedDown + TimeSpeedUp;
		}
		
	}
	
	
	
	void update() {
		//makes the Enemies move
		x-=speed;
		super.update();
	}
	
	void draw(Graphics g) {
		//makes the Enemies show up
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}

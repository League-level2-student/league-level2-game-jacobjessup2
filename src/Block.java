import java.awt.Color;
import java.awt.Graphics;

public class Block {

	int x;
	int y;
	int width;
	int height;
	boolean isUp = false;
	boolean isDown = false;
	
	Block(int x, int y, int width, int height){
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	}
	
	
	
	

	
	
	
	void draw(Graphics g) {
	g.setColor(Color.BLACK);	
	g.fillRect(x,y,width,height);
	
	
	
	
	
	}
	
	void update() {
		
	}
	
	//movement
	void up() {
		y-= 5;
	}
	
	void down() {
		y+= 5;
	}
	
	
}

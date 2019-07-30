import java.awt.Rectangle;

public class Objects {

	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	boolean isActive = true;
	Rectangle collisionBox;
	
	
	Objects(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x,y,width,height);
	}
	
	
	void update(){
		//allows the game to check if you block an Enemy or if they get past you
		collisionBox.setBounds(x, y, width, height);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

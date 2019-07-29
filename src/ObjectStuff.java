import java.util.ArrayList;
import java.util.Random;

public class ObjectStuff {

	Block play;
	ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	Random random = new Random();
	int score = 0;
	
	ObjectStuff(){
		
	}
	
	void addEnemies() {
		enemies.add(new Enemies(Game.GAMEWIDTH, random.nextInt(Game.GAMEHEIGHT-25), 25,25));
	}
	
	
	void update() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			
			if(enemies.get(i).x < 0) {
				play.isActive = false;
			}
		}
		collision();
		purge();
	}
	
	void collision() {
		for (int i = 0; i < enemies.size(); i++) {
			if(play.collisionBox.intersects(enemies.get(i).collisionBox)) {
				enemies.get(i).isActive = false;
				score++;
			}
		}
	}
	
	void purge() {
		for (int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).isActive == false) {
				enemies.remove(i);
			}
		}
	}
	
}

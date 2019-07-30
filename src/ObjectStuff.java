import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectStuff implements ActionListener{

	Block play;
	ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	Random random = new Random();
	int score = 0;
	
	ObjectStuff(Block f){
		f = new Block(75,250,50,50);
		play = f;
	}
	
	void addEnemies() {
		//makes Enemies spawn
		enemies.add(new Enemies(Game.GAMEWIDTH, random.nextInt(Game.GAMEHEIGHT-50), 25,25));
	}
	
	
	void update() {
		//if the enemies get past you it's game over
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			
			if(enemies.get(i).x < 0) {
				play.isActive = false;
			}
			play.update();
		}
		//stuff to remove Enemies if you block them
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
	
	void draw(Graphics g) {
		//calls draw methods to make stuff appear
		play.draw(g);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
	}
	
	
	
	int getScore() {
		//allows the score to show up
		return score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//spawns enemies
		addEnemies();
	}
	
	
	
	
	
	
	
	
	
}

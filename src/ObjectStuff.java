import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectStuff implements ActionListener{

	Block play;
	ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	ArrayList<PowerUps> power = new ArrayList<PowerUps>();
	ArrayList<PowerDowns> powerD = new ArrayList<PowerDowns>();
	Random random = new Random();
	int spawn = 0;
	static int score = 0;
	
	static boolean powerUp = false;
	ObjectStuff(Block f){
		f = new Block(75,250,50,50);
		play = f;
	}
	
	void addEnemies() {
		//makes Enemies spawn
		enemies.add(new Enemies(Game.GAMEWIDTH, random.nextInt(Game.GAMEHEIGHT-50), 25,25));
	}
	
	void addPowerUps() {
		//makes power ups spawn
		power.add(new PowerUps(Game.GAMEWIDTH, random.nextInt(Game.GAMEHEIGHT-50), 25,25));
	}
	
	void addPowerDowns() {
		//makes power downs spawn
		powerD.add(new PowerDowns(Game.GAMEWIDTH, random.nextInt(Game.GAMEHEIGHT-50), 25,25));
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
		for (int i = 0; i < power.size(); i++) {
			power.get(i).update();
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
		for (int i = 0; i < power.size(); i++) {
			if(play.collisionBox.intersects(power.get(i).collisionBox)) {
				power.get(i).isActive = false;
				powerUp = true;
			}
		}
	}
	
	void purge() {
		for (int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).isActive == false) {
				enemies.remove(i);
			}
		}
		for (int i = 0; i < power.size(); i++) {
			if (power.get(i).isActive == false) {
				power.remove(i);
			}
		}
	}
	
	void draw(Graphics g) {
		//calls draw methods to make stuff appear
		play.draw(g);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		for (int i = 0; i < power.size(); i++) {
			power.get(i).draw(g);
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
		spawn = random.nextInt(4);
		if (spawn == 0) {
			addPowerUps();
		}
		if (spawn == 1) {
			addPowerDowns();
		}
		else {
			addEnemies();
		}
	}
	
	
	
	
	
	
	
	
	
}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel implements ActionListener, KeyListener {
	//screen states
	static final int MENU = 0;
	static final int GAME = 1;
	static final int END = 2;
	int currentState = 0;
	//difficulty stuff
	static final int EASY = 0;
	static final int MEDIUM = 1;
	static final int IMPOSSIBLE = 2;
	static int difficulty = 0;
	//text sizes
	Font TitleFont = new Font("Arial", Font.PLAIN, 48);
	Font TextFont = new Font("Arial", Font.PLAIN, 20);
	Font GameFont = new Font("Arial",Font.PLAIN, 15);
	
	Timer frameDraw;
	Timer enemySpawn;
	Block player;
	Random rando = new Random();
	//power ups
	int pow = 0;
	
	int speedUp = 0;
	int speedCap = 0;
	
	int speedDown = 0;
	int enemyDownCap = 0;
	
	//power downs
	int debf = 0;
	
	int slowUp = 0;
	int slowCap = 0;
	
	int enemyUp = 0;
	int enemyUpCap = 0;
	
	
	
	ObjectStuff u = new ObjectStuff(player);
	
	
	Panel(){
		//updates the screen
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
	}

	
	void start() {
		//spawns Enemies
		enemySpawn = new Timer(1000, u);
		enemySpawn.start();
		ObjectStuff.score = 0;
		speedUp = 0;
		speedCap = 0;
		slowUp = 0;
		slowCap = 0;
		enemyUp = 0;
		enemyUpCap = 0;
		speedDown = 0;
		enemyDownCap = 0;
		Enemies.enemySpeedDown = 0;
		Enemies.TimeSpeedUp = 0;
		Enemies.enemySpeedUp = 0;
		OverTimeSpeed();
		
	}
	
	
	void OverTimeSpeed (){
		if (ObjectStuff.score == 40) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 80) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 120) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 160) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 200) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 240) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 280) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
		if (ObjectStuff.score == 320) {
			Enemies.TimeSpeedUp++;
			System.out.println("enemy speed up");
		}
	}
	
	
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		}
		
		else if(currentState == GAME) {
			drawGameState(g);
		}
		
		else if(currentState == END) {
			drawEndState(g);
		}
		
	}
	
	void drawMenuState(Graphics g){
		//menu stuff
		g.setColor(Color.blue);
		g.fillRect(0, 0, 900, Game.GAMEHEIGHT);
		g.setFont(TitleFont);
		g.setColor(Color.white);
		g.drawString("Gravity Catcher", 225, 75);
		g.setFont(TextFont);
		g.drawString("Press ENTER to start the game", 250, 140);
		g.drawString("Use the arrow keys to move up and down", 210, 205);
		g.drawString("Press SHIFT to see what the different colored objects do", 143, 270);
		g.drawString("Press the SPACEBAR to change the difficulty", 195, 335);
	}


public
	void drawGameState(Graphics g) {
		//game stuff
		g.setColor(Color.white);
		g.fillRect(0, 0, 900, Game.GAMEHEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(GameFont);
		g.drawString("" + u.score, Game.GAMEWIDTH-60, 25);
		u.draw(g);
		u.play.boundaries();
	}
	
	void drawEndState(Graphics g) {
		//game over screen
		g.setColor(Color.black);
		g.fillRect(0, 0, 900, Game.GAMEHEIGHT);
		g.setColor(Color.white);
		g.setFont(TitleFont);
		g.drawString("GAME OVER", 250, 75);
		g.setFont(TextFont);
		g.drawString("You saved " + u.getScore() + " People from falling", 245, 170);
		g.drawString("The High Score is: " + u.highScore, 300, 230);
		g.drawString("Press ENTER to restart", 290, 300);
		g.drawString("The Difficulty was: " + endStateDifficulty(), 270, 360);
	}
	
	String endStateDifficulty() {
		if(difficulty == EASY) {
			return "Easy";
		}
		if(difficulty == MEDIUM) {
			return "MEDIUM";
		}
		if(difficulty == IMPOSSIBLE) {
			return "Very Hard";
		}	
			
		else {
			return null;
		}
	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		u.update();
		//game over
		if (u.play.isActive == false) {
			currentState = END;
		}
		//movement
		if (u.play.isUp) {
			u.play.up();
		}
		if (u.play.isDown) {
			u.play.down();
		}
		//power ups
		if(u.powerUp == true) {
			boost();
			u.powerUp = false;
		}
		//power downs
		if(u.powerDown == true) {
			unBoost();
			u.powerDown = false;
		}
	}
	
	void updateEndState() {
		if(u.score > u.highScore) {
			u.highScore = u.score;
		}
	}
	
	void unBoost(){
		debf = rando.nextInt(2)+1;
		System.out.println("power down");
		
		//speed of player decreases
		
		if (debf == 1) {
			slowUp++;
			System.out.println("speed down player");
			if(slowCap <= 5) {
				if (slowUp == 1) {
					u.play.speedDown();
					slowUp = 0;
					System.out.println("speed -1");
				}
			}
			else {
				System.out.println("player speed down cap reached");
			}
		}
		
		
		if (debf == 2) {
			enemyUp++;
			System.out.println("speed up enemy");
			if(enemyUpCap <= 5) {
				if (enemyUp == 1) {
					Enemies.enemySpeedUp++;
					enemyUp = 0;
					System.out.println("enemy speed +1");
				}
			}
			else {
				System.out.println("enemy speed at max");
			}
		}
		
		//speed of enemies increase
	
			
	}
	
	void boost() {
		pow = rando.nextInt(2)+1;
		System.out.println("power up");
		
		//speed of player increase
		if(pow == 1) {
			speedUp++;
			System.out.println("speed up player");
			pow = 0;
			if(speedCap <= 5) {
				if(speedUp == 1) {
					u.play.speedPower();
					speedUp = 0;
					System.out.println("speed +1");
					speedCap++;
				}
			}
			else {
				System.out.println("speed cap reached on player");
			}
			
		}
		//speed of enemies decrease
		if(pow == 2) {
			speedDown++;
			System.out.println("speed down enemies");
			//if the difficulty is EASY
			if(difficulty == EASY) {
				if(enemyDownCap <=2) {
					if(speedDown == 1) {
						Enemies.enemySpeedDown++;
						enemyDownCap++;
						System.out.println("enemy speed -1");
						speedDown = 0;
					}
				}
				else {
					System.out.println("enemy speed down cap reached");
				}
			}
			//if the difficulty is MEDIUM
			if(difficulty == MEDIUM) {
				if(enemyDownCap <=3) {
					if(speedDown == 1) {
						Enemies.enemySpeedDown++;
						enemyDownCap++;
						System.out.println("enemy speed -1");
						speedDown = 0;
					}
				}
				else {
					System.out.println("enemy speed down cap reached");
				}
			}
			//if the difficulty is VERY HARD
			if(difficulty == IMPOSSIBLE) {
				if(enemyDownCap <=6) {
					if(speedDown == 1) {
						Enemies.enemySpeedDown++;
						enemyDownCap++;
						System.out.println("enemy speed -1");
						speedDown = 0;
					}
				}
				else {
					System.out.println("enemy speed down cap reached");
				}
			}
		}
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_ENTER) {
			if(currentState == END) {
				difficulty = EASY;
				currentState = MENU;
			}
			else{
				currentState++;
			}
			if(currentState == GAME) {
				u.play = new Block(75,250,50,50);
				u.play.isActive = true;
				u = new ObjectStuff(player);
				start();
			}
		}
		if(currentState == MENU) {
			
			if(e.getKeyCode() == e.VK_SHIFT) {
				JOptionPane.showMessageDialog(null, "Dr. Uptanogud has turned gravity sideways for you and made all the floors of a really tall building disappear. It's your job to save the falling people.");
				JOptionPane.showMessageDialog(null, "Blue objects are people, if they get past you it's game over.");
				JOptionPane.showMessageDialog(null, "Green objects are power-ups, they can make you faster or the enemies slower.");
				JOptionPane.showMessageDialog(null, "Be careful of the fake power-ups, they look slightly darker than power-ups but can slow you down or speed up enemies.");
				JOptionPane.showMessageDialog(null, "Over time the enemies will get faster.");
			}
			
			if(e.getKeyCode() == e.VK_SPACE) {
				if(difficulty == IMPOSSIBLE) {
					difficulty = EASY;
				}
				else{
					difficulty++;
				}
			
				if(difficulty == EASY) {
					JOptionPane.showMessageDialog(null, "The difficulty has been set to EASY");
				}
			
				else if(difficulty == MEDIUM) {
					JOptionPane.showMessageDialog(null, "The difficulty has been set to MEDIUM");
				}
			
				else if(difficulty == IMPOSSIBLE) {
					JOptionPane.showMessageDialog(null, "The difficulty has been set to VERY HARD, good luck");
				}
			
			}
		}
		
		if(e.getKeyCode() == e.VK_UP) {
			u.play.isUp = true;
		}
		
		if(e.getKeyCode() == e.VK_DOWN) {
			u.play.isDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_UP) {
			u.play.isUp = false;
		}
		
		if(e.getKeyCode() == e.VK_DOWN) {
			u.play.isDown = false;
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
			updateMenuState();
		}
		if(currentState == GAME){
			updateGameState();
		}
		if(currentState == END){
			updateEndState();
		}
		repaint();
	}
	
	
	
	
	
}

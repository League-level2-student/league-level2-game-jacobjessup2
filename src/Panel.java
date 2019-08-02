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
	
	int sizeUp = 0;
	
	int speedDown = 0;
	int enemyDownCap = 0;
	
	
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
		speedUp = 0;
		speedCap = 0;
		sizeUp = 0;
		speedDown = 0;
		enemyDownCap = 0;
		
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
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		g.setFont(TitleFont);
		g.setColor(Color.white);
		g.drawString("Blocker", 305, 75);
		g.setFont(TextFont);
		g.drawString("Press ENTER to start the game", 250, 140);
		g.drawString("Use the arrow keys to move up and down", 210, 205);
		g.drawString("Stop the Enemies from getting past you", 210, 270);
		g.drawString("Press the SPACEBAR to change the difficulty", 195, 335);
	}
	
	void drawGameState(Graphics g) {
		//game stuff
		g.setColor(Color.white);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(GameFont);
		g.drawString("" + u.score, Game.GAMEWIDTH-60, 25);
		u.draw(g);
		u.play.boundaries();
	}
	
	void drawEndState(Graphics g) {
		//game over screen
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		g.setColor(Color.white);
		g.setFont(TitleFont);
		g.drawString("GAME OVER", 250, 75);
		g.setFont(TextFont);
		g.drawString("You stopped " + u.getScore() + " Enemies from getting past you", 190, 170);
		g.drawString("Press ENTER to restart", 287, 250);
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
	}
	
	
	void boost() {
		pow = rando.nextInt(3)+1;
		System.out.println("power up");
		//size of player increase
		if(pow == 1) {
			System.out.println("size up player");
		}
		//speed of player increase
		if(pow == 2) {
			speedUp++;
			System.out.println("speed up player");
			pow = 0;
			if(speedCap <= 5) {
				if(speedUp == 3) {
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
		if(pow == 3) {
			speedDown++;
			System.out.println("speed down enemies");
			//if the difficulty is EASY
			if(difficulty == EASY) {
				if(enemyDownCap <=2) {
					if(speedDown == 3) {
						Enemies.enemySpeedDown++;
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
					if(speedDown == 3) {
						Enemies.enemySpeedDown++;
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
					if(speedDown == 3) {
						Enemies.enemySpeedDown++;
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
		 if(currentState == GAME){
		    updateGameState();
		}
		repaint();
	}
	
	
	
	
	
}

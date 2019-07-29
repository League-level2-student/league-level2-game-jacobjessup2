import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener, KeyListener {
	
	static final int MENU = 0;
	static final int GAME = 1;
	static final int END = 2;
	int currentState = 0;
	Font TitleFont = new Font("Arial", Font.PLAIN, 48);
	Font TextFont = new Font("Arial", Font.PLAIN, 20);
	Timer frameDraw;
	Block player = new Block(50,250,50,50);
	
	
	Panel(){
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
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
		g.setColor(Color.blue);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		g.setFont(TitleFont);
		g.setColor(Color.white);
		//TODO think of name
		g.drawString("Game", 325, 75);
		g.setFont(TextFont);
		g.drawString("Press ENTER to start the game.", 260, 150);
		g.drawString("Use the arrow keys to move up and down.", 215, 220);
		g.drawString("Stop the other objects from getting past you.", 206, 290);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		player.draw(g);
		player.boundaries();
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		g.setColor(Color.white);
		g.setFont(TitleFont);
		g.drawString("GAME OVER", 250, 75);
	}
	
	
	
	void updateMenuState(){
		
	}
	
	void updateGameState() {
		
		if (player.isUp) {
			player.up();
		}
		if (player.isDown) {
			player.down();
		}
		
	}
	
	void updateEndState() {}
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_ENTER) {
			if(currentState == END) {
				currentState = MENU;
			}
			else{
				currentState++;
			}
		}
		
		
		if(e.getKeyCode() == e.VK_UP) {
			player.isUp = true;
		}
		
		if(e.getKeyCode() == e.VK_DOWN) {
			player.isDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_UP) {
			player.isUp = false;
		}
		
		if(e.getKeyCode() == e.VK_DOWN) {
			player.isDown = false;
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		repaint();
	}
	
	
	
	
	
}

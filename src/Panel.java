import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener, KeyListener {
	
	static final int MENU = 0;
	static final int GAME = 1;
	static final int END = 2;
	int currentState = 0;
	Font TitleFont = new Font("Arial", Font.PLAIN, 48);
	Font TextFont = new Font("Arial", Font.PLAIN, 20);
	
	
	
	Panel(){
		
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
		repaint();
	}
	
	void drawMenuState(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
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
				currentState = MENU;
			}
			else{
				currentState++;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}

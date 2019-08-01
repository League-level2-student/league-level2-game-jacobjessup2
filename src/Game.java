import javax.swing.JFrame;

public class Game {
JFrame frame = new JFrame();
	//size of the screen
	static final int GAMEWIDTH = 800;
	static final int GAMEHEIGHT = 400;
	
	Panel panel = new Panel();
	
	
	public static void main(String[] args) {
		Game g = new Game();
		g.setup();
	}
	
	void setup() {
		//basic stuff
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setSize(GAMEWIDTH, GAMEHEIGHT);
	}
	
	
	
	
	
	
	
	
	
}

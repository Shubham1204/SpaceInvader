import javax.swing.JFrame;

public class GameFrame extends JFrame implements GameConstants {
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GWIDTH,GHEIGHT);
		setTitle(TITLE);
		setLocationRelativeTo(null);
		Board board = new Board();
		add(board);
		setVisible(true);
	}

	public static void main(String []args)
	{
		GameFrame frame = new GameFrame();
		
	}
}

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame p = new JFrame();
		GameUI g = new GameUI();
		p.setBounds(10, 10, 700, 600);
		p.setTitle("Brick Breaker");
		p.setResizable(false);
		p.add(g);
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

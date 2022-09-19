
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Random;
import java.util.function.IntBinaryOperator;

import javax.swing.Timer;

public class GameUI extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int score = 0;

	private int totalBricks = 21;

	private Timer timer;
	private int delay = 8;
	private int xBar = 310;
	Random rand = new Random();
	private int n = (int) (rand.nextInt(2 + 2 - 2) - 2);

	private int positioningX = 120;
	private int positioningY = 350;
	private int ballXdir = n;
	private int ballYdir = -2;
	Color x = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	Color y = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	private BrickMap map;

	public GameUI() {
		map = new BrickMap(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {

		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won!", 260, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press 'Enter' for the next level", 220, 350);
		}

		if (positioningY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over! Score: " + score, 190, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press 'R' to Restart", 260, 350);
		}
		// background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);

		// drawing map
		map.draw((Graphics2D) g);
		// border color
		g.setColor(Color.red);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);

		// scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);
		// paddle
		g.setColor(x);
		g.fillRect(xBar, 550, 100, 8);

		// ball
		g.setColor(y);
		g.fillOval(positioningX, positioningY, 20, 20);

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		if (play) {
			if (new Rectangle(positioningX, positioningY, 20, 20).intersects(new Rectangle(xBar, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.bW + 80;
						int brickY = i * map.bH + 50;
						int brickWidth = map.bW;
						int brickHeight = map.bH;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(positioningX, positioningY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;

							if (positioningX + 19 <= brickRect.x || positioningX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}
			positioningX += ballXdir;
			positioningY += ballYdir;

			if (positioningX < 0)
				ballXdir = -ballXdir;
			if (positioningY < 0)
				ballYdir = -ballYdir;
			if (positioningX > 670)
				ballXdir = -ballXdir;

		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (xBar >= 600) {
				xBar = 600;
			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (xBar < 10) {
				xBar = 10;
			} else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			if (!play) {
				play = true;
				positioningX = 120;
				positioningY = 350;
				ballXdir = n;
				ballYdir = -2;
				xBar = 310;
				score = 0;
				totalBricks = 21;
				map = new BrickMap(3, 7);
				repaint();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				positioningX = 120;
				positioningY = 350;
				ballXdir = n;
				ballYdir = -2;
				xBar = 310;
				score = 0;
				delay -= 2;
				totalBricks = 21;
				timer = new Timer(delay, this);
				map = new BrickMap(3, 7);
				repaint();
			}
		}

	}

	public void moveRight() {
		play = true;
		xBar += 40;
	}

	public void moveLeft() {
		play = true;
		xBar -= 40;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

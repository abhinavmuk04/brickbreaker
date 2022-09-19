import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

public class BrickMap {
	public int map[][];
	Random rand = new Random();
	public int bW;
	public int bH;

	public BrickMap(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}

		bW = 540 / col;
		bH = 150 / row;
	}
	Color x = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	Color y = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	public void draw(Graphics2D g) {
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					g.setColor(y);
					g.fillRect(j * bW + 80, i * bH + 50, bW, bH);

					g.setStroke(new BasicStroke(3));
					g.setColor(x);
					g.drawRect(j * bW + 80, i * bH + 50, bW, bH);
				}
			}
		}
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}

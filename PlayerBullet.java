import java.awt.Color;
import java.awt.Graphics;

public class PlayerBullet extends Sprite implements GameConstants{
	boolean isVisible;
	public PlayerBullet(int x, int y) {
		w = 3;
		h =8;
		this.x = x;
		this.y = y;
		this.speed = 10;
		this.isVisible = true;
	}
	
	public void move() {
		y-= speed;
		outOfScreen();
	}
	
	public void outOfScreen() {
		if(y<=0) {
			isVisible = false;
		}
	}
	
	public void drawPlayerBullet(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, w, h);
		move();
	}

}

import java.awt.Color;
import java.awt.Graphics;

public class AlienBullet extends Sprite implements GameConstants {

	boolean isVisible;
	public AlienBullet(int x,int y,int speed) {
		w=3;
		h=6;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.isVisible = true;
	}
	
	public void move() {
		y+=speed;
		outOfScreen();
	}
	
	public void outOfScreen() {
		if(y<=GHEIGHT) {
			isVisible = false;
		}
	}
	
	public void drawAlienBullet(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x,y,w,h);
		move();
	}
}

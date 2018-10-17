import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player extends Sprite implements GameConstants{
	
	boolean isDead;
	public Player() {
		x = 30;
		w = h =40;
		y = FLOOR - h;
		speed = 0;
		image = new ImageIcon(Player.class.getResource(PLAYER_IMAGE)).getImage();
	}
	
	public void drawPlayer(Graphics g) {
		g.drawImage(image, x, y, w, h, null);
		move();
	}
	public void stopMovement() {
		if(x>=(GWIDTH-w-10)) {
			setX(GWIDTH-w-10);
		}
		else 
			if(x<=0) {
				setX(0);
			}
			else if(y>=(GHEIGHT-h-25)) {
				setY(GHEIGHT-h-25);
			}
	}
	public void move() {
		x+= speed; 
		stopMovement();
	}
}

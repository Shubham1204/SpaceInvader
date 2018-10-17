import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Alien extends Sprite implements GameConstants{

	boolean isDead;
	public Alien(int x) {
		this.x=x;
		y = 40;
		//this.y=y;
		w = 30;
		h =20;
		speed = 2;
	
		this.image = new ImageIcon(Alien.class.getResource(ALIEN_IMAGE)).getImage();
		this.image11 = new ImageIcon(Alien.class.getResource(ALIEN_IMAGE1CPY)).getImage();
		this.image2 = new ImageIcon(Alien.class.getResource(ALIEN_IMAGE2)).getImage();
		this.image3 = new ImageIcon(Alien.class.getResource(ALIEN_IMAGE3)).getImage();
	}
	
	//int timepass = 0;
	
	public void drawAlien(Graphics g) {
	//	if(changeSprite == 1) {
		g.drawImage(image3, x, y, w, h, null);
		g.drawImage(image2, x, (y+40), w, h, null);
		g.drawImage(image2, x, (y+80), w, h, null);
		g.drawImage(image, x, (y+120), w, h, null);
		g.drawImage(image, x, (y+160), w, h, null);
		move();
//		changeSprite = 2;
//		
//		}
//		else if(changeSprite == 2) {
//			timepass+=1;
//			if(timepass >= 2) {
//			g.drawImage(image3, x, y, w, h, null);
//			g.drawImage(image2, x, (y+40), w, h, null);
//			g.drawImage(image2, x, (y+80), w, h, null);
//			g.drawImage(image3, x, (y+120), w, h, null);
//			g.drawImage(image3, x, (y+160), w, h, null);
//			move();
//			changeSprite = 1;
//			timepass = 0;
//			}
//		}
	}
//	
//	
//	public void drawAlien2(Graphics g) {
//		g.drawImage(image, x, y, w, h, null);
//		g.drawImage(image2, x, (y+40), w, h, null);
//		g.drawImage(image3, x, (y+80), w, h, null);
//		move();
//	} 
			
	//int changeSprite = 1;
	public void move() {
		x+=speed;
		changeDirection();
	}
	
	public void changeDirection() {	
		if(x>=(GWIDTH-w-10)) {
			
			speed*=-1;
			y+=20;
		}
		else 
			if(x<=0) {
				speed*=-1;
				y+=20;
			}
	}
}

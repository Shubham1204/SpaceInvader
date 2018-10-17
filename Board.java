import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements GameConstants {
	
	private Player player;
	private Timer timer;
	private ArrayList<PlayerBullet> playerBullets = new ArrayList<>();
	private ArrayList<AlienBullet> alienbullets = new ArrayList<>();
	private static RandomGenerator randomalien = new RandomGenerator(MAX_ALIENS);
	private Alien aliens[] = new Alien[MAX_ALIENS];
	
	private void loadaliens() {
	//	int y = GHEIGHT/2-100;
		//int speed = 1;
		int x = 50;
	//int y = 200;
		for(int i=0;i<aliens.length;i++) {
			aliens[i] = new Alien(x);
			x+=40;
			//y+=40;
			//speed+=1;
		}
		
	}
	
	private void drawAliens(Graphics g) {
		for(Alien alien : aliens) {
			if(!alien.isDead) {
				alien.drawAlien(g);
				
			}
		}
	}
	
	private boolean isCollide(Player player, Alien alien) {
		
		int maxWidth = Math.max(player.w,alien.w);
		int maxHeight = Math.max(player.h,alien.h);
		int xDistance = Math.abs(player.x - alien.x);
		int yDistance = Math.abs(player.y - alien.y);
		return xDistance<=maxWidth-50 && yDistance<=maxHeight-100;
	}
	
private boolean isCollide(AlienBullet alienBullet,Player player) {
		
		int maxWidth = Math.max(player.w,alienBullet.w);
		int maxHeight = Math.max(player.h,alienBullet.h);
		int xDistance = Math.abs(player.x - alienBullet.x);
		int yDistance = Math.abs(player.y - alienBullet.y);
		return xDistance<=maxWidth && yDistance<=maxHeight;
	}
	
	private void checkCollision(Graphics g) {
		for(Alien alien : aliens) {
			if(isCollide(player,alien)) {
				if(alien.isDead==false) {
				gameOver(g);
				return ;
				}
			}
		}
	}
	
	private void checkCollisionAlienBullet(Graphics g) {
		//	for(Spider spider : aliens) {
		for(AlienBullet alienBullet:alienbullets) {
				if(isCollide(alienBullet, player)) {
					if(player.isDead==true) {
					gameOver(g);
					return ;
					}
				}
			//	}
			}
		}
		
	
	private void gameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Aerial",Font.BOLD,32));
		g.drawString("GAME OVER", GWIDTH/2, GHEIGHT/2);
		timer.stop();
	}
	
	public Board() {
		setBackground(Color.BLACK);
		setSize(GWIDTH, GHEIGHT);
		
		player = new Player();
		loadaliens();
		gameLoop();
		setFocusable(true);
		
		bindEvents();
	}
	private void gameLoop() {
		timer = new Timer(DELAY,(e)-> {
			repaint();
		});
		timer.start();
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				player.setSpeed(0);
			}
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_LEFT) {
					player.setSpeed(-5);
				}
				else
					if(keyCode ==KeyEvent.VK_RIGHT ) {
						player.setSpeed(5);
					}
					else
						if(keyCode == KeyEvent.VK_UP) {
							
						}
						else
							if(keyCode == KeyEvent.VK_SPACE) {
								int playerBulletX  = player.getX()+(player.getW()/2);
								int playerBulletY = player.getY()+(player.getH()/2);
								PlayerBullet playerBullet = new PlayerBullet(playerBulletX,playerBulletY);
								playerBullets.add(playerBullet);
							
//								//
//								for(int i = 0;i<aliens.length; i++) {
//								int alienbulletX = aliens[i].getX()+(aliens[i].getW()/2);
//								int alienbulletY  = aliens[i].getY() + (aliens[i].getH()/2);
//								AlienBullet alienbullet = new AlienBullet(alienbulletX,alienbulletY);
//								alienbullets.add(alienbullet);
//								}
//								//
							
							}
			}
		});
	}
	
	private boolean isCollideplayerBulletAlien(PlayerBullet playerBullet , Alien alien) {
		int maxW = Math.max(alien.getW(), playerBullet.getW());
		int maxH = Math.max(alien.getH(), playerBullet.getH());
		int xDistance = Math.abs(alien.getX()-playerBullet.getX());
		int yDistance = Math.abs(alien.getY()-playerBullet.getY());
		return xDistance<=(maxW-20) && yDistance<=maxH-20;
	}
	
	private boolean isCollideBulletPlayer(AlienBullet alienbullet , Player player) {
		int maxW = Math.max(player.getW(), alienbullet.getW());
		int maxH = Math.max(player.getH(), alienbullet.getH());
		int xDistance = Math.abs(player.getX()-alienbullet.getX());
		int yDistance = Math.abs(player.getY()-alienbullet.getY());
		return xDistance<=(maxW-20) && yDistance<=(maxH-20);
	}
	
	
	private void checkplayerBulletAlienCollision() {
		for(PlayerBullet playerBullet : playerBullets) {
			for(Alien alien :aliens) {
				if(isCollideplayerBulletAlien(playerBullet, alien)) {
					alien.isDead = true;
				}
			}
		}
	}
	
	private void checkBulletPlayerCollision(Graphics g) {
		for(AlienBullet alienbullet : alienbullets) {
			//for(Player spider:aliens) {
				if(isCollideBulletPlayer(alienbullet, player)) {
					player.isDead = true;
					gameOver(g);
			//	}
			}
		}
	}
	
//	private void drawBackground(Graphics g) {
//		
//		
//		Image bg = new ImageIcon(Board.class.getResource(BACKGROUND_IMAGE)).getImage();
//		g.drawImage(bg, 0, 0, GWIDTH, GHEIGHT, null);
//	}
	
	public void drawPlayerBullets(Graphics g) {
		for(PlayerBullet playerBullet : playerBullets) {
			if(playerBullet.isVisible){
			playerBullet.drawPlayerBullet(g);
		}
	}
}
	private int timewaste = 0;
	private void drawAlienBullets(Graphics g) {
		for(AlienBullet alienbullet : alienbullets) {
			//if(alienbullet.isVisible) {
				alienbullet.drawAlienBullet(g);
			}
		
	//
		if(timewaste==30) {
			int spiderrandom = randomalien.getRandom();
	//for(int i = 0;i<aliens.length; i++) {
	int alienbulletX = aliens[spiderrandom].getX()+(aliens[spiderrandom].getW()/2);
	int alienbulletY  = aliens[spiderrandom].getY() + (aliens[spiderrandom].getH()/2);
	int alienspeed = aliens[spiderrandom].getSpeed()+5;
	AlienBullet alienbullet = new AlienBullet(alienbulletX,alienbulletY,alienspeed);
	alienbullets.add(alienbullet);
	timewaste=0; 
		}
		else {
			timewaste++;
		}
	}
	//

	//}

	
	@Override
	public void paintComponent(Graphics g) {
//		drawBackground(g);
		super.paintComponent(g);
		player.drawPlayer(g);
		drawPlayerBullets(g);
		drawAlienBullets(g);
		drawAliens(g);
		checkCollision(g);
		checkCollisionAlienBullet(g);
		checkplayerBulletAlienCollision();
		checkBulletPlayerCollision(g);
	}
}

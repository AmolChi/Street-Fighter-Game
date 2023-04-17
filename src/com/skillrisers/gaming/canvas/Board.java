package com.skillrisers.gaming.canvas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.skillrisers.gaming.sprites.KenPlayer;
import com.skillrisers.gaming.sprites.Power;
import com.skillrisers.gaming.sprites.PowerEffect;
import com.skillrisers.gaming.sprites.RyuPlayer;
import com.skillrisers.gaming.sprites.Sprite;
import com.skillrisers.gaming.utils.GameConstants;
import com.skillrisers.gaming.utils.PlayerConstants;



public class Board extends JPanel implements GameConstants, PlayerConstants {
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	private Timer timer;
	private Power ryuPower;
	private Power kenPower;
	private boolean isGameOver = false;
	private boolean kenPowerCollision = false;
	private boolean ryuPowerCollision = false;
	private void loadPower() {
		ryuPower = new Power(20,"RYU");
		kenPower = new Power(GWIDTH/2 + 150,"KEN");
	}
	
	private void paintPower(Graphics pen) {
		ryuPower.printBox(pen);
		kenPower.printBox(pen);
	}
	
	
	public Board() throws IOException  {
		
		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
		
	}
	
	private void gameLoop() {
		// Thread Trigger
		timer = new Timer(125, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				ryuPlayer.fall();
				kenPlayer.fall();
				if(kenPower.getHealth()<=0 || ryuPower.getHealth()<=0)
					isGameOver = true;
				collision();
				// TODO Auto-generated method stub
				
			}
		});
		timer.start();
		
	}
	
	public void collision() {
		if(isCollide()) {
			ryuPlayer.setCollide(true);
			kenPlayer.setCollide(true);
			if(ryuPlayer.isAttacking()) {
				kenPlayer.setCurrentMove(DAMAGE);
				kenPower.setHealth((int)(MAX_HEALTH*0.01));
				ryuPlayer.setAttacking(false);
			}

			if(kenPlayer.isAttacking()) {
				ryuPlayer.setCurrentMove(DAMAGE);
				ryuPower.setHealth((int)(MAX_HEALTH*0.01));
				kenPlayer.setAttacking(false);
			}
			
			
				
		}
		else {
			ryuPlayer.setCollide(false);
			ryuPlayer.setSpeed(SPEED);
			kenPlayer.setCollide(false);
			kenPlayer.setSpeed(SPEED);
		}
	}
	
	private void printPowerKen(Graphics pen) {
		for(PowerEffect power: kenPlayer.getPowers()) {
			power.printPower(pen);
			if(isPowerCollide(ryuPlayer,power)) {
				ryuPlayer.setCurrentMove(DAMAGE);
				ryuPower.setHealth(100);
				ryuPowerCollision = true;
				kenPlayer.emptyPower();
				break;
			}
		}
	}
	
	private void printPowerRyu(Graphics pen) {
		for(PowerEffect power: ryuPlayer.getPowers()) {
			power.printPower(pen);
			if(isPowerCollide(kenPlayer,power)) {
				kenPlayer.setCurrentMove(DAMAGE);
				kenPower.setHealth(100);
				kenPowerCollision = true;
				ryuPlayer.emptyPower();
				break;
			}
		}
		
	}
	
	private void printMessage(Graphics pen) {
		timer.stop();	
		if(ryuPower.getHealth()<=0)
			JOptionPane.showMessageDialog(null, "KEN WINS");
		else
			JOptionPane.showMessageDialog(null,"RYU WINS");
		System.exit(0);
	}
	
	private boolean isCollide() {
		int xDistance = Math.abs(ryuPlayer.getX()-kenPlayer.getX());
		int yDistance = Math.abs(ryuPlayer.getY() - kenPlayer.getY());
		
		int maxW = Math.max(ryuPlayer.getW(), kenPlayer.getW());
		int maxH = Math.max(ryuPlayer.getH(), kenPlayer.getH());
		
		return xDistance<=maxW-40 && yDistance<=maxH;
	}
	
	private boolean isPowerCollide(Sprite player,PowerEffect power) {
		int xDistance = Math.abs(player.getX() - power.getX());
		int yDistance = Math.abs(player.getY() - power.getY());
		int maxW = Math.max(player.getW(),power.getW());
		int maxH = Math.max(player.getH(), power.getH());
		return xDistance<=maxW-40 && yDistance<=maxH;
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				ryuPlayer.setSpeed(0);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					ryuPlayer.setSpeed(-SPEED);
					ryuPlayer.setCurrentMove(WALK);
					ryuPlayer.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_D) {
					if(ryuPlayer.isCollide())
						ryuPlayer.setSpeed(0);
					else {
						ryuPlayer.setSpeed(SPEED);
						ryuPlayer.setCurrentMove(WALK);
						ryuPlayer.move();
					}
					
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_O) {
					ryuPlayer.setAttacking(true);
					ryuPlayer.setCurrentMove(KICK);
				}
				else if(e.getKeyCode() == KeyEvent.VK_P) {
					ryuPlayer.setAttacking(true);
					ryuPlayer.setCurrentMove(PUNCH);
				}
				else if(e.getKeyCode() == KeyEvent.VK_W) {
					ryuPlayer.jump();
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP) {
					kenPlayer.jump();
				}
				else if(e.getKeyCode() == KeyEvent.VK_E) {
					kenPowerCollision = false;
					ryuPlayer.setCurrentMove(POWEREFFECT);
					ryuPlayer.showPower();
				}
				// Ken 
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(kenPlayer.isCollide()) {
						kenPlayer.setSpeed(0);
						return;
					}
					kenPlayer.setSpeed(-SPEED);
					kenPlayer.setCurrentMove(WALK);
					kenPlayer.move();
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					kenPlayer.setSpeed(SPEED);
					kenPlayer.setCurrentMove(WALK);
					kenPlayer.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_M) {
					kenPlayer.setAttacking(true);
					kenPlayer.setCurrentMove(PUNCH);
				}
				else if(e.getKeyCode() == KeyEvent.VK_N) {
					kenPlayer.setAttacking(true);
					kenPlayer.setCurrentMove(KICK);
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_B) {
					ryuPowerCollision = false;
					kenPlayer.setCurrentMove(POWEREFFECT);
					kenPlayer.showPower();
				}
				
				
			}
		});
	}
	
	
	
	@Override
	public void paintComponent(Graphics pen) {
		// Rendering / Painting
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		if(ryuPowerCollision == false)
			printPowerKen(pen);

		if(kenPowerCollision == false)
			printPowerRyu(pen);
		
		paintPower(pen);
		if(isGameOver)
			printMessage(pen);
	}

	
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(imageBg,0,0, 1400,900, null);
	}
	
	
	
	private void loadBackgroundImage() {
		try {
		imageBg = ImageIO.read(Board.class.getResource("bg.jpeg"));
		}
		catch(Exception ex) {
			System.out.println("Background Image Loading Fail...");
			System.exit(0);
		
		}
	}
}

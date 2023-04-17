package com.skillrisers.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RyuPlayer extends Sprite  {
	private BufferedImage walkImages [] = new BufferedImage[6];

	private BufferedImage standingImages [] = new BufferedImage[8];
	private BufferedImage kickImages [] = new BufferedImage[6];
	private BufferedImage punchImages [] = new BufferedImage[6];
	private BufferedImage powerImages[] = new BufferedImage[8];
	private BufferedImage damageImages[] = new BufferedImage[2];
	private ArrayList<PowerEffect> power = new ArrayList<>();
	
	public ArrayList<PowerEffect> getPowers(){
		return power;
	}
	public RyuPlayer() throws IOException {
		x = 100;
		h = 300;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		currentMove = STANDING;
		image = ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
		loadWalkImages();
		loadStandingImages();
		loadKickImages();
		loadPunchImages();
		loadPowerImages();
		loadDamageImages();
	}
	
	
	public void jump() {
		if(!isJump) {
		force = DEFAULT_FORCE;
		y = y + force;
		isJump = true;
		}
	}
	
	public void fall() {
		if(y>= (FLOOR - h)) {
			isJump = false;
			return ;
		}
		force = force + GRAVITY;
		y = y + force;
	}
	
	public void loadDamageImages() {
		damageImages[0] = image.getSubimage(246,2533,77,93);
		damageImages[1] = image.getSubimage(331,2533,77,94);
	}
	
	public void loadPowerImages() {
		powerImages[0] = image.getSubimage(24, 1705, 112, 88);
		powerImages[1] = image.getSubimage(148, 1703, 93, 90);
		powerImages[2] = image.getSubimage(245, 1702, 112, 91);
		powerImages[3] = image.getSubimage(371, 1707, 94, 88);
		powerImages[4] = image.getSubimage(471, 1706, 120, 88);

		powerImages[5] = image.getSubimage(126, 1811, 115, 91);

		powerImages[6] = image.getSubimage(383, 1816, 101, 88);
		powerImages[7] = image.getSubimage(490, 1815, 145, 88);
	}

	
	private void loadWalkImages() {
		walkImages[0]  = image.getSubimage(60, 236,77,98);
		walkImages[1]  = image.getSubimage(142, 235,77,98);
		walkImages[2]  = image.getSubimage(225,236,60,98);
		walkImages[3]  = image.getSubimage(304, 233,58,98);
		walkImages[4]  = image.getSubimage(377, 234,59,99);
		walkImages[5]  = image.getSubimage(453, 239,65,96);
	}
	
	private void loadStandingImages() {
		standingImages[0] = image.getSubimage(14, 4, 73, 106);
		standingImages[1] = image.getSubimage(87, 4, 68, 107);
		standingImages[2] = image.getSubimage(164, 4, 72, 108);
		standingImages[3] = image.getSubimage(241,1,72,110);
		standingImages[4] = image.getSubimage(321, 6, 70, 107);
		standingImages[5] = image.getSubimage(396, 4, 61, 105);
		standingImages[6] = image.getSubimage(464, 4, 62, 109);
		standingImages[7] = image.getSubimage(534, 4, 63, 109);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(38, 1043, 69, 103);
		kickImages[1] = image.getSubimage(120,1043,69,98);
		kickImages[2] = image.getSubimage(198, 1040, 122, 104);
		kickImages[3] = image.getSubimage(328, 1046, 72, 99);
		kickImages[4] = image.getSubimage(409, 1046, 69, 100);
		kickImages[5] = image.getSubimage(482, 1045, 92, 104);
				
	}
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(26, 819, 70, 102);
		punchImages[1] = image.getSubimage(106, 821, 72, 100);
		punchImages[2] = image.getSubimage(187, 821, 115, 100);
		punchImages[3] = image.getSubimage(310, 819, 78, 99);
		punchImages[4] = image.getSubimage(402, 816, 108, 102);
		punchImages[5] = image.getSubimage(517, 821, 79, 100);
	}
	
	private BufferedImage damageImage() {
		if(imageIndex>=2) {
			imageIndex = 0;
			currentMove = STANDING;
		}
		BufferedImage img = damageImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	private BufferedImage walkImage() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = STANDING;
		}
		BufferedImage img = walkImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	private BufferedImage kickImage() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = STANDING;
			isAttacking = false;
		}
		BufferedImage img = kickImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	private BufferedImage punchImage() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = STANDING;
			isAttacking = false;
		}
		BufferedImage img = punchImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	private BufferedImage standingImage() {
		if(imageIndex>7) {
			imageIndex=0;
		}
		BufferedImage img = standingImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	private BufferedImage powerEffect() {
		if(imageIndex>=7) {
			imageIndex=0;
			currentMove = STANDING;
			isAttacking=false;
		}
		BufferedImage img = powerImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	public void showPower() {
		power.add(new PowerEffect(x,y+45,image.getSubimage(115,3367,66,39),1));
	}
	
	public void emptyPower() {
		power.removeAll(power);
	}
	
	@Override
	public BufferedImage defaultImage() {
		 if (currentMove == WALK) {
			return walkImage();
		}
		else if(currentMove == PUNCH) {
			return punchImage();
		}
		else if (currentMove == KICK) {
			return kickImage();
		}
		else if(currentMove == POWEREFFECT) {
			return powerEffect();
		}
		else if(currentMove == DAMAGE)
			return damageImage();
		return standingImage();		
	}
}

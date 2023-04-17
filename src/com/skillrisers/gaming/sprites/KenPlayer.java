package com.skillrisers.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class KenPlayer extends Sprite {
	
	private BufferedImage [] damageImages = new BufferedImage[5];
	private BufferedImage[] standingImages = new BufferedImage[12];
	private BufferedImage[] punchImages = new BufferedImage[7];
	private BufferedImage [] kickImages = new BufferedImage[9];
	private BufferedImage[] powerImages = new BufferedImage[8];
	private ArrayList<PowerEffect> power = new ArrayList<>();
	
	public ArrayList<PowerEffect> getPowers() {
		return power;
	}
	
	public KenPlayer() throws IOException {
		x = GWIDTH - 500;
		h = 300;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(KEN_IMAGE));
		loadStandingImages();
		loadDamageImage();
		loadPunchImages();
		loadKickImages();
		loadPowerImages();
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
	
	private void loadPowerImages() {
		powerImages[0] = image.getSubimage(2016, 2740,79,91);
		powerImages[1] = image.getSubimage(2016, 2740,79,91);
		powerImages[2] = image.getSubimage(1918, 2746,89,85);
		powerImages[3] = image.getSubimage(1918, 2746,89,85);
		powerImages[4] = image.getSubimage(1815, 2749,95,83);
		powerImages[5] = image.getSubimage(1815, 2749,95,83);
		powerImages[6] = image.getSubimage(1653, 2752,151,81);
		powerImages[7] = image.getSubimage(1639, 2752,165,81);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(2035,1671,62,95);
		kickImages[1] = image.getSubimage(1960,1670,49,95);
		kickImages[2] = image.getSubimage(1875,1672,82,95);
		kickImages[3] = image.getSubimage(1806,1670,49,96);
		kickImages[4] = image.getSubimage(1731,1671,62,95);
		kickImages[5] = image.getSubimage(1662,1670,62,95);
		kickImages[6] = image.getSubimage(1594,1667,68,95);
		kickImages[7] = image.getSubimage(1466,1667,116,95);
		kickImages[8] = image.getSubimage(1399,1668,68,95);
	}
	
	private void loadStandingImages() {
		standingImages[0] = image.getSubimage(2032,869,57,88);
		standingImages[1] = image.getSubimage(1964,865,66,92);
		standingImages[2] = image.getSubimage(1892,866,66,92);
		standingImages[3] = image.getSubimage(1817,863,67,93);
		standingImages[4] = image.getSubimage(1753,863,58,95);
		standingImages[5] = image.getSubimage(1691,865,61,90);
		standingImages[6] = image.getSubimage(1616,866,64,92);
		standingImages[7] = image.getSubimage(1549,866,61,90);
		standingImages[8] = image.getSubimage(1482,864,58,93);
		standingImages[9] = image.getSubimage(1409,861,60,96);
		standingImages[10] = image.getSubimage(1336,863,60,95);
		standingImages[11] = image.getSubimage(1265,863,60,95);
	}
	
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(2028, 1152, 66, 94);
		punchImages[1] = image.getSubimage(1930, 1149, 98, 95);
		punchImages[2] = image.getSubimage(1864, 1149, 68, 96);
		punchImages[3] = image.getSubimage(1787, 1147, 78, 96);
		punchImages[4] = image.getSubimage(1668, 1147, 109, 97);
		punchImages[5] = image.getSubimage(1592, 1147, 74, 96);
		punchImages[6] = image.getSubimage(1518, 1148, 63, 96);
	}
	
	private void loadDamageImage() {
		damageImages[0] = image.getSubimage(1361, 3275, 67, 93);
		damageImages[1] = image.getSubimage(1437, 3273, 84, 100);
		damageImages[2] = image.getSubimage(1535, 3276, 81, 93);
		damageImages[3] = image.getSubimage(1628, 3277, 70, 96);
		damageImages[4] = image.getSubimage(1709, 3275, 66, 91);
	}
	
	public BufferedImage standingImage() {
		if(imageIndex>=11) {
			imageIndex = 0;
		}
		BufferedImage img = standingImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	public BufferedImage damageImage() {
		if(imageIndex>=5) {
			imageIndex = 0;
			currentMove = STANDING;
		}
		BufferedImage img = damageImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	public BufferedImage kickImage() {
		if(imageIndex>=8) {
			imageIndex = 0;
			currentMove = STANDING;
		}
		BufferedImage img = kickImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	public BufferedImage powerImage() {
		if(imageIndex>=7) {
			imageIndex = 0;
			currentMove = STANDING;
		}
		BufferedImage img = powerImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	public BufferedImage punchImage() {
		if(imageIndex>=6) {
			imageIndex = 0;
			currentMove = STANDING;
		}
		BufferedImage img = punchImages[imageIndex];
		w = img.getWidth()*3;
		h = img.getHeight()*3;
		imageIndex++;
		return img;
	}
	
	public void showPower() {
		power.add(new PowerEffect(x,y+30,image.getSubimage(1578,2759,59,33),-1));
	}
	
	public void emptyPower() {
		power.removeAll(power);
	}
	
	
	@Override
	public BufferedImage defaultImage() {
		if(currentMove == DAMAGE) {
			return damageImage();
		}
		else if(currentMove == PUNCH)
				return punchImage();
		else if(currentMove == WALK)
				return standingImage();
		else if(currentMove == KICK)
				return kickImage();
		else if(currentMove == POWEREFFECT)
			return powerImage();
		return standingImage();
	}
	
}

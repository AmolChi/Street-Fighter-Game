package com.skillrisers.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PowerEffect extends Sprite {
	
	
	
	PowerEffect(int x,int y,BufferedImage img,int direction){
		
		this.image = img;
		this.x = x;
		this.y = y;
		this.w = img.getWidth()*3;
		this.h = img.getHeight()*3;
		speed = direction*100;
	}

	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return image;
	}
	
	public void printPower(Graphics pen) {
		pen.drawImage(defaultImage(), x,y,w,h,null);
		move();
	}

}

package com.skillrisers.gaming.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Power extends Sprite{
	private String playerName;
	public Power(int x,String playerName) {
		this.x = x;
		y = 50;
		h = 50;
		w = MAX_HEALTH;
		health = MAX_HEALTH;
		this.playerName = playerName;
	}
	
	public void setHealth(int damage) {
		health = health - damage;
	}
	
	public int getHealth() {
		return health;
	}
	
	
	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void printBox(Graphics pen) {
		pen.setColor(Color.red);
		pen.fillRect(x,y,w,h);
		pen.setColor(Color.green);
		pen.fillRect(x, y, health, h);
		pen.setColor(Color.white);
		pen.setFont(new Font("times",Font.BOLD,30));
		pen.drawString(playerName, x , y+75);
	}
}

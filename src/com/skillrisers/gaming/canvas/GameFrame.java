package com.skillrisers.gaming.canvas;

import java.io.IOException;

import javax.swing.JFrame;

import com.skillrisers.gaming.utils.GameConstants;



public class GameFrame extends JFrame implements GameConstants  {
	
	public GameFrame() throws IOException  {
		setResizable(false);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GWIDTH, GHEIGHT);
		setLocationRelativeTo(null);
		Board board = new Board();
		add(board); // Board added in Frame.
		setVisible(true);
		
	}
}

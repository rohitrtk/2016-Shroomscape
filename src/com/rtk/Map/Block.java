package com.rtk.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.rtk.States.GameState;

/*
 * Handles everything to do with the blocks 
 */

public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public static final int blockSize = 64;
	private int id;
	
	public Block(int x, int y, int id) {
		setBounds(x, y, blockSize, blockSize);
		this.id = id;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		if(id != 0) {
			g.drawImage(Images.blocks[id - 1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
		}
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}

package com.rtk.Map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/*
 * Handles everything to do with the map class
 */

public class Map {
	private String path;																// Path of the map
	private int width;																	// x pos of a block
	private int height;																	// y pos of a block
	
	private Block[][] blocks;															// Array of blocks
	
	private BufferedImage bg;															// Background image
	
	public Map(String path) {
		this.path = path;
		
		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/assets/png/BG/BG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadMap();
	}
	
	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		
		for(int i = 0;i < blocks.length;i++) {
			for(int j = 0;j < blocks[0].length;j++) {
				blocks[i][j].render(g);
			}
		}
	}
	
	public void loadMap() {																// Loads the map
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// Reads the map file and puts it into a string, then serperates it and places it into
		// a 2D array
		
		try {												
			width = Integer.parseInt(br.readLine());
			height = Integer.parseInt(br.readLine());
			
			blocks = new Block[height][width];
			
			for(int i = 0;i < height;i++) {
				String line = br.readLine();
				String[] tokens = line.split("\\s+");
				
				for(int j = 0;j < width;j++) {
					blocks[i][j] = new Block(j * Block.blockSize, i * Block.blockSize, Integer.parseInt(tokens[j]));
				}
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Returns current block
	public Block[][] getBlocks() {
		return blocks;
	}
}

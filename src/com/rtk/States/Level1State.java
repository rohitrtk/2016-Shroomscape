package com.rtk.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.rtk.Map.Map;
import com.rtk.Player.Player;

/*
 *	This class handles the first level the player goes through, there are 3 stages per level 
 */

public class Level1State extends GameState {
	private Player player;						
	private Map map = new Map("/map1.map");													// Loads the map
	private int scoreNeeded = 3;															// Score needed per stage
	private int currentStage = 1;															// Sets the current stage
	private final int maxStage = 3;															// Max 3 stages per level
	
	public Level1State(GameStateManager gsm) {
		super(gsm); 																		
		xOffset = -350;																		// Sets the x position of everything
		yOffset = -200;																		// Sets the y position of everything
	}

	private void level() {																	// Sets current level
		if(currentStage == 1) {
			
		} else if(currentStage == 2) {
			scoreNeeded = 3;
			Player.currentScore = 0;
			xOffset = -350;
			yOffset = 800;
			map = new Map("/map1-2.map");
		} else if(currentStage == 3) {
			Player.currentScore = 0;
			xOffset = -350;
			yOffset = 220;
			map = new Map("/map1-3.map");
		}	
	}
	
	public void init() {																	// Run when the class is loaded
		Player.dead = false;
		player = new Player(30, 30);
		level();
	}
	 
	public void tick() {
		player.tick(map.getBlocks());
		
		if(Player.dead) {																	// If the player is dead
			gsm.states.push(new DefeatState(gsm));
		}
		
		if(Player.currentScore == scoreNeeded && currentStage < maxStage) {					// If there are stages to go to
			scoreNeeded = 0;
			currentStage++;
			level();
			System.out.print(Player.currentScore);
		} else if(Player.currentScore == 3) {												// If stages are finished
			Player.victory = true;
			System.out.println("asd");
		}
		
		if(Player.victory) {																// If player wins
			gsm.states.push(new LevelClearState(gsm));
		}
	}
	
	public void render(Graphics g) {
		map.render(g);
		player.render(g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));
		g.drawString("SHROOMS: " + Player.currentScore + "/" + 3, 50, 50);
	}
	
	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}
}

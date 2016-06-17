package com.rtk.States;

import java.awt.Graphics;

/*
 * This class will be extended upon to make different game states to make the game
 * code easier to handle. Basically a blueprint for all the state classes
 */

public abstract class GameState {

	protected GameStateManager gsm;
	public static double xOffset;
	public static double yOffset;
	public static int scoreNeeded;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;													// Sets the classes gsm to the methods gsm
		
		xOffset = 0;
		yOffset = 0;
		
		init();															// Runs the init method
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}

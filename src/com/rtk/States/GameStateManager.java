package com.rtk.States;

import java.awt.Graphics;
import java.util.Stack;

/*
 * This class will handle the current game state and will look at which ever state is currently
 * on the top of the stack 
 */

public class GameStateManager {
	public Stack<GameState> states;								// New stack of gamestates (stack is basically a list, specifically a vector)
	
	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new MenuState(this));						// Give the menu state this classes game state manager
	}
	
	public void tick() {
		states.peek().tick();
	}
	
	public void render(Graphics g) {
		states.peek().render(g);
	}
	
	public void keyPressed(int k) {
		states.peek().keyPressed(k);
	}
	
	public void keyReleased(int k) {
		states.peek().keyReleased(k);
	}
}

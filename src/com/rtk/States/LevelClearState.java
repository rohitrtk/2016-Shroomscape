package com.rtk.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.rtk.Player.Player;
import com.rtk.SummativeGame.Game;

/*
 * This class is called upon once the player clears a stage/level
 */

public class LevelClearState extends GameState {
	private BufferedImage bufferedImage; 										// Baackground image object
	
	protected LevelClearState(GameStateManager gsm) {
		super(gsm);
		
		try {																	// Loads image to background
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/assets/png/BG/BG.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, null);									// Draws the background to the screen
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));					// Makes a new font
		g.setColor(new Color(99, 0, 255));
		g.drawString("LEVEL CLEAR", Game.WIDTH / 2 - 160, Game.HEIGHT / 2);		// Write 'VICTORY' to the screen
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			Player.victory = false;												// Sets plyaer victory to false
			gsm.states.push(new MenuState(gsm));								// Goto menu state
		}
	}

	public void keyReleased(int k) {
		
	}
}

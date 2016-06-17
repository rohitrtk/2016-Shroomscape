package com.rtk.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.rtk.SummativeGame.Game;

/*
 * This class will handle everything to do with the MAIN menu
 */

public class MenuState extends GameState {

	private String[] options = {"Start", "Help", "Quit"};				// The options on the menu screen
	private int selected = 0;											// The current option selected (Int based on index of options array)
	private BufferedImage bufferedImage;								// Background image
	private BufferedImage title;
	
	protected MenuState(GameStateManager gsm) {
		super(gsm);
		
		try {															// Loads image background
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/assets/png/BG/BG.png"));
			title = ImageIO.read(getClass().getResourceAsStream("/shroomscape1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		
	}

	public void tick() {
		
	}

	public void render(Graphics g) {									// Renders menu graphics to the screen
		g.drawImage(bufferedImage, 0, 0, null);
		g.drawImage(title, Game.WIDTH / 2 - 300, 0, null);
			
		for(int i = 0;i < options.length;i++) {							// Loops through menu choices
			if(i == selected) {											// Sets the seleccted choices colour
				g.setColor(new Color(99, 0, 255));
			} else {
				g.setColor(Color.BLACK);
			}
			
			
			
			
			g.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g.drawString(options[i], Game.WIDTH / 2 - 50, (Game.HEIGHT /2) + 100 + i * 60);
		}
	}

	// Keystrokes
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_DOWN) {
			selected++;
			if(selected >= options.length) {
				selected = 0;
			}
		}
		else if(k == KeyEvent.VK_UP) {
			selected--;
			if(selected < 0) {
				selected = options.length - 1;
			}
		}
		
		if(k == KeyEvent.VK_ENTER) {
			if(selected == 0) {											// PLAY
				gsm.states.push(new Level1State(gsm));
			} else if(selected == 1) {									// HELP
				gsm.states.push(new HelpState(gsm));
			} else if(selected == 2) {									// QUIT
				System.exit(0);
			}
		}
	}
	

	public void keyReleased(int k) {	
		
	}
}

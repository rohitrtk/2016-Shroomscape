package com.rtk.States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.rtk.SummativeGame.Game;

public class HelpState extends GameState {

	private BufferedImage bufferedImage;								// Background image
	private BufferedImage instructions;									// Instruction image	
	
	protected HelpState(GameStateManager gsm) {
		super(gsm);

		try {															// Loads image background
			bufferedImage = ImageIO.read(getClass().getResourceAsStream("/assets/png/BG/BG.png"));
			instructions = ImageIO.read(getClass().getResourceAsStream("/instructions.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, null);
		g.drawImage(instructions, Game.WIDTH / 4, 100, null);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			gsm.states.push(new MenuState(gsm));
		}
	}

	public void keyReleased(int k) {
	
	}
}

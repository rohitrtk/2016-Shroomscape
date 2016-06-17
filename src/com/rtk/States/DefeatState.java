package com.rtk.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.rtk.SummativeGame.Game;

/*
 * This class handles everything to do with the player losing on a stage/level
 */

public class DefeatState extends GameState {

	private BufferedImage bufferedImage;
	
	protected DefeatState(GameStateManager gsm) {
		super(gsm);
		
		try {																// Loads the background image
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
		g.drawImage(bufferedImage, 0, 0, null);
		
		g.setColor(new Color(99, 0, 255));
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		g.drawString("DEFEAT", Game.WIDTH / 2 - 100, Game.HEIGHT / 2);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			gsm.states.push(new MenuState(gsm));
		}
	}

	public void keyReleased(int k) {

	}
}

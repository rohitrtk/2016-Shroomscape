package com.rtk.SummativeGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;

import com.rtk.Map.Images;
import com.rtk.States.GameStateManager;

/*
 * This class is where the game loop resides, handles fps and screen dimensions
 */

public class Game extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;						// Serial ID
	
	// Dimension stuff
	public static final int WIDTH = 860;									// Width of the frame
	public static final int HEIGHT = 640;									// Height of the frame
	public static final int SCALE = 2;
	
	// Game loop stuff
	private Thread thread;													// Thread for the game loop
	private boolean isRunning = false;										// Boolean for if the game IS running
	
	// Frames stuff
	private int FPS = 60;													// 60 Frames Per Second
	private long targetTime = 1000 / FPS;									// Time wanted per frame
	
	private GameStateManager gsm;											// Gamestate manager object
	
	// Audio stuff
	private String bgm = "res/bgm.wav";									// Path to the music file
	private File audioFile;													
	private AudioInputStream audioStream;
	private AudioFormat format;
	private DataLine.Info info;
	public static Clip audioClip;
	public static boolean mute = false;										// Mute boolean
	
	public Game() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));						// Sets the dimension of the panel
	
		setFocusable(true);
		addKeyListener(this);
		
		new Images();
		
		init();																// Starts the setup
	}
	
	private void init() {
		gsm = new GameStateManager();
		
		try {																// Loading music to game
			audioFile = new File(bgm);
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			format = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		isRunning = true;													// Init method to set things up
		thread = new Thread(this);											
		thread.start();
	}

	public void run() {														// Run when the thread starts
		long start;
		long elapsed;
		long wait;
		
		if(!Game.mute) {
			Game.audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			Game.audioClip.stop();
		}
		
		while(isRunning) {
			start = System.nanoTime();										// Gets the current time in nano seconds
			
			tick();															// Update method
			repaint();														// Render method
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;							// Prevents the program from running faster/slower than usual
			
			if(wait < 1) {													// Prevents negative wait time
				wait = 3;
			}
			
			try {
				Thread.sleep(wait);											// Makes the thread pause for a moment
			} catch (Exception e) {
				e.printStackTrace();										// If there is an error with the thread sleeping, print in console
			}
		}
	}
	
	// The following methods will be handled through use of the game state manager class
	
	public void tick() {
		gsm.tick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		g.clearRect(0, 0, WIDTH, HEIGHT);
		gsm.render(g);
	}
	
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
		e.consume();
	}

	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
		
	}
}

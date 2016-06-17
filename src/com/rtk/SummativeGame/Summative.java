package com.rtk.SummativeGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author Rohit
 * FINAL PROJECT
 * Created on 25/5/2016
 * Purpose: To have a full functioning platformer that allows the user to play as a character
 * whos goal is to reach the end of the level whilst avoiding traps and enemies who are trying
 * to stop him/her
 * 
 * NOTES:
 * Sprites and music are not mine, they belong to their respected owners
 * Links to some tutorials I looked at
 * 	https://www.youtube.com/watch?v=ByucQN42OOY
 * 	https://www.youtube.com/watch?v=9dzhgsVaiSo&list=PL-2t7SM0vDfcIedoMIghzzgQqZq45jYGv
 * 	https://www.youtube.com/watch?v=zkDGGSUeKKQ
 * 	http://alvinalexander.com/java/java-audio-example-java-au-play-sound
 * 	http://stackoverflow.com/ <- Best Site Ever
 * 
 * v0.23
 */

/*
 * This class is the main entry point of the program, the JFrame is created here 
 * Adds the game class to the window which runs the constructor
 */

public class Summative {
	public static void main(String[] args) {
		JFrame f = new JFrame("Shroomscape v0.23");			// New JFrame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Sets default close operation
		f.setResizable(false);								// Sets resizable to false
		f.setLayout(new BorderLayout());					// Sets the layout of the frame
		f.add(new Game(), BorderLayout.CENTER);				// Adds the 'game' to the center of the panel
		//f.setLocationRelativeTo(null);					// Makes window start in the middle of the screen
		f.pack(); 											// Packs the frame
		f.setVisible(true);                  				// Makes the frame visible
	}
}

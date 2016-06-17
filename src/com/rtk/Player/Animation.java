package com.rtk.Player;

import java.awt.image.BufferedImage;

/*
 * This class handles everything to do with the players animation
 */

public class Animation {
	private BufferedImage[] frames;										// Array of images
	private int currentFrame;											// Int to represent the current frame
	
	private long startTime;												// The start time of the animarion
	private long delay;													// How long to wait per frame change
	
	public Animation() {
		
	}
	
	public void setFrames(BufferedImage[] images) {
		frames = images;
		if(currentFrame >= frames.length) {
			currentFrame = 0;
		}
	}
	
	public void setDelay(long d) {
		delay = d;
	}
	
	public void tick() {
		if(delay == -1) {
			return;
		}
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame >= frames.length) {
			currentFrame = 0;
		}
	}
	
	public BufferedImage getImage() {
		if(frames != null) {
			return frames[currentFrame];
		} else {
			return null;
		}
	}
}

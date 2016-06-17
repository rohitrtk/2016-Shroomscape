package com.rtk.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import com.rtk.Map.Block;
import com.rtk.Map.Collision;
import com.rtk.States.GameState;
import com.rtk.SummativeGame.Game;

/*
 * This class handles everything to do with the player
 * including hit detection, drawing and animation
 */

public class Player {

	private boolean right = false;													// Right boolean
	private boolean left = false;													// Left boolean
	private boolean jump = false;													// Jump boolean
	private boolean sprint = false;													// Sprint boolean
	private boolean falling = false;												// Falling boolean
	private boolean topCollision = false;											// Top of block boolean
	private boolean collision = false;												// Collision in general
	private boolean touchingLadder = false;											// Top collision w/ ladder
	
	private double moveSpeed = 5;													// Players move speed
	private double sprintSpeed = 1.5;
	private double jumpSpeed = 5.5;													// Players jump speed
	private double currentJumpSpeed = jumpSpeed;									// Players current jump speed
	
	private double maxFallSpeed = 7;												// The maximum fall speed
	private double currentFallSpeed = 0.1;											// Decrease fall speed by this value
	
	private double maxEnergy = 100;													// Players energy for sprinting
	private double currentEnergy = 100;												// Players current energy
	private double energyDepleteSpeed = 0.5;										// Rate at which energy decreases at
	private double energyRecoverSpeed = 0.2;										// Rate at which energy recovers at
	
	public static boolean victory = false;											// If stage is clear
	public static boolean dead = false;												// If player is dead
	
	private double x;																// Players x pos relative to the game window
	private double y;																// Players y pos relative to the game window
	private int width;																// Players width
	private int height;																// Players height
	
	private Animation animation;													// Animation object
	private BufferedImage[] idleSprite;												// Idle sprites
	private BufferedImage[] walkSprite;												// Walking sprites
	private BufferedImage[] jumpSprite;												// Jump sprites
	//private BufferedImage[] attackSprite;											// Attack sprites
	//private BufferedImage[] fallSprite;
	private boolean facingRight;													// If player is facing right
	
	public static int currentScore;													// Players current score
	public static int totalScore;													// Players total score
	
	public Player(int width, int height) {											// Player constructor
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;
		this.width = width;
		this.height = height;
		
		try {																		// Load the sprites into arrays
			idleSprite = new BufferedImage[1];
			jumpSprite = new BufferedImage[1];
			walkSprite = new BufferedImage[10];
			
			idleSprite[0] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Idle (1).png"));
			jumpSprite[0] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Jump (6).png"));
			
			//BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/assets/png/Run (1).png"));
			for(int i = 0;i < walkSprite.length;i++) {
				walkSprite[i] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Run (" + (i + 1) + ").png"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		facingRight = true;
	}
	
	private void hitDetect(Block[][] b) {											// HIT DETECTION
		int ix = (int)x;
		int iy = (int)y;
		
		for(int i = 0;i < b.length;i++) {
			for(int j = 0;j < b[0].length;j++) {
				if(b[i][j].getID() != 0) {
					
					collision = false;
					
					// Right collision
					if(Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset + 8, iy + (int)GameState.yOffset + 2), b[i][j]) 
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset + 8, iy + height + (int)GameState.yOffset - 1), b[i][j])) {
						if(b[i][j].getID() == 8 || b[i][j].getID() == 9 || b[i][j].getID() == 5) {
							
						} else if(b[i][j].getID() == 7) {
							dead = true;
							currentScore = 0;
							totalScore = 0;
						} else {
							collision = true;
							right = false;							
						}
					}
					
					// Left collision
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset - 8, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset - 8, iy + height + (int)GameState.yOffset - 1), b[i][j])) {
						if(b[i][j].getID() == 8 || b[i][j].getID() == 9 || b[i][j].getID() == 5) {
							
						} else {
							collision = true;
							left = false;							
						}
					}
					
					// Top collision
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset + 1, iy + (int)GameState.yOffset), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset - 1, iy + (int)GameState.yOffset), b[i][j])) {
						if(b[i][j].getID() == 8 || b[i][j].getID() == 9) {
						
						// Ladder collision
						} else if(b[i][j].getID() == 5) {
							touchingLadder = true;
						} else {
							collision = true;
							jump = false;
							falling = true;	
							touchingLadder = false;
						}
					}
					
					// Bottom collision
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset + 2, iy + height + (int)GameState.yOffset + 1), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset - 1, iy + height + (int)GameState.yOffset + 1), b[i][j])) {
						if(b[i][j].getID() == 8 || b[i][j].getID() == 9 || b[i][j].getID() == 5) {
						
						} else if(b[i][j].getID() == 5) {						// Touching ladder
							touchingLadder = true;
						} else {
							collision = true;
							y = (b[i][j].getY() - (height + GameState.yOffset));
							falling = false;
							topCollision = true;
							touchingLadder = false;
						}
					} else {
						if(!topCollision && !jump) {
							falling = true;
						}
					}
					
					// Collision to be dealt with special blocks
					if(collision) {
						if(b[i][j].getID() == 6) {									// Collision with shrooms
							b[i][j].setID(0);
							//victory = true;
							currentScore++;
							totalScore++;
						} else if(b[i][j].getID() == 3 || b[i][j].getID() == 4) {	// Collision with water
							dead = true;
							currentScore = 0;
							totalScore = 0;
						}
					}
				}
			}
		} 																			// End of block collision
		
		topCollision = false;														// Reset the top collision
	}
	
	public void tick(Block[][] b) {
		hitDetect(b);
		
		if(sprint && left || sprint && right) {										// Handles sprint speed
			sprintSpeed = 2;
			currentEnergy -= energyDepleteSpeed;
			if(currentEnergy < 1) {													// Makes it so player can't sprint
				sprint = false;
			}
		} else {																	// Default sptinr speed is 1
			sprintSpeed = 1;
		}
		
		if(!sprint) {																// If not sprinting
			if(currentEnergy < maxEnergy) {
				currentEnergy += energyRecoverSpeed;
			} else {																// Energy cap
				currentEnergy = maxEnergy;
			}
		}
		
		if(left) facingRight = false;
		if(right) facingRight = true;
		if(left || right) {															// Walking animation
			animation.setFrames(walkSprite);
			animation.setDelay((int) moveSpeed * 10);
			if(right) {
				GameState.xOffset += moveSpeed * sprintSpeed;
			} else if(left){
				GameState.xOffset -= moveSpeed * sprintSpeed;
			}
		} else {
			animation.setFrames(idleSprite);
			animation.setDelay(-1);
		}
		
		if(jump) {																	// JUMP
			GameState.yOffset -= currentJumpSpeed;
			
			animation.setFrames(jumpSprite);
			animation.setDelay(-1);
			
			currentJumpSpeed -= 0.1;
			
			if(currentJumpSpeed < 0.1) {
				currentJumpSpeed = jumpSpeed;
				jump = false;
				falling = true;
			}
		}
		
		if(falling) {																// FALL
			GameState.yOffset += currentFallSpeed;
			
			if(currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += 0.1;
			}
		} else if(!falling) {
			currentFallSpeed = 0.1;
		}

		/*
		if(falling) {
			animation.setFrames(fallSprite);
			animation.setDelay(-1);
		}
		*/
		animation.tick();
		
		if(GameState.yOffset > 10000) {												// If the player falls out of the map somehow
			dead = true;
		}
	}
	
	public void render(Graphics g) {
		// Player hit box
		//g.setColor(Color.BLACK);
		//g.fillRect((int) x, (int) y, width, height);
		
		if(facingRight) {
			g.drawImage(animation.getImage(), (int) x + width / 2 - 25, (int) y - height / 2 - 10, width * Game.SCALE, height * Game.SCALE, null);
		} else {
			g.drawImage(animation.getImage(), (int) x + width / 2 + width, (int) y - height / 2 - 10, -width * Game.SCALE, height * Game.SCALE, null);
		}
		
		g.setColor(Color.YELLOW);
		g.fillRect(10, 500, 20, (int) currentEnergy * -4);
	}
	
	public void setScore(int currentScore) {										// Sets the current score
		this.currentScore = currentScore;
	}
	
	public int getScore() {															// Gets the current score
		return currentScore;
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_SHIFT) {
			sprint = true;
		}
		
		if(k == KeyEvent.VK_RIGHT) {
			right = true;
		}
		
		if(k == KeyEvent.VK_LEFT) {
			left = true;
		}
		
		if(k == KeyEvent.VK_UP && touchingLadder|| k == KeyEvent.VK_SPACE && touchingLadder) {
			GameState.yOffset -= 20;
		} else if(k == KeyEvent.VK_UP || k == KeyEvent.VK_SPACE && !jump && !falling) {
			jump = true;
		}
		
		if(k == KeyEvent.VK_1) {
			dead = true;
		}
		
		if(k == KeyEvent.VK_2) {
			if(!Game.mute){
				Game.mute = true;
			} else {
				Game.mute = false;
			}
		}
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_RIGHT) {
			right = false;
		}
		
		if(k == KeyEvent.VK_LEFT) {
			left = false;
		}
		
		if(k == KeyEvent.VK_1) {
			dead = false;
		}
		
		if(k == KeyEvent.VK_SHIFT) {
			sprint = false;
		}
	}
}

package com.rtk.Map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * Handles the block image files and loading them to an array
 */

public class Images {
	public static BufferedImage[] blocks;
	public static int numberOfBlocks = 9;
	
	public Images() {
		blocks = new BufferedImage[numberOfBlocks];
		
		/*
		 * Block 1 - Grass Tile
		 * Block 2 - Dirt Tile
		 * Block 3 - Left Side Grass Tile
		 * Block 4 - Left Side Dirt Tile
		 * Block 5 - Right Side Grass Tile
		 * Block 6 - Right Side Dirt Tile
		 * Block 7 - Bottom Side Anything Tile
		 * Block 8 - Water
		 * Block 9 - Left Side Floating Grass Tile				
		 * Block 10 - Middle Floating Grass Tile
		 * Block 11 - Right Side Floating Grass Tile
		 * Block 12 - Left Side Corner
		 * Block 13 - Right Side Corner
		 * Block 14 - Pink Mushroom											SHROOM
		 * Block 15 - Orange Mushroom										SHROOM
		 * Block 16 - Dark Green Bush
		 * Block 17 - Crate
		 * Block 18 - Arrow Sign
		 * Block 19 - Stone
		 * Block 20 - Dark Green Tree
		 * Block 21 - Deep Water
		 * Block 22 - Ladder
		 * Block 23 - Spikes Up
		 * Block 24 - Spikes Down
		 * Block 25 - Spikes Left
		 * Block 26 - Spikes Right
		 */		
		/** NEW BLOCKS =============================================
		 * Block 1 - Grass
		 * Block 2 - Dirt
		 * Block 3 - Water/Lava
		 * Block 4 - Deep Water/Lava
		 * Block 5 - Ladder
		 * Block 6 - Mushroom
		 * Block 7 - Spikes Left
		 * Block 8 - Arrow Sign
		 * Block 9 - Tree
		 */
		
		try {
			
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/2.png")); 			// 1
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/5.png")); 			// 2
			blocks[2] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/17-2.png")); 		// 3
			blocks[3] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/18-2.png"));			// 4
			blocks[4] = ImageIO.read(getClass().getResourceAsStream("/ladder.png"));						// 5
			blocks[5] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Mushroom_1.png"));	// 6
			blocks[6] = ImageIO.read(getClass().getResourceAsStream("/spike_left.png"));					// 7
			blocks[7] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Sign_2.png"));  	// 8
			blocks[8] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Tree_2.png"));  	// 9
			/*
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/2.png")); 			// 1
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/5.png")); 			// 2
			blocks[2] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/1.png")); 			// 3
			blocks[3] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/4.png")); 			// 4
			blocks[4] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/3.png")); 			// 5
			blocks[5] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/6.png")); 			// 6
			blocks[6] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/9.png")); 			// 7
			blocks[7] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/17-2.png"));			// 8
			blocks[8] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/13.png"));			// 9
			blocks[9] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/14.png")); 			// 10
			blocks[10] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/15.png")); 			// 11
			blocks[11] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/12.png")); 			// 12
			blocks[12] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/16.png"));			// 13
			blocks[13] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Mushroom_1.png")); // 14
			blocks[14] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Mushroom_2.png")); // 15
			blocks[15] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Bush (3).png"));	// 16
			blocks[16] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Crate.png"));		// 17
			blocks[17] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Sign_2.png"));		// 18
			blocks[18] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Stone.png")); 		// 19
			blocks[19] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Object/Tree_2.png"));		// 20
			blocks[20] = ImageIO.read(getClass().getResourceAsStream("/assets/png/Tiles/18.png"));			// 21
			blocks[21] = ImageIO.read(getClass().getResourceAsStream("/ladder.png"));						// 22
			blocks[22] = ImageIO.read(getClass().getResourceAsStream("/spike_up.png"));	// 23
			blocks[23] = ImageIO.read(getClass().getResourceAsStream("/spike_down.png"));	// 24
			blocks[24] = ImageIO.read(getClass().getResourceAsStream("/spike_left.png")); 	// 25
			blocks[25] = ImageIO.read(getClass().getResourceAsStream("/spike_right.png")); // 26
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

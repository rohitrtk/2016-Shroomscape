package com.rtk.Map;

import java.awt.Point;

/*
 * Handles collision between a point on the player and the block
 */

public class Collision {
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
}

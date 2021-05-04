package com.example;


import java.awt.Canvas;

import java.awt.Graphics;
import java.awt.Image;


class ImageLoad extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;

	public ImageLoad(Image img) {
		this.img = img;
	}

	public void paint(Graphics g) {
		if (img != null) {
			g.drawImage(img, 300, 25, 400, 400, this);
		}
	}

	public void setImage(Image img) {
		this.img = img;
	}
}

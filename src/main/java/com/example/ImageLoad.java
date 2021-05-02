package com.example;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

class ImageLoad extends Canvas {
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

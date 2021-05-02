package com.example;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ImageLoading implements ActionListener {
	JFrame fr = new JFrame("Image loading program Using awt");
	Label Label1 = new Label("Choose your image");
	Button Button1 = new Button("select");
	Image Image1;
	ImageLoad Canvas1;
	FileDialog fd = new FileDialog(fr, "Open", FileDialog.LOAD);

	void initialize() {
		fr.setSize(500, 500);
		fr.setLocation(200, 200);
		fr.setBackground(Color.lightGray);
		fr.setLayout(new FlowLayout());
		fr.add(Label1);
		fr.add(Button1);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Button1.addActionListener(this);
		Canvas1 = new ImageLoad(null);
		Canvas1.setSize(1000, 1000);
		fr.add(Canvas1);
		fr.show();
	}

	void Imageload() {
		fd.show();
		if (fd.getFile() == null) {
			Label1.setText("You have not select");
		} else {
			String d = (fd.getDirectory() + fd.getFile());
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image1 = toolkit.getImage(d);
			Canvas1.setImage(Image1);
			Canvas1.repaint();
		}
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public void actionPerformed(ActionEvent event) {
		Button b = (Button) event.getSource();
		if (b == Button1) {
			Imageload();
		}
	}

	public static void main(String args[]) {
		ImageLoading a = new ImageLoading();
		a.initialize();
	}
}

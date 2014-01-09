package Battleships.Graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	public void paintComponent(Graphics g) {
		this.setSize(200,200);
		setBackground(Color.blue);
		super.paintComponent(g);
		DrawGrid.paint(g,0,0);
	}
}

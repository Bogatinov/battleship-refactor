package Battleships.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GUI;

public class RotateButtonAction extends MouseAdapter {
	private GUI gui;
	
	public RotateButtonAction(GUI gui2)
	{
		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event)
	{
		System.out.println("Horiz = " +gui.rotate());
	}
}

package Battleships.Adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Battleships.GUI;

public class ShowButtonAction extends MouseAdapter {
	private GUI gui;
	
	public ShowButtonAction(GUI gui2)
	{
		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event)
	{
		gui.showMap();
	}
}

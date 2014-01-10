package Battleships;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Battleships.Factories.GUIFactory;
import Battleships.Factories.ShipGraphicFactory;
import Battleships.Graphics.HitIcon;
import Battleships.Graphics.InfluenceMapGraphic;
import Battleships.Graphics.InfluencePanel;
import Battleships.Graphics.MissIcon;
import Battleships.Graphics.PlayerPanel;
import Enums.Orientation;
import Enums.Position;
	
public class GUI extends JFrame
{
	public PlayerPanel attackPanel;
	public PlayerPanel homePanel;
	public InfluencePanel influenceMapPanel;
	public JTextField outText;
	public int i;
	public int j;
	public GameState gameState;
	private GUIFactory guiFactory;
	
	public Orientation orientation;
	public boolean showMap;

	public GUI(GameState paramGameState)
	{
		super("Battleships");
		gameState = paramGameState;
		guiFactory = new GUIFactory(this);
	}
	
	public void setOut(String s)
	{
		outText.setText(s);
	}
	
	public void repaint()
	{
		Graphics attackPanelGraphics = attackPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				if (gameState.isPlayerMissedShot(i,j))
					MissIcon.paint(attackPanelGraphics,j,i);
				else if (gameState.isPlayerHitShot(i,j))
					HitIcon.paint(attackPanelGraphics,j,i);
			}
		}

	}
			
	public void reset()
	{
		 guiFactory.resetLayout();
	}
	
	public boolean placeShip(String type, Position position) {
		StringBuilder out = new StringBuilder();
		boolean valid = gameState.playerHomeGrid.addShip(type, position);

		if(valid) {
			Graphics hp = homePanel.getGraphics();
			ShipGraphicFactory.paint(type, hp, position);
			out.append(gameState.playerHomeGrid.toString());
			setOut(type + " Placed");
			return true;
		} else {
			setOut(type + " Will Not Fit Here");
			out.append("not valid ");
			out.append(gameState.playerHomeGrid.toString());
			return false;
		}
	}
	
	public Orientation rotate()
	{
		if(getOrientation() == Orientation.Vertical) {
			setOrientation(Orientation.Horizontal);
			setOut("Ship Will Be Placed Horizontally");
		} else {
			setOrientation(Orientation.Vertical);
			setOut("Ship Will Be Placed Vertically");
		}
		return getOrientation();
	}
	
	public void showMap()
	{
		showMap = true;
		this.paintMap();
		setOut("Influence Map shown");
	}
	
	public void hideMap()
	{
		showMap= false;
		
		Graphics g = influenceMapPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				int col = 0;
				InfluenceMapGraphic.paint(g,(j*20),(i*20), col);
			}
		}
		
		setOut("Influence Map Hidden");
	}	
	
	public void paintMap()
	{
		
		Graphics g =  influenceMapPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				int col =  gameState.influenceMap.getVal(i,j);
				
				if(showMap)
				{
					InfluenceMapGraphic.paint(g,(j*20),(i*20), col);
				}
			}
		}
	}
	
	private void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	private Orientation getOrientation() {
		return orientation;
	}

	public void setOutText(JTextField outText) {
		this.outText = outText;
	}	
}
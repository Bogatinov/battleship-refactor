package Battleships;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Battleships.Factories.GUIFactory;
import Battleships.Factories.ShipGraphicFactory;
import Battleships.Graphics.AircraftCarrier;
import Battleships.Graphics.HitIcon;
import Battleships.Graphics.InfluenceMapGraphic;
import Battleships.Graphics.InfluencePanel;
import Battleships.Graphics.MissIcon;
import Battleships.Graphics.PlayerPanel;
import Enums.GridValue;
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
	
	public boolean playerMineSunk;
	public boolean paintMineSunk;
	public boolean paintDestSunk;
	public boolean paintSubSunk;
	public boolean paintBattleSunk;
	public boolean paintAirSunk;

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
	
    public String deploy(int i, int j)
	{
		String out1= "";
		//TODO: placeShip with position
//		out1=this.placeAir(i,j);
//		out1= out1 + "\n" +this.placeBattle(i,j);
//		out1= out1 + "\n" +this.placeDest(i,j);
//		out1= out1 + "\n" +this.placeSub(i,j);
//		out1= out1 + "\n" +this.placeMine(i,j);	
		out1=out1 +  gameState.isPlayerTurn();

		return out1;
	}
	
	public void repaint()
	{
		Graphics attackPanelGraphics = attackPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				if (gameState.isPlayerMissedShot(i,j))
					new MissIcon(attackPanelGraphics,(j*20),(i*20));
				else if (gameState.isCompHomeGridLessThanMinus1(i,j))
					new HitIcon(attackPanelGraphics,(j*20),(i*20));
			}
		}

	}
			
	public void reset()
	{
		 i = 0;
		 j = 0;
		 guiFactory.resetLayout();
	}
	
	private String placeShip(String type, Position position) {
		StringBuilder out = new StringBuilder();
		boolean valid = gameState.playerHomeGrid.addShip(type, position);

		if(valid) {
			Graphics hp = homePanel.getGraphics();
			ShipGraphicFactory.paint(type, hp, position);
			out.append(gameState.playerHomeGrid.toString());
			setOut(type + " Placed");
		} else {
			setOut(type + " Will Not Fit Here");
			out.append("not valid ");
			out.append(gameState.playerHomeGrid.toString());
		}
		return out.toString();
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
	
	public boolean getGameOver()
	{
		return gameState.IsGameOver();
	}

	public void endDeploymentPhase()
	{
		//TODO: call this method after all ships are added in deploy
		gameState.SetAllShipsDeployed();
		setOut("All Ships Deployed, Player's Turn! Click on the left grid to fire shots");
		gameState.setPlayerTurn();
		outText.setText( gameState.turnToString());
	} 
	
	
	
	public void paintMap()
	{
		
		Graphics g =  influenceMapPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				int col =  gameState.influenceMap.getVal(i,j);
				
				if( showMap)
				{
					InfluenceMapGraphic.paint(g,(j*20),(i*20), col);
				}
			}
		}
	}
	
	public void agentShot(int X, int Y)	{
		if( gameState.agentTurn &&  gameState.isBothPlayerAndAgentShipsDeployed())
		{
		GridValue sqrVal =  gameState.playerHomeGrid.getGridVal(X,Y);
						
						if(sqrVal == GridValue.EmptyCellValue || sqrVal == GridValue.MissedShot)
						{
							System.out.println("Shot already taken! Have another go"); 
						}
							
						if(sqrVal == GridValue.EmptyCellValue)
						{
							System.out.println( gameState.playerHomeGrid.shot(X,Y));
							 gameState.compHomeGrid.set(X,Y,GridValue.MissedShot);
							 gameState.influenceMap.miss(X,Y);
							this.paintMap();
							Graphics hp =  homePanel.getGraphics();	
							new MissIcon(hp,(Y*20),(X*20));
							setOut("Agent Has Missed. Player's Turn");
							this. gameState.setPlayerTurn();
							setOut( gameState.turnToString());
						} else {
							
						}
						
						if(sqrVal.getValue() > 1)
						{
							System.out.println( gameState.playerHomeGrid.shot(X,Y));
							 gameState.compHomeGrid.set(X,Y,GridValue.SuccessfulShot);
							 gameState.influenceMap.hit(X,Y);
							Graphics hp =  homePanel.getGraphics();	
							new HitIcon(hp,(Y*20),(X*20));
							setOut("Agent Has Hit One Of your ships! Agent's Turn again");
							this.paintMap();
							
						}
						
						System.out.println("compAtt");						
						System.out.println( gameState.compHomeGrid.toString());
						
						if(sqrVal==GridValue.EmptyCellValue)
							this. gameState.setPlayerTurn();
				
		}		
		
		System.out.println("Map is \n" +  gameState.influenceMap.toString());
		
		
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
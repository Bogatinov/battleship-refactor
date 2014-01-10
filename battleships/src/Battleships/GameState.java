package Battleships;

import java.awt.Graphics;

import javax.swing.JTextField;

import Battleships.Graphics.HitIcon;
import Battleships.Graphics.MissIcon;
import Enums.GridValue;

public class GameState {

	private static final int WidthOfGrid = 10;
	private static final int HeightOfGrid = 10;
	
	private boolean playerShipsdeployed;
	private boolean agentShipsDeployed;
	
	public Grid playerHomeGrid;
    public boolean playerTurn;
    private boolean allPlayerShipsSunk;
    
    public Grid compHomeGrid;
	public boolean agentTurn;
	private boolean allAgentShipsSunk;
	
	public InfluenceMap influenceMap;
	
	public GameState() {
		allAgentShipsSunk = false;
		allPlayerShipsSunk = false;
		playerHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		compHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		influenceMap = new InfluenceMap();
		playerTurn = true;
		agentTurn = false;
		playerShipsdeployed = false;
	}
	

	public void setShipSunkStates() {
		allPlayerShipsSunk = playerHomeGrid.areShipsSunk();
		allAgentShipsSunk = compHomeGrid.areShipsSunk();
	}
	
	public boolean areAgentShipsSunk() {
		return allAgentShipsSunk;
	}

	public boolean arePlayerShipsSunk() {
		return allPlayerShipsSunk;
	}
	
	public String acceptPlayerShot(int i, int j, Graphics attackPanelGraphics, JTextField outText)
	{
		int sqr = playerHomeGrid.getGridVal(i,j).getValue();
		String out ="";

		if (sqr == 0)
		{
			boolean hit = false;
			hit = compHomeGrid.shot(i,j);

			if(hit)
			{
				new HitIcon(attackPanelGraphics,(j*20),(i*20));
				compHomeGrid.set(i,j,GridValue.SuccessfulShot);
				outText.setText("HIT! Have Another Turn!");
			}
			else
			{
				new MissIcon(attackPanelGraphics,(j*20),(i*20));
				compHomeGrid.set(i,j,GridValue.MissedShot);
				out="Miss!"+ playerTurn;
				outText.setText("Miss. Agent's Turn");
				startAgentTurn();
			}
		}
		setShipSunkStates();
		
		out = out + "CompHome " +compHomeGrid.toString();
		out = out + "player Attack = \n" + playerHomeGrid.toString();
	
		
		return out;	
	}

	public boolean isPlayerWinner() {
		return allAgentShipsSunk;
	}

	public boolean isBothPlayerAndAgentShipsDeployed() {
		return playerShipsdeployed && agentShipsDeployed;
	}
	
	public boolean arePlayerShipsDeployed() {
		return playerShipsdeployed;
	}

	public void setPlayerShipsDeployed() {
		playerShipsdeployed = true;
	}

	public boolean IsAcceptingPlayerInput() {
		return playerTurn && playerShipsdeployed;
	}

	public void addAgentShips(Grid gridWithAgentShipsPlaced) {
		compHomeGrid = gridWithAgentShipsPlaced;
		agentShipsDeployed = compHomeGrid.areShipsPlaced();
	}

	public boolean isCompHomeGridLessThanMinus1(int i,int j) {
		return compHomeGrid.getGridVal(i,j).getValue() <= -1;
	}
	

	public void startAgentTurn() {
		agentTurn = true;
		playerTurn = false;
	}

	public boolean isPlayerTurn() {
		return playerTurn && !agentTurn;
	}
	

	public void setPlayerTurn()
	{
			playerTurn= true;
			agentTurn = false;	
	}

	public String turnToString() {
	
		if(playerTurn)
			return "Player turn, take a shot";
		else if(agentTurn)
			return "Agent turn, please wait";
		else
			return "Error! neither player's turn";
	}

	public boolean isAgentTurn() {
		return agentTurn && !playerTurn;
	}

	public boolean isPlayerMissedShot(int i, int j) {
		return playerHomeGrid.getGridVal(i, j) == GridValue.MissedShot;
	}
	
	public void agentShot(int X, int Y, GUI gui)	{
		if( agentTurn &&  isBothPlayerAndAgentShipsDeployed())
		{
		GridValue sqrVal =  playerHomeGrid.getGridVal(X,Y);
						
						if(sqrVal == GridValue.EmptyCellValue || sqrVal == GridValue.MissedShot)
						{
							System.out.println("Shot already taken! Have another go"); 
						}
							
						if(sqrVal == GridValue.EmptyCellValue)
						{
							System.out.println( playerHomeGrid.shot(X,Y));
							 compHomeGrid.set(X,Y,GridValue.MissedShot);
							 influenceMap.miss(X,Y);
							gui.paintMap();
							Graphics hp =  gui.homePanel.getGraphics();	
							new MissIcon(hp,(Y*20),(X*20));
							gui.setOut("Agent Has Missed. Player's Turn");
							setPlayerTurn();
							gui.setOut(turnToString());
						} else {
							
						}
						
						if(sqrVal.getValue() > 1)
						{
							System.out.println(playerHomeGrid.shot(X,Y));
							compHomeGrid.set(X,Y,GridValue.SuccessfulShot);
							influenceMap.hit(X,Y);
							Graphics hp =  gui.homePanel.getGraphics();	
							new HitIcon(hp,(Y*20),(X*20));
							gui.setOut("Agent Has Hit One Of your ships! Agent's Turn again");
							gui.paintMap();
							
						}
						
						System.out.println("compAtt");						
						System.out.println(compHomeGrid.toString());
						
						if(sqrVal==GridValue.EmptyCellValue)
							setPlayerTurn();
				
		}		
		
		System.out.println("Map is \n" +  influenceMap.toString());
		
		
	}
}
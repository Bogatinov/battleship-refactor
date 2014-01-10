package Battleships;

import java.awt.Graphics;

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
	private boolean allAgentShipsSunk;

	public InfluenceMap influenceMap;

	public GameState() {
		allAgentShipsSunk = false;
		allPlayerShipsSunk = false;
		playerHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		compHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		influenceMap = new InfluenceMap();
		playerTurn = false;
		playerShipsdeployed = false;
	}


	public void setShipSunkStates() {
		allPlayerShipsSunk = playerHomeGrid.areShipsSunk();
		allAgentShipsSunk = compHomeGrid.areShipsSunk();
	}

	public String acceptPlayerShot(int i, int j, Graphics attackPanelGraphics)
	{
		String out ="";

		boolean hit = compHomeGrid.shot(i,j);

		if(hit)
		{
			HitIcon.paint(attackPanelGraphics,j,i);
			compHomeGrid.set(i,j,GridValue.SuccessfulShot);
		}
		else
		{
			MissIcon.paint(attackPanelGraphics,j,i);
			compHomeGrid.set(i,j,GridValue.MissedShot);
			out="Miss!"+ playerTurn;
			changeTurn();
		}
		setShipSunkStates();

		out = out + "CompHome \n" +compHomeGrid.toString();
		out = out + "player Attack = \n" + playerHomeGrid.toString();


		return out;	
	}

	public boolean isPlayerWinner() {
		return allAgentShipsSunk;
	}
	
	public boolean isAgentWinner() {
		return allPlayerShipsSunk;
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

	public boolean isPlayerHitShot(int i,int j) {
		return compHomeGrid.getGridVal(i,j).getValue() <= -1;
	}
	
	public void changeTurn() {
		playerTurn = !playerTurn; 
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public String turnToString() {

		if(playerTurn)
			return "Player turn, take a shot";
		else
			return "Agent turn, please wait";
	}

	public boolean isAgentTurn() {
		return !playerTurn;
	}

	public boolean isPlayerMissedShot(int i, int j) {
		return playerHomeGrid.getGridVal(i, j) == GridValue.MissedShot;
	}

	public void agentShot(int X, int Y, GUI gui)	{
		GridValue sqrVal = playerHomeGrid.getGridVal(X,Y);

		if(sqrVal == GridValue.MissedShot) {
			System.out.println("Shot already taken! Have another go"); 
		} 
		else if(sqrVal == GridValue.EmptyCellValue) {
			System.out.println( playerHomeGrid.shot(X,Y));
			playerHomeGrid.set(X,Y,GridValue.MissedShot);
			influenceMap.miss(X,Y);
			gui.paintMap();
			Graphics hp =  gui.homePanel.getGraphics();	
			MissIcon.paint(hp,Y,X);
			gui.setOut("Agent Has Missed. Player's Turn");
			changeTurn();
			gui.setOut(turnToString());
		} else if(sqrVal.getValue() > 1) {
			System.out.println(playerHomeGrid.shot(X,Y));
			playerHomeGrid.set(X,Y,GridValue.SuccessfulShot);
			influenceMap.hit(X,Y);
			Graphics hp =  gui.homePanel.getGraphics();	
			HitIcon.paint(hp,Y,X);
			gui.setOut("Agent Has Hit One Of your ships! Agent's Turn again");
			gui.paintMap();
		}

		System.out.println("compAtt");						
		System.out.println(compHomeGrid.toString());
		System.out.println("Map is \n" +  influenceMap.toString());
	}
}
package Battleships;

import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Battleships.Graphics.HitIcon;
import Battleships.Graphics.MissIcon;

public class GameState {

	private static final int WidthOfGrid = 10;
	private static final int HeightOfGrid = 10;
	private boolean isGameOver;
	
	private boolean playerShipsdeployed;
	private boolean agentShipsDeployed;
	
	public Grid playerHomeGrid;
	//public Grid playerAtt;
	private boolean playerBattleSunk;
	private boolean playerSubSunk;
	private boolean playerDestSunk;
    private boolean playerAirSunk;
    private boolean playerMineSunk;
    private boolean playerWins;
    public boolean playerTurn;
    private boolean allPlayerShipsSunk;
    
    public Grid compHomeGrid;
	//public Grid compAtt;
	private boolean agentBattleSunk;
	private boolean agentAirSunk;
	private boolean agentDestSunk;
	private boolean agentSubSunk;
	private boolean agentMineSunk;
	public boolean agentTurn;
	private boolean allAgentShipsSunk;
	
	public InfluenceMap influenceMap;
	
	public GameState() {
		isGameOver = false;
		playerBattleSunk = false;
		allAgentShipsSunk = false;
		allPlayerShipsSunk = false;
		playerSubSunk = false;
		playerAirSunk = false;
		agentAirSunk = false;
		agentBattleSunk = false;
		playerHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		compHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		//compAtt = new Grid(WidthOfGrid, HeightOfGrid);
		//playerAtt = new Grid(WidthOfGrid, HeightOfGrid);
		influenceMap = new InfluenceMap();
		playerTurn = true;
		agentTurn = false;
		playerShipsdeployed = false;
	}

	public void outputHitList(JTextComponent displayTextbox)
	{
		
			if(compHomeGrid.isAirSunk())
			{
				displayTextbox.setText("You Have sunk the Agent's Aircraft Carrier");
			}
			if(compHomeGrid.isBattleSunk())
			{
				displayTextbox.setText(displayTextbox.getText() +("You Have sunk the Agent's Battleship"));
			}
			if(compHomeGrid.isDestSunk())
			{
				displayTextbox.setText(displayTextbox.getText() +("You Have sunk the Agent's Destroyer"));
			}
			if(compHomeGrid.isSubSunk())
			{
				displayTextbox.setText(displayTextbox.getText() +("You Have sunk the Agent's Submarine"));
			}
			if(compHomeGrid.isMineSunk())
			{
				displayTextbox.setText(displayTextbox.getText() + ("You Have sunk the Agent's Minesweeper"));
			}
		
	}
	
	public boolean IsGameOver() {
		return isGameOver;
	}

	public void gameNotOver() {
		isGameOver = false;

	}

	public void SetGameOver() {
		isGameOver = true;

	}

	public void setShipSunkStates() {
		playerAirSunk = playerHomeGrid.isAirSunk();
		playerBattleSunk = playerHomeGrid.isBattleSunk();
		playerDestSunk = playerHomeGrid.isDestSunk();
		playerSubSunk = playerHomeGrid.isSubSunk();
		playerMineSunk = playerHomeGrid.isMineSunk();
		
		agentAirSunk = compHomeGrid.isAirSunk();
		agentBattleSunk = compHomeGrid.isBattleSunk();
		agentDestSunk = compHomeGrid.isDestSunk();
		agentSubSunk = compHomeGrid.isSubSunk();
		agentMineSunk = compHomeGrid.isMineSunk();

		this.setAgentShipsSunk();
		this.setPlayerShipsSunk();

	}
	private void setAgentShipsSunk() {
		if(compHomeGrid.areShipsSunk())
			allAgentShipsSunk = true;
	}
	
	private void setPlayerShipsSunk() {
		if (playerHomeGrid.areShipsSunk())
			allPlayerShipsSunk = true;
	}
	
	public boolean areAgentShipsSunk() {
		return allAgentShipsSunk;
	}

	public boolean arePlayerShipsSunk() {
		return allPlayerShipsSunk;
	}

	public void updatePlayerClick(int gridj, int gridi, Graphics attackPanelGraphics) {
		if (playerTurn && !isGameOver && playerShipsdeployed) {
			//System.out.println(acceptPlayerShot(gridi, gridj, attackPanelGraphics));
			setShipSunkStates();
		}
	}
	
	public String acceptPlayerShot(int i, int j, Graphics attackPanelGraphics, JTextField outText)
	{
		int sqr = playerHomeGrid.getGridVal(i,j);
		String out ="";

			if (sqr == 0)
			{
				boolean hit = false;
				hit = compHomeGrid.shot(i,j);
		
				
		
				if(hit)
				{
					HitIcon.paint(attackPanelGraphics,(j*20),(i*20));
					playerHomeGrid.set(i,j,9);
					outText.setText("HIT! Have Another Turn!");
				}
				else
				{
					MissIcon.paint(attackPanelGraphics,(j*20),(i*20));
					compHomeGrid.set(i,j,1);
					playerHomeGrid.set(i,j,1);
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
	public void PlayerIsTheWinner() {
		playerWins = true;
	}

	public boolean isPlayerWinner() {
		return playerWins;
	}

	public boolean isBothPlayerAndAgentShipsDeployed() {
		return playerShipsdeployed && agentShipsDeployed;
	}
	
	public boolean arePlayerShipsDeployed() {
		return playerShipsdeployed;
	}
	
	public boolean areAgentShipsDeployed() {
		return agentShipsDeployed;
	}

	public void SetAllShipsDeployed() {
		playerShipsdeployed = true;
	}

	public boolean IsAcceptingPlayerInput() {
		return playerTurn && !isGameOver && playerShipsdeployed;
	}

	public void addAgentShips(Grid gridWithAgentShipsPlaced) {
		compHomeGrid = gridWithAgentShipsPlaced;
		agentShipsDeployed = compHomeGrid.areShipsPlaced();
	}

//	public boolean isCompHomegridRefIsminus3(int i, int j) {
//		return compHomeGrid.getGridVal(i,j) == -3;
//	}
//
//	public boolean isCompHomeGridRefMinus4(int i, int j) {
//		return compHomeGrid.getGridVal(i,j) == -4;
//	}
//
//	public boolean isCompHomeGridLessThanMinus1(int i,int j) {
//		return compHomeGrid.getGridVal(i,j) < -1;
//	}

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
	
	public void determineIfShotSunkAShip(GUI gui, Agent smith) {
		System.out.println("Player Home board \n" + playerHomeGrid.toString());
		if(playerHomeGrid.isMineSunk())
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == -6)
						{
							smith.setSunk(i,j);
							gui.paintMineSunk = true;
						}
					}
				}
		}
		
		if(playerHomeGrid.isDestSunk())
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == -1)
						{
							smith.setSunk(i,j);
							gui.paintDestSunk = true;
						}
					}
				}
		}
		
		if(playerHomeGrid.isSubSunk())
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == -5)
						{
							smith.setSunk(i,j);
							gui.paintSubSunk = true;	
						}
					}
				}
		}
		
		if(playerHomeGrid.isBattleSunk())
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == -4)
						{
							smith.setSunk(i,j);
							gui.paintBattleSunk = true;
						}
					}
				}
		}
		
		if(playerHomeGrid.isAirSunk())
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == -3)
						{
							smith.setSunk(i,j);
							gui.paintAirSunk = true;
						}
					}
				}
		}
	}
}
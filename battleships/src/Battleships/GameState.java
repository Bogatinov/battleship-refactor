package Battleships;

import java.awt.Graphics;

import javax.swing.JTextField;

import Battleships.Graphics.HitIcon;
import Battleships.Graphics.MissIcon;
import Battleships.Ships.AircraftCarrier;
import Battleships.Ships.Battleship;
import Battleships.Ships.Destroyer;
import Battleships.Ships.Minesweeper;
import Battleships.Ships.Submarine;
import Enums.GridValue;

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
		playerAirSunk = playerHomeGrid.isShipSunk(AircraftCarrier.class.getName());
		playerBattleSunk = playerHomeGrid.isShipSunk(Battleship.class.getName());
		playerDestSunk = playerHomeGrid.isShipSunk(Destroyer.class.getName());
		playerSubSunk = playerHomeGrid.isShipSunk(Submarine.class.getName());
		playerMineSunk = playerHomeGrid.isShipSunk(Minesweeper.class.getName());
		allPlayerShipsSunk = playerHomeGrid.areShipsSunk();
		
		agentAirSunk = compHomeGrid.isShipSunk(AircraftCarrier.class.getName());
		agentBattleSunk = compHomeGrid.isShipSunk(Battleship.class.getName());
		agentDestSunk = compHomeGrid.isShipSunk(Destroyer.class.getName());
		agentSubSunk = compHomeGrid.isShipSunk(Submarine.class.getName());
		agentMineSunk = compHomeGrid.isShipSunk(Minesweeper.class.getName());
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
				playerHomeGrid.set(i,j,GridValue.SuccessfulShot);
				outText.setText("HIT! Have Another Turn!");
			}
			else
			{
				new MissIcon(attackPanelGraphics,(j*20),(i*20));
				compHomeGrid.set(i,j,GridValue.MissedShot);
				playerHomeGrid.set(i,j,GridValue.MissedShot);
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
	
	public void determineIfShotSunkAShip(GUI gui, Agent smith) {
		System.out.println("Player Home board \n" + playerHomeGrid.toString());
		if(playerHomeGrid.isShipSunk(Minesweeper.class.getName()))
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == GridValue.MinesweeperShot)
						{
							smith.setSunk(i,j);
							gui.paintMineSunk = true;
						}
					}
				}
		}
		
		if(playerHomeGrid.isShipSunk(Destroyer.class.getName()))
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == GridValue.DestroyerShot)
						{
							smith.setSunk(i,j);
							gui.paintDestSunk = true;
						}
					}
				}
		}
		
		if(playerHomeGrid.isShipSunk(Submarine.class.getName()))
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == GridValue.SubmarineShot)
						{
							smith.setSunk(i,j);
							gui.paintSubSunk = true;	
						}
					}
				}
		}
		
		if(playerHomeGrid.isShipSunk(Battleship.class.getName()))
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == GridValue.BattleshipShot)
						{
							smith.setSunk(i,j);
							gui.paintBattleSunk = true;
						}
					}
				}
		}
		
		if(playerHomeGrid.isShipSunk(AircraftCarrier.class.getName()))
		{
				for (int i = 0; i < 10; i++) //change these to ROWS to use the default
				{
					for (int j = 0; j < 10; j++)//change this to CoLumns for default
					{
						if(playerHomeGrid.getGridVal(i,j) == GridValue.AircraftcarrierShot)
						{
							smith.setSunk(i,j);
							gui.paintAirSunk = true;
						}
					}
				}
		}
	}

	public boolean isPlayerMissedShot(int i, int j) {
		return playerHomeGrid.getGridVal(i, j) == GridValue.MissedShot;
	}
}
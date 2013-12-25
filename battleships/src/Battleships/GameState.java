package Battleships;

import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Battleships.Graphics.HitIcon;
import Battleships.Graphics.MissIcon;

public class GameState {

	private static final int WidthOfGrid = 10;
	private static final int HeightOfGrid = 10;
	private boolean gameOver;
	public Grid playerHomeGrid;
	private boolean playerBattleSunk;
	private boolean allAgentShipsSunk;
	private boolean allPlayerShipsSunk;
	boolean playerSubSunk;
	boolean playerDestSunk;
	private boolean agentBattleSunk;
	private boolean agentAirSunk;
	private Grid compHomeGrid;
	public Grid compAtt;
	public Grid playerAtt;
	public InfluenceMap influenceMap;
	private boolean playerMineSunk;
	private boolean agentDestSunk;
	private boolean agentSubSunk;
	private boolean agentMineSunk;
	private boolean playerWins;
	private boolean isGameOver;

	public boolean playerTurn;
	public boolean agentTurn;
	private boolean playerShipsdeployed;
	private boolean agentShipsDeployed;
	
	public GameState() {
		gameOver = false;
		playerBattleSunk = false;
		allAgentShipsSunk = false;
		allPlayerShipsSunk = false;
		playerSubSunk = false;
		agentAirSunk = false;
		agentBattleSunk = false;
		playerHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		compHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		compAtt = new Grid(WidthOfGrid, HeightOfGrid);
		playerAtt = new Grid(WidthOfGrid, HeightOfGrid);
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
		return gameOver;
	}

	public void gameNotOver() {
		gameOver = false;

	}

	public void SetGameOver() {
		gameOver = true;

	}

	public void setShipSunkStates() {
		playerHomeGrid.isAirSunk();
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
	
	public boolean areAllAgentShipsSunk() {
		return allAgentShipsSunk;
	}

	public boolean getPlayerShipsSunk() {
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
		int sqr = playerAtt.getGridVal(i,j);
		String out ="";

			if (sqr == 0)
			{
				boolean hit = false;
				hit = compHomeGrid.shot(i,j);
		
				
		
				if(hit)
				{
					HitIcon.paint(attackPanelGraphics,(j*20),(i*20));
					playerAtt.update(i,j,9);
					outText.setText("HIT! Have Another Turn!");
				}
				else
				{
					MissIcon.paint(attackPanelGraphics,(j*20),(i*20));
					compHomeGrid.update(i,j,1);
					playerAtt.set(i,j,1);
					out="Miss!"+ playerTurn;
					outText.setText("Miss. Agent's Turn");
					startAgentTurn();
				}
			}
	
		
		
		setShipSunkStates();
		
		out = out + "CompHome " +compHomeGrid.toString();
		out = out + "player Attack = \n" + playerAtt.toString();
	
		
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

	public void SetAllShipsDeployed() {
		playerShipsdeployed = true;
	}

	public boolean IsAcceptingPlayerInput() {
		return playerTurn && !gameOver && playerShipsdeployed;
	}

	public void addAgentShips(Grid gridWithAgentShipsPlaced) {
		compHomeGrid = gridWithAgentShipsPlaced;
		agentShipsDeployed = compHomeGrid.areShipsPlaced();
	}

	public boolean isCompHomegridRefIsminus3(int i, int j) {
		return compHomeGrid.getGridVal(i,j) == -3;
	}

	public boolean isCompHomeGridRefMinus4(int i, int j) {
		return compHomeGrid.getGridVal(i,j) == -4;
	}

	public boolean isCompHomeGridLessThanMinus1(int i,int j) {
		return compHomeGrid.getGridVal(i,j) < -1;
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
}
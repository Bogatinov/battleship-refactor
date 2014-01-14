package Battleships;

import Enums.Position;

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
	
	public boolean playerShot(int i, int j) {
		return compHomeGrid.shot(i, j);
	}
	
	public boolean placeShip(String type, Position position) {
		return playerHomeGrid.addShip(type, position);
	}
	
	public boolean agentShot(int X, int Y)	{
		if(playerHomeGrid.shot(X, Y)) {
			influenceMap.hit(X, Y);
			return true;
		} else {
			influenceMap.miss(X, Y);
			return false;
		}
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

	public void addAgentShips(Grid gridWithAgentShipsPlaced) {
		compHomeGrid = gridWithAgentShipsPlaced;
		agentShipsDeployed = compHomeGrid.areShipsPlaced();
	}
	
	public void changeTurn() {
		playerTurn = !playerTurn; 
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public String turnToString() {

		if(isPlayerTurn())
			return "Player turn, take a shot";
		else
			return "Agent turn, please wait";
	}

	public boolean isAgentTurn() {
		return !playerTurn;
	}
}
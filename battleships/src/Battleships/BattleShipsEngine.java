package Battleships;

public class BattleShipsEngine {
	
	public GameState gameState;
	public boolean agentWins;
	public boolean minePlaced;
	public boolean destPlaced;
	public boolean subPlaced;
	public boolean battlePlaced;
	
	public boolean agentMineSunk;
	public boolean agentDestSunk;
	public boolean agentSubSunk;
	public boolean agentAirSunk;
	private GUI gui;
	private Agent smith;

	public BattleShipsEngine() {
		this.initializeVariables();
		this.printInitialState();
		this.waitForPlayerToPlaceShips();
		this.loadingGame();
		this.startGame();
		this.endGame();
	}
	
	private void endGame() {
		System.out.println("Game Over!");
		if(gameState.isPlayerWinner())
		{
			System.out.println("Player Wins");
			gui.setOut("Game Over! You Win!");
		}
		else
		{
			System.out.println("Computer Wins");
			gui.setOut("Game Over! Agent Wins!");
		}
	}
	
	private void initializeVariables() {
		minePlaced = false;
		destPlaced = false;
		subPlaced = false;
		battlePlaced = false;
		gui = new GUI(new GameState());
		smith = new Agent();
	}
	
	private void printInitialState() {
		System.out.println("PlayerTurn " + gameState.isPlayerTurn());
		System.out.println("Deployed " + gameState.isBothPlayerAndAgentShipsDeployed());
		
		System.out.println("PlayerTurn " + gameState.isPlayerTurn());	
		System.out.println("Deployed " + gameState.isBothPlayerAndAgentShipsDeployed());
	}
	
	private void waitForPlayerToPlaceShips() {
		while(!gameState.arePlayerShipsDeployed())
		{
				//PlayerDeploymentPhase, wait for player to place all their ships
		}
	}
	
	private void loadingGame() {
		gameState.addAgentShips(smith.placeShips());
		gameState.setPlayerTurn();
		gui.outText.setText(gameState.turnToString());
	}
	
	private void startGame() {
		while (!gameState.IsGameOver())
		{
			this.PlayerTurn();
			gui.repaint();
			this.AgentTurn();
		}
	}
	private void isAgentWinner() {
		if(gameState.arePlayerShipsSunk())
		{
			gameState.SetGameOver();
			gameState.setPlayerTurn();
		}
	}
	private void isPlayerWinner() {
		if(gameState.areAgentShipsSunk())
		{
			System.out.println("All sunk");
			gameState.SetGameOver();
			gameState.PlayerIsTheWinner();
		}
	}
	private void AgentTurn() {
		while(gameState.isAgentTurn()) {
			System.out.println("agent turn");
			smith.nextShot(gameState.influenceMap, gameState.compHomeGrid);
			gui.agentShot(smith.getI(),smith.getJ());
			System.out.println("shot at " + smith.getI() + " " +smith.getJ());
			System.out.println(gameState.compHomeGrid.toString());
			gameState.determineIfShotSunkAShip(gui, smith);
			gameState.setShipSunkStates();
			this.threadSleep(1000);
			this.isAgentWinner();
		}
	}
	private void threadSleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	private void PlayerTurn() {
		while (gameState.isPlayerTurn())
		{
			gameState.setShipSunkStates();
			this.isPlayerWinner();
		}
	}
	
	private void resetGame() {
		gameState = new GameState();
		gui.reset();
	}
	
	public static void main (String args[])
	{
		BattleShipsEngine engine = new BattleShipsEngine();
	}
}
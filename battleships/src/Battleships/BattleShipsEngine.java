package Battleships;

public class BattleShipsEngine {
	public GameState gameState;
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
		gameState = new GameState();
		gui = new GUI(gameState);
		smith = new Agent();
	}
	
	private void printInitialState() {
		System.out.println("PlayerTurn " + gameState.isPlayerTurn());
		System.out.println("Deployed " + gameState.isBothPlayerAndAgentShipsDeployed());
		
		System.out.println("PlayerTurn " + gameState.isPlayerTurn());	
		System.out.println("Deployed " + gameState.isBothPlayerAndAgentShipsDeployed());
	}
	
	private void waitForPlayerToPlaceShips() {
		while(!gameState.arePlayerShipsDeployed()){}
	}
	
	private void loadingGame() {
		gameState.addAgentShips(smith.placeShips());
		gameState.changeTurn();
		gui.setOut(gameState.turnToString());
	}
	
	private void startGame() {
		while (!gameState.isPlayerWinner() && !gameState.isAgentWinner())
		{
			this.PlayerTurn();
			gui.repaint();
			this.AgentTurn();
		}
	}

	private void AgentTurn() {
		while(gameState.isAgentTurn()) {
			System.out.println("agent turn");
			smith.nextShot(gameState.influenceMap, gameState.compHomeGrid);
			gameState.agentShot(smith.getI(),smith.getJ(),this.gui);
			System.out.println("shot at " + smith.getI() + " " +smith.getJ());
			System.out.println(gameState.compHomeGrid.toString());
			smith.setSunk(smith.getI(), smith.getJ());
			gameState.setShipSunkStates();
			this.threadSleep(1000);
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
		}
	}
	
	public static void main (String args[])
	{
		BattleShipsEngine engine = new BattleShipsEngine();
	}
}
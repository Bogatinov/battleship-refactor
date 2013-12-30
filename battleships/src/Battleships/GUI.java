package Battleships;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Battleships.Adapters.AttackMousePressListener;
import Battleships.Adapters.HideButtonAction;
import Battleships.Adapters.HomeMousePressListener;
import Battleships.Adapters.NewButtonAction;
import Battleships.Adapters.QuitButtonAction;
import Battleships.Adapters.RotateButtonAction;
import Battleships.Adapters.ShowButtonAction;
import Battleships.Graphics.AircraftCarrier;
import Battleships.Graphics.AircraftCarrierH;
import Battleships.Graphics.AttackPanel;
import Battleships.Graphics.Battleship;
import Battleships.Graphics.BattleshipH;
import Battleships.Graphics.Destroyer;
import Battleships.Graphics.DestroyerH;
import Battleships.Graphics.HitIcon;
import Battleships.Graphics.HomePanel;
import Battleships.Graphics.InfluenceMapGraphic;
import Battleships.Graphics.InfluencePanel;
import Battleships.Graphics.Minesweeper;
import Battleships.Graphics.MinesweeperH;
import Battleships.Graphics.MissIcon;
import Battleships.Graphics.Submarine;
import Battleships.Graphics.SubmarineH;
import Battleships.Graphics.SunkIcon;
	
public class GUI extends JFrame
{
	public AttackPanel attackPanel;
	public HomePanel homePanel;
	public InfluencePanel influenceMapPanel;
	public JTextField outText;
	public int i;
	public int j;
	public GameState gameState;
	
	public boolean horiz;
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
		this.setLayout();
		this.initializeVariables();
		// gameState = paramGameState;
		this.setContentPane();
		this.finishLayout();
	}
	
	private void setLayout() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	private void initializeVariables() {
		paintMineSunk= false;
		paintDestSunk= false;
		paintSubSunk= false;
		paintBattleSunk= false;
		paintAirSunk= false;
		horiz = true;
		showMap= true;
	}
	
	private void setContentPane() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(2,1));
		contentPane.add(createCenterPanel(),BorderLayout.CENTER);
		contentPane.add(createSouthPanel(),BorderLayout.SOUTH);
	}
	
	private void finishLayout() {
		this.pack();
		this.setSize(640,400);
		this.setVisible(true);
	}
	
	private Container createCenterPanel() {
		Container CenterPanel = new Container();
		CenterPanel.setLayout(new GridLayout(1,3));
		CenterPanel.add(createAPanel());
		CenterPanel.add(createHPanel());
		CenterPanel.add(createIMPanel());
		return CenterPanel;
	}
	
	private Container createSouthPanel() {
		Container southPanel = new Container();
		southPanel.setLayout(new GridLayout(1,2));
		southPanel.setSize(400,400);
		southPanel.add(createShipPanel());
		southPanel.add(createRotatePanel());
		return southPanel;
	}
	
	private Container createRotatePanel() {
		Container rotatePanel = new Container();
		rotatePanel.setLayout(new BorderLayout());
		JButton viewMap = new JButton("View Influence Map");
		viewMap.addMouseListener(new ShowButtonAction(this));
		rotatePanel.add(viewMap, BorderLayout.NORTH);
		setOutText(new JTextField("lookat me!"));
		getOutText().setText("Welcome To Battleships. Place ships on the middle grid");
		getOutText().setEditable(false);
		rotatePanel.add(getOutText());
		return rotatePanel;
	}
	
	private Container createShipPanel() {
		Container shipPanel = new Container();
		shipPanel.setLayout(new GridLayout(4,2));
		shipPanel.add(createTopShipPanel());
		shipPanel.add(createTopShipLabelPanel());
		shipPanel.add(createBottomShipPanel());
		shipPanel.add(createFlowPanel());
		return shipPanel;
	}
	
	private Container createBottomShipPanel() {
		Container bottomShipPanel = this.createFlowPanel();
		bottomShipPanel.add(createRotateButton());
		bottomShipPanel.add(createQuitButton());
		return bottomShipPanel;
	}
	
	private JButton createRotateButton() {
		JButton rotateButton = new JButton("Rotate Ship");
		rotateButton.addMouseListener(new RotateButtonAction(this));
		return rotateButton;
	}
	
	private JButton createQuitButton() {
		JButton quitButton = new JButton("Quit");
		quitButton.addMouseListener(new QuitButtonAction());
		return quitButton;
	}
	
	private JButton createNewButton() {
		JButton NewButton = new JButton("New Game");
		NewButton.addMouseListener(new NewButtonAction(this));
		return NewButton;
	}
	
	private JButton createHideButton() {
		JButton hideButton = new JButton("Hide Influence Map");
		hideButton.addMouseListener(new HideButtonAction(this));
		return hideButton;
	}
	
	private JButton createDestButton() {
		JButton destButton = new JButton("Rotate");
		return destButton;
	}
	
	private Container createTopShipPanel() {
		Container topShipPanel = this.createFlowPanel();
		topShipPanel.add(createNewButton());
		topShipPanel.add(createHideButton());
		topShipPanel.add(createDestButton());
		return topShipPanel;
	}
	
	private Container createTopShipLabelPanel() {
		Container topShipLabelPanel = new Container();
		topShipLabelPanel.setLayout(new FlowLayout());
		return topShipLabelPanel;
	}
	
	private Container createGridPanel() {
		Container Panel = new Container();
		Panel.setLayout(new GridLayout());
		return Panel;
	}
	
	private Container createFlowPanel() {
		Container Panel = new Container();
		Panel.setLayout(new FlowLayout());
		return Panel;
	}
	
	private Container createAPanel() {
		Container APanel = this.createGridPanel();
		attackPanel = new AttackPanel();
		attackPanel.addMouseListener(new AttackMousePressListener(attackPanel,gameState));
		APanel.add(attackPanel);
		return APanel;
	}
	
	private Container createHPanel() {
		Container HPanel = this.createGridPanel();
		homePanel = new HomePanel();
		homePanel.addMouseListener(new HomeMousePressListener(this));
		HPanel.add(homePanel);
		return HPanel;
	}
	
	private Container createIMPanel() {
		Container IMPanel = this.createGridPanel();
		influenceMapPanel = new InfluencePanel();
		IMPanel.add(influenceMapPanel);
		return IMPanel;
	}
	
	public void setOut(String s)
	{
		getOutText().setText(s);
	}
	
    public String deploy(int i, int j)
	{
		String out1= "";
		
		out1=this.placeAir(i,j);
		out1= out1 + "\n" +this.placeBattle(i,j);
		out1= out1 + "\n" +this.placeDest(i,j);
		out1= out1 + "\n" +this.placeSub(i,j);
		out1= out1 + "\n" +this.placeMine(i,j);	
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
				if ( gameState.playerHomeGrid.getGridVal(i,j) == 1)
					MissIcon.paint(attackPanelGraphics,(j*20),(i*20));
				else
				if ( gameState.isCompHomeGridLessThanMinus1(i,j))
					HitIcon.paint(attackPanelGraphics,(j*20),(i*20));
				
				
			}
		}

	}
			
	public void reset()
	{
		 i = 0;
		 j = 0;
		 this.initializeVariables();
		 this.finishLayout();
	}
		
	public String placeAir(int i, int j)
	{
		String out ="";
		if(!gameState.playerHomeGrid.isAirPlaced())
		{
			if(isShipRotatedHorizonally())
			{
				boolean valid;
				valid = gameState.playerHomeGrid.addAir(i,j,0);
		
				Graphics hp = homePanel.getGraphics();
		
				if(valid)
				{	
					AircraftCarrierH.paint(hp,(j*20),(i*20));
					out = out + gameState.playerHomeGrid.toString();
					// gameState.playerHomeGrid.setAirPlaced(true);
					getOutText().setText("Air Placed");
				}
				else
				{
					getOutText().setText("Aircraft Carrier Will Not Fit Here");
					out ="not valid";
					out = out + gameState.playerHomeGrid.toString();
				}	
			}
		
		else 
		{
			boolean valid;
			valid = gameState.playerHomeGrid.addAir(i,j,1);
			if(valid)
			{
				Graphics hp = homePanel.getGraphics();	
				AircraftCarrier.paint(hp,(j*20),(i*20));
				out = out + gameState.playerHomeGrid.toString();
				// gameState.playerHomeGrid.setAirPlacedTrue();
				getOutText().setText("Air Placed");
			}
			else
			{
				out ="not valid";
				out = out + gameState.playerHomeGrid.toString();
			}	
		
		}
		}
		return out;
	}
	
	
	public String placeBattle(int i, int j)
	{
		String out ="";
		if()
		{
			if(isShipRotatedHorizonally())
			{
				boolean valid;
				valid = gameState.playerHomeGrid.addBattle(i,j,0);
		
				Graphics hp = homePanel.getGraphics();
		
				if(valid)
				{	
					BattleshipH.paint(hp,(j*20),(i*20));
					out = out + gameState.playerHomeGrid.toString();
					battlePlaced = true;
				//	outText.setText("Battleship Placed");
				}
				else
				{
					out ="not valid";
					out = out + gameState.playerHomeGrid.toString();
					getOutText().setText("Battleships Will Not Fit Here");
				}	
			}
		
		else 
		{
			boolean valid;
			valid = gameState.playerHomeGrid.addBattle(i,j,1);
			if(valid)
			{
				Graphics hp = homePanel.getGraphics();	
				Battleship.paint(hp,(j*20),(i*20));
				out = out + gameState.playerHomeGrid.toString();
				battlePlaced = true;
				getOutText().setText("Battleship Placed");
			}
			else
			{
				out ="not valid";
				out = out + gameState.playerHomeGrid.toString();
				getOutText().setText("Battleships Will Not Fit Here");
			}	
		
		}
		}
		return out;
	}	
	

	public String placeDest(int i, int j)
	{
		String out ="";
		if(gameState.playerHomeGrid.isAirPlaced() &&  battlePlaced && ! destPlaced)
		{
			if(isShipRotatedHorizonally())
			{
				boolean valid;
				valid = gameState.playerHomeGrid.addDest(i,j,0);
		
				Graphics hp = homePanel.getGraphics();
		
				if(valid)
				{	
					DestroyerH.paint(hp,(j*20),(i*20));
					out = out + gameState.playerHomeGrid.toString();
					destPlaced = true;
					getOutText().setText("Destroyer Placed");
				}
				else
				{
					out ="not valid";
					out = out + gameState.playerHomeGrid.toString();
					getOutText().setText("Destroyer Will Not Fit Here");
				}	
			}
		
		else 
		{
			boolean valid;
			valid = gameState.playerHomeGrid.addDest(i,j,1);
			if(valid)
			{
				Graphics hp = homePanel.getGraphics();	
				Destroyer.paint(hp,(j*20),(i*20));
				out = out + gameState.playerHomeGrid.toString();
				destPlaced = true;
				getOutText().setText("Destroyer Placed");
			}
			else
			{
				out ="not valid";
				out = out + gameState.playerHomeGrid.toString();
				
			}	
		
		}
		}
		return out;
	}
	
	
	public String placeSub(int i, int j)
	{
		String out ="";
		if(gameState.playerHomeGrid.isAirPlaced() && battlePlaced && destPlaced && !subPlaced)
		{
			if(isShipRotatedHorizonally())
			{
				boolean valid;
				valid = gameState.playerHomeGrid.addSub(i,j,0);
		
				Graphics hp = homePanel.getGraphics();
		
				if(valid)
				{	
					SubmarineH.paint(hp,(j*20),(i*20));
					out = out + gameState.playerHomeGrid.toString();
					subPlaced = true;
					getOutText().setText("Submarine Placed");
				}
				else
				{
					out ="not valid";
					out = out + gameState.playerHomeGrid.toString();
					getOutText().setText("Submarine Will Not Fit Here");
				}	
			}
		
		else 
		{
			boolean valid;
			valid = gameState.playerHomeGrid.addSub(i,j,1);
			if(valid)
			{
				Graphics hp = homePanel.getGraphics();	
				Submarine.paint(hp,(j*20),(i*20));
				out = out + gameState.playerHomeGrid.toString();
				subPlaced = true;
				getOutText().setText("Submarine Placed");
			}
			else
			{
				out ="not valid";
				out = out + gameState.playerHomeGrid.toString();
				getOutText().setText("Submarine Will Not Fit Here");
			}	
		
		}
		}
		return out;
	}	
	
	
	
	public String placeMine(int i, int j)
	{
		String out ="";
		if(gameState.playerHomeGrid.isAirPlaced() && battlePlaced && destPlaced && subPlaced && !minePlaced)
		{
			if(isShipRotatedHorizonally())
			{
				boolean valid;
				valid = gameState.playerHomeGrid.addMine(i,j,0);
		
				Graphics hp = homePanel.getGraphics();
		
				if(valid)
				{	
					MinesweeperH.paint(hp,(j*20),(i*20));
					out = out + gameState.playerHomeGrid.toString();
					minePlaced = true;
					getOutText().setText("Minesweeper Placed");
				}
				else
				{
					out ="not valid";
					out = out + gameState.playerHomeGrid.toString();
					getOutText().setText("Minesweeper Will Not Fit Here");
				}	
			}
		
		else 
		{
			boolean valid;
			valid = gameState.playerHomeGrid.addMine(i,j,1);
			if(valid)
			{
				Graphics hp = homePanel.getGraphics();	
				Minesweeper.paint(hp,(j*20),(i*20));
				out = out + gameState.playerHomeGrid.toString();
				minePlaced = true;
				getOutText().setText("Minesweeper Placed");
			}
			else
			{
				out ="not valid";
				out = out + gameState.playerHomeGrid.toString();
				getOutText().setText("Minesweeper Will Not Fit Here");
			}	
		
		}
		
		if(gameState.playerHomeGrid.isAirPlaced() && battlePlaced && destPlaced && subPlaced && minePlaced)
				this.endDeploymentPhase();
			
		}
		
		
		return out;
	}	
	
	public boolean rotate()
	{
		setHoriz(!isShipRotatedHorizonally());
		if(isShipRotatedHorizonally()&&!gameState.isBothPlayerAndAgentShipsDeployed())
		getOutText().setText("Ship Will Be Placed Horizontally");
		if(!isShipRotatedHorizonally()&&!gameState.isBothPlayerAndAgentShipsDeployed())
		getOutText().setText("Ship Will Be Placed Vertically");
		return isShipRotatedHorizonally();
	}
	
	public void showMap()
	{
		showMap = true;
		this.paintMap();
		getOutText().setText("Influence Map shown");
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
		
		getOutText().setText("Influence Map Hidden");
	}	
	
	public boolean getGameOver()
	{
		return gameState.IsGameOver();
	}

	
	

	
	
	

	/*
	public void startDeployment()
	{
		deployment= true;
	}*/
	
	public void endDeploymentPhase()
	{
		if( minePlaced &&  destPlaced && subPlaced &&	battlePlaced &&  gameState.playerHomeGrid.isAirPlaced())
		 gameState.SetAllShipsDeployed();
		getOutText().setText("All Ships Deployed, Player's Turn! Click on the left grid to fire shots");
		this. gameState.setPlayerTurn();
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
		
	
	public void paintPlayerAttackGrid()
	{
		this.gameState.setShipSunkStates();
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if( gameState.isCompHomegridRefIsminus3(i,j) &&  gameState.isAgentAirSunk())
				{					
					Graphics attackPanelGraphics =  attackPanel.getGraphics();	
					SunkIcon.paint(attackPanelGraphics,(j*20),(i*20));
				}
				
				if( gameState.isCompHomeGridRefMinus4(i,j) &&  gameState.isAgentBattleSunk())
				{					
					Graphics ap =  attackPanel.getGraphics();	
					SunkIcon.paint(ap,(j*20),(i*20));
				}
				
				//(agentMineSunk || agentDestSunk || agentSubSunk || agentBattleSunk
			}
		}
	}
	
	
	
	
	
	public void agentShot(int X, int Y)
		
	{
		if( gameState.agentTurn &&  gameState.isBothPlayerAndAgentShipsDeployed())
		{
		int sqrVal =  gameState.playerHomeGrid.getGridVal(X,Y);
						
						if(sqrVal < 0 || sqrVal==1)
						{
							System.out.println("Shot already taken! Have another go"); 
						}
							
						if(sqrVal == 0)
						{
							System.out.println( gameState.playerHomeGrid.shot(X,Y));
							 gameState.compHomeGrid.set(X,Y,1);
							 gameState.influenceMap.miss(X,Y);
							this.paintMap();
							Graphics hp =  homePanel.getGraphics();	
							MissIcon.paint(hp,(Y*20),(X*20));
							getOutText().setText("Agent Has Missed. Player's Turn");
							this. gameState.setPlayerTurn();
							 outText.setText( gameState.turnToString());
						}
						
						if(sqrVal > 1)
						{
							System.out.println( gameState.playerHomeGrid.shot(X,Y));
							 gameState.compHomeGrid.set(X,Y,8);
							 gameState.influenceMap.hit(X,Y);
							Graphics hp =  homePanel.getGraphics();	
							HitIcon.paint(hp,(Y*20),(X*20));
							getOutText().setText("Agent Has Hit One Of your ships! Agent's Turn again");
							this.paintMap();
							
						}
						
						System.out.println("compAtt");						
						System.out.println( gameState.compHomeGrid.toString());
						
						if(sqrVal==0)
							this. gameState.setPlayerTurn();
				
		}		
		
		System.out.println("Map is \n" +  gameState.influenceMap.toString());
		
		
	}
		

	


	private void setHoriz(boolean horiz) {
		this.horiz = horiz;
	}

	private boolean isShipRotatedHorizonally() {
		return horiz;
	}

	public void setOutText(JTextField outText) {
		this. outText = outText;
	}

	public JTextField getOutText() {
		return  outText;
	}	
}
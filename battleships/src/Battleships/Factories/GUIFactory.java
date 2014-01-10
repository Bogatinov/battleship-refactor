package Battleships.Factories;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Battleships.GUI;
import Battleships.Adapters.AttackMousePressListener;
import Battleships.Adapters.HideButtonAction;
import Battleships.Adapters.HomeMousePressListener;
import Battleships.Adapters.NewButtonAction;
import Battleships.Adapters.QuitButtonAction;
import Battleships.Adapters.RotateButtonAction;
import Battleships.Adapters.ShowButtonAction;
import Battleships.Graphics.InfluencePanel;
import Battleships.Graphics.PlayerPanel;
import Enums.Orientation;

public class GUIFactory {
	private GUI gui;
	public GUIFactory(GUI gui) {
		this.gui = gui;
		this.setLayout();
		this.initializeVariables();
		this.setContentPane();
		this.finishLayout();
	}
	
	public void resetLayout() {
		this.setLayout();
		this.initializeVariables();
		this.setContentPane();
		this.finishLayout();
	}
	
	private void initializeVariables() {
		gui.orientation = Orientation.Vertical;
		gui.showMap= true;
	}
	
	private void setLayout() {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setResizable(false);
	}
	
	private void setContentPane() {
		Container contentPane = gui.getContentPane();
		contentPane.setLayout(new BorderLayout(2,1));
		contentPane.add(createCenterPanel(),BorderLayout.CENTER);
		contentPane.add(createSouthPanel(),BorderLayout.SOUTH);
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
		viewMap.addMouseListener(new ShowButtonAction(this.gui));
		rotatePanel.add(viewMap, BorderLayout.NORTH);
		this.gui.setOutText(new JTextField("lookat me!"));
		this.gui.setOut("Welcome To Battleships. Place ships on the middle grid");
		this.gui.outText.setEditable(false);
		rotatePanel.add(this.gui.outText);
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
		rotateButton.addMouseListener(new RotateButtonAction(this.gui));
		return rotateButton;
	}
	
	private JButton createQuitButton() {
		JButton quitButton = new JButton("Quit");
		quitButton.addMouseListener(new QuitButtonAction());
		return quitButton;
	}
	
	private JButton createNewButton() {
		JButton NewButton = new JButton("New Game");
		NewButton.addMouseListener(new NewButtonAction(this.gui));
		return NewButton;
	}
	
	private JButton createHideButton() {
		JButton hideButton = new JButton("Hide Influence Map");
		hideButton.addMouseListener(new HideButtonAction(this.gui));
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
		gui.attackPanel = new PlayerPanel();
		gui.attackPanel.addMouseListener(new AttackMousePressListener(gui.attackPanel,gui.gameState));
		APanel.add(gui.attackPanel);
		return APanel;
	}
	
	private Container createHPanel() {
		Container HPanel = this.createGridPanel();
		gui.homePanel = new PlayerPanel();
		gui.homePanel.addMouseListener(new HomeMousePressListener(this.gui));
		HPanel.add(gui.homePanel);
		return HPanel;
	}
	
	private Container createIMPanel() {
		Container IMPanel = this.createGridPanel();
		gui.influenceMapPanel = new InfluencePanel();
		IMPanel.add(gui.influenceMapPanel);
		return IMPanel;
	}
	
	private void finishLayout() {
		gui.pack();
		gui.setSize(640,400);
		gui.setVisible(true);
	}
}

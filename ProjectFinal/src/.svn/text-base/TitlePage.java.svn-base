import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class TitlePage extends JFrame {

	static TitlePage frame = new TitlePage("Poker Memory Game");
	JButton easy = new JButton("Easy Level");
	JButton pair = new JButton("Equal Pair Level");
	JButton trio = new JButton("Rank Trio Level");
	JButton full = new JButton("Full House Level");
	JButton straight = new JButton("Straight Flush Level");
	JButton combo = new JButton("Combo Level");

	//Constructor
	public TitlePage(String name){
		super(name);

		//titlePage.setLocation(width, height);
		//titlePage.setSize(width, height);

		//titlePage.setVisible(true);
	}
	public void addComponentsToPane(final Container pane){
		final JPanel titlePage = new JPanel();
		ImageIcon ii = new ImageIcon("images/StartScreen.jpg");
		JLabel lable = new JLabel(ii);
		JScrollPane jsp = new JScrollPane(lable);
		titlePage.add(easy);
		titlePage.add(pair);
		titlePage.add(trio);
		titlePage.add(full);
		titlePage.add(straight);
		titlePage.add(combo);

		//Left to right component orientation is selected by default
		titlePage.setComponentOrientation(
				ComponentOrientation.LEFT_TO_RIGHT);

		easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				//Check the selection
				if (easy.getText().equalsIgnoreCase("Easy Level")) {
					try{
						MemoryGame instance = new MemoryGame();
						instance.newGame("easy");
						TitlePage.frame.dispose();
						//titlePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(IOException ioe) {
						System.out.println(ioe);
					}
				} 


			}
		});
		pair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				//Check the selection
				if (pair.getText().equalsIgnoreCase("Equal Pair Level")) {
					try{
						EqualPairLevel.equalPairLevel=true;
						RankTrioLevel.rankTrioLevel=false;
						FullHouseLevel.fullHouseLevel=false;
						StraightFlushLevel.straightFlushLevel=false;
						MemoryGame instance = new MemoryGame();
						instance.newGame("medium");
						TitlePage.frame.dispose();
						//titlePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(IOException ioe) {
						System.out.println(ioe);
					}
				} 


			}
		});
		trio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				//Check the selection
				if (trio.getText().equalsIgnoreCase("Rank Trio Level")) {
					try{
						EqualPairLevel.equalPairLevel=false;
						RankTrioLevel.rankTrioLevel=true;
						FullHouseLevel.fullHouseLevel=false;
						StraightFlushLevel.straightFlushLevel=false;
						MemoryGame instance = new MemoryGame();
						instance.newGame("trio");
						TitlePage.frame.dispose();
						//titlePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(IOException ioe) {
						System.out.println(ioe);
					}
				} 


			}
		});
		full.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				//Check the selection
				if (full.getText().equalsIgnoreCase("Full House Level")) {
					try{
						EqualPairLevel.equalPairLevel=false;
						RankTrioLevel.rankTrioLevel=false;
						FullHouseLevel.fullHouseLevel=true;
						StraightFlushLevel.straightFlushLevel=false;
						MemoryGame instance = new MemoryGame();
						instance.newGame("house");
						TitlePage.frame.dispose();
						//titlePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(IOException ioe) {
						System.out.println(ioe);
					}
				} 


			}
		});
		straight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				//Check the selection
				if (straight.getText().equalsIgnoreCase("Straight Flush Level")) {
					try{
						EqualPairLevel.equalPairLevel=false;
						RankTrioLevel.rankTrioLevel=false;
						FullHouseLevel.fullHouseLevel=false;
						StraightFlushLevel.straightFlushLevel=true;
						MemoryGame instance = new MemoryGame();
						instance.newGame("straight");
						TitlePage.frame.dispose();
						//titlePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(IOException ioe) {
						System.out.println(ioe);
					}
				} 


			}
		});
		combo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				//Check the selection
				if (combo.getText().equalsIgnoreCase("Combo Level")) {
					try{
						MemoryGame instance = new MemoryGame();
						instance.newGame("combo");
						TitlePage.frame.dispose();
						//titlePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					catch(IOException ioe) {
						System.out.println(ioe);
					}
				} 


			}
		});
		pane.add(titlePage, BorderLayout.SOUTH);
		pane.add(jsp,BorderLayout.NORTH);
	}
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	public static void createAndShowGUI() {
		//Create and set up the window.
		
		frame.setBounds(200, 200,1,1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		//Display the window.
		frame.pack();
		frame.setBackground(Color.black);
		frame.setVisible(true);
	}
}


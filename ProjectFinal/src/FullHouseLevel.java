import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class FullHouseLevel extends RankTrioLevel {
	static boolean fullHouseLevel=false;	
	private int[] array=new int[15];

	protected FullHouseLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Full House Level");
		cardsToTurnUp = 5;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}
	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// Record the player's turn
			this.turnsTakenCounter.increment();
			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
			Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
			Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
			if(score.fullHouseLevel(card,otherCard1,otherCard2,otherCard3,otherCard4))
			{
					Score.increment();
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses=0;
					for(int i = 0; i < getRowsPerGrid()*getCardsPerRow(); i++)
					{
						if(!this.grid.get(i).isFaceUp())
						{
							array[this.grid.get(i).getNumber()]++;
							System.out.println(this.grid.get(i).getNumber()+this.grid.get(i).getSuit());
						}
					}
					if(gameOver())
					{
						try {
							AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/win.wav"));
							Clip clip = AudioSystem.getClip();
							clip.open(audio);
							clip.start();
						}

						catch(UnsupportedAudioFileException uae) {
							System.out.println(uae);
						}
						catch(IOException ioe) {
							System.out.println(ioe);
						}
						catch(LineUnavailableException lua) {
							System.out.println(lua);
						}
						JOptionPane.showMessageDialog(this.mainFrame, "There are no more Full House. You Won!");
					}
					for(int i = 0; i < 15; i++)
					{
						System.out.println(array[i]);
						array[i]=0;
					}
			}
			else 
			{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
			}
		}
		return true;
	}
	protected boolean  gameOver(){

		boolean trio=true;
		boolean pair=true;
		for(int i = 0; i < 15; i++)
		{
			if(array[i]>3)
			{
				trio=false;
			}
			if(array[i]>2)
			{
				pair=false;
			}
		}
		return (trio && pair);
	}
}

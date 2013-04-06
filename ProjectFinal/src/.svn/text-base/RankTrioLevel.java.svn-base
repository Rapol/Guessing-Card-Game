import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RankTrioLevel extends EqualPairLevel {
	static int scoreTrio;
	static boolean rankTrioLevel;
	private int[] array=new int[15];
	// TRIO LEVEL: The goal is to find, on each turn, three cards with the same rank

	protected RankTrioLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Trio Level");
		cardsToTurnUp = 3;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}

	@Override
	protected void makeDeck() {
		// In Trio level the grid consists of distinct cards, no repetitions
		ImageIcon cardIcon[] = this.loadCardIcons();

		//back card
		ImageIcon backIcon = cardIcon[TotalCardsPerDeck];

		int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
		for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
		{
			cardsToAdd[i] = i;
		}

		// randomize the order of the deck
		this.randomizeIntArray(cardsToAdd);

		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, randomized
			int num = cardsToAdd[i];
			// make the card object and add it to the panel
			String rank = cardNames[num].substring(0, 1);
			String suit = cardNames[num].substring(1, 2);
			this.grid.add( new Card(this, cardIcon[num], backIcon, num, rank, suit));
			System.out.println(this.grid.get(i).getNumber()+this.grid.get(i).getSuit());
		}
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) 
	{
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
			if(score.rankTrioLevel(card, otherCard1, otherCard2)) 
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
					JOptionPane.showMessageDialog(this.mainFrame, "There are no more trios. You Won!");
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

		for(int i = 0; i < 15; i++)
		{
			if(array[i]>3)
			{
				return false;
			}
		}
		return true;
	}

}

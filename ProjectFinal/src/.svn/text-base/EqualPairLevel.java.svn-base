import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EqualPairLevel extends EasyLevel {
	static boolean equalPairLevel;
	Score score=new Score();

	protected EqualPairLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Medium Level");
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// there are two cards faced up
			// record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard = (Card) this.turnedCardsBuffer.get(0);
			// the cards match, so remove them from the list (they will remain face up)
			if( score.equalPairLevel(card,otherCard))
			{
				Score.increment();
				this.turnsTakenCounter.update();
				this.turnedCardsBuffer.clear();
				Score.numberOfGuesses=0;
				
			}
			// the cards do not match, so start the timer to turn them down
			else this.turnDownTimer.start();
		}
		if(gameOver1())
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
			JOptionPane.showMessageDialog(this.mainFrame, "You Won!");
		}
		return true;
	}

	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.turnedCardsBuffer.size() < getCardsToTurnUp()) 
		{
			return true;

		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}

	protected boolean  gameOver1(){

		int count=0;
		for(int i = 0; i < getRowsPerGrid()*getCardsPerRow(); i++)
		{
			if(!this.grid.get(i).isFaceUp())
			{
				count++;
			}
		}
		if(count==0)
		{
			return true;
		}
		return false;
	}

	@Override
	protected String getMode() {
		// TODO Auto-generated method stub
		return "MediumMode";
	}


}

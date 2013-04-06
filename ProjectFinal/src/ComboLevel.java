import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ComboLevel extends FullHouseLevel
{
	String input;
	String response;
	private int bonus;
	private Random rand=new Random();
	private int[] array=new int[15];
	static boolean comboLevel = false;
	protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Combo Level");
		String rank="";
		bonus=rand.nextInt(13)+2;
		if(bonus==11)
		{
			rank="J";
		}
		else if(bonus==12)
		{
			rank="Q";
		}
		else if(bonus==13)
		{
			rank="K";
		}
		else if(bonus==14)
		{
			rank="A";
		}
		else
		{
			rank=""+bonus;
		}
		JOptionPane.showMessageDialog(this.mainFrame, "The Combo Level combines the other poker hands plus a new Combo Level.\n" +
				"The Combo Level consist of three new poker hands:\n" +
				"1) Four of a kind(same rank). The value of the hand is 500 plus 4 times the rank minus the number of guesses\n" +
				"2) Two pairs. The value of the hand is 150 minus number of guesses\n" +
				"3)Special bonus level. Every 10 turns you need to the find the pair that randomly gets generated. The value of the hand is 70 minus number of guesses.\nBe careful that sometimes there are no more pairs of the one chosen so remember well and use the pairs wisely!\n\n" +
				"						Your first special pair is "+rank+". Good Luck!");

	}
	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			//get the other card (which was already turned up)
			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
			Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
			Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);

			//winning hand is of type straight flush (best possible winning hand)
			if(score.straightFlushLevel(card, otherCard1, otherCard2, otherCard3, otherCard4)){
				EqualPairLevel.equalPairLevel=false;
				RankTrioLevel.rankTrioLevel=false;
				FullHouseLevel.fullHouseLevel=false;
				StraightFlushLevel.straightFlushLevel=true;
				comboLevel = false;
				Score.increment();
				int n = JOptionPane.showConfirmDialog(
						this.mainFrame,
						"You found a Straight Flush! Your score will be: "+Score.score+". Would you like to Pass?",
						"Memory Game",
						JOptionPane.YES_NO_OPTION);
				if(n==0){
					Score.score-=Score.handScore;
					// The cards do not match, so start the timer to turn them down
					this.turnDownTimer.start();
					this.turnsTakenCounter.update();
				}
				else{
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses = 0;
				}
			}
			//winning hand is of type full house
			else if(score.fullHouseLevel(card, otherCard1, otherCard2, otherCard3, otherCard4)){

				EqualPairLevel.equalPairLevel=false;
				RankTrioLevel.rankTrioLevel=false;
				FullHouseLevel.fullHouseLevel=true;
				StraightFlushLevel.straightFlushLevel=false;
				comboLevel = false;
				Score.increment();
				int n = JOptionPane.showConfirmDialog(
						this.mainFrame,
						"You found a Full House! Your score will be: "+Score.score+". Would you like to Pass?",
						"Memory Game",
						JOptionPane.YES_NO_OPTION);
				if(n==0){
					Score.score-=Score.handScore;
					this.turnDownTimer.start();
					this.turnsTakenCounter.update();
				}
				else{
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses = 0;
					checkGameOver();
				}
			}
			//winning hand is of type four of a kind
			else if(score.comboLevel(card, otherCard1, otherCard2, otherCard3, otherCard4)){
				EqualPairLevel.equalPairLevel=false;
				RankTrioLevel.rankTrioLevel=false;
				FullHouseLevel.fullHouseLevel=false;
				StraightFlushLevel.straightFlushLevel=false;
				comboLevel = true;
				Score.increment();
				int n = JOptionPane.showConfirmDialog(
						this.mainFrame,
						"You found a combo! Your score will be: "+Score.score+". Would you like to Pass?",
						"Memory Game",
						JOptionPane.YES_NO_OPTION);
				if(n==0){
					Score.score-=Score.handScore;
					// The cards do not match, so start the timer to turn them down
					this.turnDownTimer.start();
					this.turnsTakenCounter.update();
				}
				else{
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses = 0;
					checkGameOver();
				}
			}
			else if(score.twoEqualPairFor5(card, otherCard1, otherCard2, otherCard3, otherCard4))
			{
				Score.score+=150-Score.numberOfGuesses;
				int n = JOptionPane.showConfirmDialog(
						this.mainFrame,
						"You found two Pairs! Your score will be: "+Score.score+". Would you like to Pass?",
						"Memory Game",
						JOptionPane.YES_NO_OPTION);
				if(n==0){
					Score.score=Score.score-150+Score.numberOfGuesses;
					// The cards do not match, so start the timer to turn them down
					this.turnDownTimer.start();
					this.turnsTakenCounter.update();
				}
				else{
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses = 0;
					checkGameOver();
				}
			}
			//			//winning hand is of type rank trio
			else if (score.rankTrioFor5(card, otherCard1, otherCard2, otherCard3, otherCard4)){
				EqualPairLevel.equalPairLevel=false;
				RankTrioLevel.rankTrioLevel=true;
				FullHouseLevel.fullHouseLevel=false;
				StraightFlushLevel.straightFlushLevel=false;
				comboLevel = false;
				Score.increment();
				int n = JOptionPane.showConfirmDialog(
						this.mainFrame,
						"You found a trio! Your score will be: "+Score.score+". Would you like to Pass?",
						"Memory Game",
						JOptionPane.YES_NO_OPTION);
				if(n==0){
					Score.score-=Score.handScore;
					// The cards do not match, so start the timer to turn them down
					this.turnDownTimer.start();
					this.turnsTakenCounter.update();
				}
				else{
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses = 0;
					checkGameOver();
				}
			}
			//winning hand is of type equal pair
			else if(score.equalPairFor5(card, otherCard1, otherCard2, otherCard3, otherCard4)){
				EqualPairLevel.equalPairLevel=true;
				RankTrioLevel.rankTrioLevel=false;
				FullHouseLevel.fullHouseLevel=false;
				StraightFlushLevel.straightFlushLevel=false;
				comboLevel = false;
				Score.increment();
				int n;
				if(Score.pair==bonus)
				{
					n = JOptionPane.showConfirmDialog(
							this.mainFrame,
							"Congratulations you found the Pair!. Your score will be: "+(Score.getScore()+60)+". Would you like to Pass?",
							"Memory Game",
							JOptionPane.YES_NO_OPTION);
				}
				else{
					n = JOptionPane.showConfirmDialog(
							this.mainFrame,
							"You found a Pair! Your score will be: "+Score.score+". Would you like to Pass?",
							"Memory Game",
							JOptionPane.YES_NO_OPTION);
				}
				if(n==0){
					Score.score-=Score.handScore;
					// The cards do not match, so start the timer to turn them down
					this.turnDownTimer.start();
					this.turnsTakenCounter.update();
				}
				else{
					if(Score.pair==bonus)
					{Score.score+=60;}
					this.turnsTakenCounter.update();
					this.turnedCardsBuffer.clear();
					Score.numberOfGuesses = 0;
					checkGameOver();
				}
			}
			else{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
			}
			specialBonus();
		}
		return true;
	}
	public void specialBonus()
	{
		String rank="";
		if(this.turnsTakenCounter.getNumOfTurns()%10==0 && this.turnsTakenCounter.getNumOfTurns()!=0)
		{
			bonus=rand.nextInt(13)+2;
			if(bonus==11)
			{
				rank="J";
				JOptionPane.showMessageDialog(this.mainFrame, "Find one pair of "+rank+"s in the next 10 turns for bonus points");
			}
			else if(bonus==12)
			{
				rank="Q";
				JOptionPane.showMessageDialog(this.mainFrame, "Find one pair of "+rank+"s in the next 10 turns for bonus points");
			}
			else if(bonus==13)
			{
				rank="K";
				JOptionPane.showMessageDialog(this.mainFrame, "Find one pair of "+rank+"s in the next 10 turns for bonus points");
			}
			else if(bonus==14)
			{
				rank="A";
				JOptionPane.showMessageDialog(this.mainFrame, "Find one pair of "+rank+"s in the next 10 turns for bonus points");
			}
			else{
				JOptionPane.showMessageDialog(this.mainFrame, "Find one pair of "+bonus+"s in the next 10 turns for bonus points");
			}
		}
	}
	protected boolean  gameOver(){

		for(int i = 0; i < 15; i++)
		{
			if(array[i]>=2)
			{
				return false;
			}
		}
		return true;
	}
	private void checkGameOver()
	{
		for(int i = 0; i < getRowsPerGrid()*getCardsPerRow(); i++)
		{
			if(!this.grid.get(i).isFaceUp())
			{
				array[this.grid.get(i).getNumber()]++;
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
			JOptionPane.showMessageDialog(this.mainFrame, "There are no more Poker Hands. You Won!");
		}
		for(int i = 0; i < 15; i++)
		{
			array[i]=0;
		}
	}
}

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class StraightFlushLevel extends FullHouseLevel 
{
	static int highestCard;
	private int[] s=new int[15];
	private int[] h=new int[15];
	private int[] c=new int[15];
	private int[] d=new int[15];
	static boolean straightFlushLevel=false;
	protected StraightFlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Full House Level");
	}
	protected boolean addToTurnedCardsBuffer(Card card) 
	{
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
			Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
			Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
			if(score.straightFlushLevel(card,otherCard1,otherCard2,otherCard3,otherCard4))
			{
				
				Score.increment();
				this.turnsTakenCounter.update();
				this.turnedCardsBuffer.clear();
				Score.numberOfGuesses=0;
				for(int i = 0; i < getRowsPerGrid()*getCardsPerRow(); i++)
				{
					if(!this.grid.get(i).isFaceUp())
					{
						int x=this.grid.get(i).getNumber();
						String y=this.grid.get(i).getSuit();
						System.out.println(x+y);
						if(y.equals("c"))
						{
							c[x]++;
						}
						else if(y.equals("s"))
						{
							s[x]++;
						}
						else if(y.equals("h"))
						{
							h[x]++;
						}
						else
						{
							d[x]++;
						}
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
					JOptionPane.showMessageDialog(this.mainFrame, "There are no more Straight Flush. You Won!");
				}
				for(int i = 0; i < 15; i++)
				{
					s[i]=0;
					d[i]=0;
					h[i]=0;
					c[i]=0;
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
	for(int x=2;x<14;x++)
	{
		if(c[x]==1 && c[x+1]==1 && c[x+2]==1 && c[x+3]==1 && c[x+4]==1)
		{
			return false;
		}
		if(s[x]==1 && s[x+1]==1 && s[x+2]==1 && s[x+3]==1 && s[x+4]==1)
		{
			return false;
		}
		if(h[x]==1 && h[x+1]==1 && h[x+2]==1 && h[x+3]==1 && h[x+4]==1)
		{
			return false;
		}
		if(d[x]==1 && d[x+1]==1 && d[x+2]==1 && d[x+3]==1 && d[x+4]==1)
		{
			return false;
		}
	}
	return true;
	}
}



public class Score 
{
	static int score=0;
	private static int quadRank;
	private static int trio;
	private static int trioRank;
	private static int highestCard;
	static int pair;
	static int numberOfGuesses=0;
	static int handScore;
	private int[] allRanks=new int[16];
	public Score()
	{
	}
	public static int getScore()
	{
		return score;
	}
	public boolean equalPairLevel(Card card,Card otherCard)
	{
		if( otherCard.getNum() == card.getNum())
		{
			return true;
		}
		return false;
	}
	// Checks to see if there are 4 cards with the same rank in the current hand.
	public boolean comboLevel(Card card, Card otherCard1, Card otherCard2, Card otherCard3, Card otherCard4){
		arrayOfCards(card,otherCard1,otherCard2,otherCard3,otherCard4);
		quadRank=0;
		for(int x=2;x<allRanks.length-1;x++)
		{
			if(allRanks[x]==4)
			{
				quadRank=x;
				return true;
			}
		}
		
		return false;
	}
	public boolean twoEqualPairFor5(Card card, Card otherCard1, Card otherCard2, Card otherCard3, Card otherCard4)
	{
		int check=0;
		arrayOfCards(card,otherCard1,otherCard2,otherCard3,otherCard4);
		for(int x=2;x<allRanks.length-1;x++)
		{
			if(allRanks[x]==2)
			{
				check++;
			}
		}
		return check==2;
	}
	public boolean equalPairFor5(Card card, Card otherCard1, Card otherCard2, Card otherCard3, Card otherCard4)
	{
		arrayOfCards(card,otherCard1,otherCard2,otherCard3,otherCard4);
		for(int x=2;x<allRanks.length-1;x++)
		{
			if(allRanks[x]==2)
			{
				pair=x;
				return true;
			}
		}
		return false;
		
	}
	public boolean rankTrioFor5(Card card, Card otherCard1, Card otherCard2, Card otherCard3, Card otherCard4)
	{
		arrayOfCards(card,otherCard1,otherCard2,otherCard3,otherCard4);
		for(int x=2;x<allRanks.length-1;x++)
		{
			if(allRanks[x]==3)
			{
				trio=3*card.getNumber();
				return true;
			}
		}
		return false;
	}
	public boolean rankTrioLevel(Card card,Card otherCard1,Card otherCard2)
	{
		if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank())))
		{
			trio=3*card.getNumber();
			return true;
		}
		return false;
	}
	public boolean fullHouseLevel(Card card,Card otherCard1,Card otherCard2,Card otherCard3,Card otherCard4)
	{
		arrayOfCards(card,otherCard1,otherCard2,otherCard3,otherCard4);
		trioRank=0;
		for(int x=2;x<allRanks.length-1;x++)
		{
			if(allRanks[x]==3)
			{
				trioRank=x;
			}
		}
		for(int x=2;x<allRanks.length-1;x++)
		{
			if(allRanks[x]==2 && trioRank!=0)
			{
				return true;
			}
		}
		return false;
	}
	public boolean straightFlushLevel(Card card,Card otherCard1,Card otherCard2,Card otherCard3,Card otherCard4)
	{
		arrayOfCards(card,otherCard1,otherCard2,otherCard3,otherCard4);
		// Five cards match the same suit, so remove them from the list (they will remain face up)
		if(card.getSuit().equals(otherCard1.getSuit()) && card.getSuit().equals(otherCard2.getSuit()) && card.getSuit().equals(otherCard3.getSuit()) && card.getSuit().equals(otherCard4.getSuit()))
		{
			for(int x=2;x<allRanks.length-1;x++)
			{
				if(allRanks[x]==1 && allRanks[x+1]==1 && allRanks[x+2]==1 && allRanks[x+3]==1 && allRanks[x+4]==1)
				{
					highestCard=x+4;
					return true;
				}
			}
		}
		return false;

	}
	public static void increment()
	{
		if(EqualPairLevel.equalPairLevel)
		{
			handScore=10-numberOfGuesses;
			score+=handScore;
		}
		else if(RankTrioLevel.rankTrioLevel)
		{
			handScore=46+trio-numberOfGuesses;
			score+=handScore;
		}
		else if(FullHouseLevel.fullHouseLevel)
		{
			handScore=700+trioRank*3-numberOfGuesses;
			score+=handScore;
		}
		else if(StraightFlushLevel.straightFlushLevel)
		{
			handScore=65000+100*highestCard-numberOfGuesses;
			score+=handScore;
		}
		else if(ComboLevel.comboLevel){
			handScore=500 + quadRank*4 - numberOfGuesses;
			score+= handScore;
		}

	}
	public static void numberOfGuesses()
	{
		numberOfGuesses++;
	}
	public static void reset()
	{
		numberOfGuesses=0;
		score = 0;
	}
	public static void resetNumberOfGuesses()
	{
		numberOfGuesses=0;
	}
	public void arrayOfCards(Card card,Card otherCard1,Card otherCard2,Card otherCard3,Card otherCard4)
	{
		for(int x=2;x<allRanks.length-1;x++)
		{
			allRanks[x]=0;
		}
		allRanks[card.getNumber()]+=1;
		allRanks[otherCard1.getNumber()]+=1;
		allRanks[otherCard2.getNumber()]+=1;
		allRanks[otherCard3.getNumber()]+=1;
		allRanks[otherCard4.getNumber()]+=1;
		for(int x=2;x<allRanks.length-1;x++)
		{
			System.out.println(x+" "+allRanks[x]);
		}
	}

}

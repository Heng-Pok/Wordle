package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * class to track the global win statistics
 */
public class GlobalLeaderboard implements Serializable {
	/**
	 * total wins by score (score=index+1)
	 */
	private int[] winCounts = new int[5];
	/**
	 * total wins by word and score (score=index+1)
	 */
	private HashMap<String, int[]> winCountsByWord = new HashMap<String, int[]>();;
	
	/**
	 * @return the overall win counts
	 */
	public int[] getWinCounts()
    {
    	return winCounts;
    }
    
    /**
     * @param word the word to get win counts for
     * @return the win counts for a particular word
     */
    public int[] getWinCounts(String word)
    {
    	return winCountsByWord.get(word);
    }
    
    /**
     * records a game win
     * @param guesses the number of guesses a user took to complete a game
     * @param word the target word of the game won
     */
    public void registerWin(int guesses, String word)
    {
    	winCounts[guesses-1] += 1;
    	if(winCountsByWord.containsKey(word))
    	{
    		winCountsByWord.get(word)[guesses-1] += 1;
    	}
    	else
    	{
    		winCountsByWord.put(word, new int[5]);
    		winCountsByWord.get(word)[guesses-1] += 1;
    	}
    }
}

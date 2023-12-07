package model;

import java.util.ArrayList;

/**
 * class to store details of a word that is the target of a wordle game
 * 
 * @authors Charles Scott and Parker Hines
 */
public class Word {
	
	/**
	 * the target word
	 */
	private String word;
	
	/**
	 * initializes a new word
	 * @param word the word to initialize with
	 */
	public Word(String word) {
		this.word = word.toUpperCase();
	}
	
	/**
	 * @return the target word
	 */
	public String getWord()
	{
		return word;
	}
	
	/**
	 * @param index the index of the character to get
	 * @return the character at the passed index of the target word
	 */
	public char getChar(int index)
	{
		return word.charAt(index);
	}
	
	/**
	 * @return the length of the target word
	 */
	public int length()
	{
		return word.length();
	}
	
	/**
	 * check the passed word against the target to see which letters are correct or partially correct
	 * @param check the word to check
	 * @return an array representing the match status of each character in the check word ('r'=right, 'w'=wrong, 'p'=incorrect position)
	 */
	public char[] checkWord(String check)
	{
		check = check.toUpperCase().strip();
		char[] retval = new char[word.length()];
		//array list to remember incorrect letters/duplicates
		ArrayList<Character> remainingWordChars = new ArrayList<>();
		ArrayList<Character> remainingCheckChars = new ArrayList<>();
		for(int i = 0; i<word.length(); i++)
		{
			if(check.length()-1 >= i && word.charAt(i) == check.charAt(i))
			{
				retval[i] = 'r';
			}
			else {
				retval[i] = 'w';
				//remember incorrect/required letter uses at 'w' indices
				remainingWordChars.add(word.charAt(i));
				remainingCheckChars.add(check.charAt(i));
				}
			
		}
		// loop through characters in guess associated with 'r' index in retval
		for (Character guessChar : remainingCheckChars) {
			// if character is also in remainingWordChars arraylist, this letter
			// was in the wrong position
			if (remainingWordChars.contains(guessChar)) {
				for (int i = 0; i < check.length(); i++) {
					if (retval[i] == 'w' && check.charAt(i) == guessChar) {
						retval[i] = 'p';
						remainingWordChars.remove(guessChar);
						break;
					}
				}
			}
		}
		return retval;
	}
}

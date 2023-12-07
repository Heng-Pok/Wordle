
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Internal representation of a Wordle game being played
 * 
 * @author Charles Scott
 */

public class Game {

	/**
	 * the object that holds the target word for the game
	 */
	private Word word;

	/**
	 * list of the words that the player has guessed
	 */
	private ArrayList<String> guesses = new ArrayList<String>();
	/**
	 * Stores the valid words that can be entered. each sub list has words of length
	 * index+5
	 */
	private static ArrayList<ArrayList<String>> dictionary;
	/**
	 * the word last randomly chosen to be the target word for each word length.
	 * each string has length index+5
	 */
	private static ArrayList<String> lastChosenWord;
	/**
	 * number of guesses
	 */
	private int numGuesses = 6;
	/**
	 * whether the game has been won
	 */
	public boolean won;
	/**
	 * whether the game is still running
	 */
	private boolean stillRunning = true;
	/**
	 * whether the game is in zenmode
	 */
	private boolean zenMode = false;
	
	/**
	 * hashmap of letters known to the player: mapping of 0 indicates that the letter is not in the word, mapping of 1 indicates the letter is in the word at least once
	 */
	public HashMap<Character, Integer> knownInformation = new HashMap<Character, Integer>();

	/**
	 * initializes the game with a target word length
	 * 
	 * @param wordLength the length that the target word for the game should be
	 */
	public Game(int wordLength) {
		constructorActual(wordLength);
	}

	/**
	 * testing method to initialize the game with a preset target word
	 * 
	 * @param target the target word to initialize with
	 */
	public Game(String target) {
		if (dictionary == null || dictionary.size() < target.length() - 4
				|| dictionary.get(target.length() - 5).size() == 0)
			fillDict();
		word = new Word(target);
	}

	/**
	 * default constructor to build a game with a word of length 5
	 */
	public Game() {
		constructorActual(5);
	}

	/**
	 * overload constructor for zenmode
	 * 
	 * @param zenMode determines whether the game should be in zen mode
	 */
	public Game(boolean zenMode) {
		this.zenMode = zenMode;
		constructorActual(5);
	}

	/**
	 * @return the game's target word
	 */
	public String getWord() {
		return word.getWord();
	}

	/**
	 * sets the maximum number of guesses
	 * 
	 * @param num the number of guesses to set to
	 */
	public void setNumGuess(int num) {
		numGuesses = num;
	}

	/**
	 * initializes a new game for a random word of wordlength
	 * 
	 * @param wordLength the length of the word to initialize with
	 */
	private void constructorActual(int wordLength) {
		if (dictionary == null || dictionary.size() < wordLength - 4 || dictionary.get(wordLength - 5).size() == 0)
			fillDict();
		if (lastChosenWord == null)
			lastChosenWord = new ArrayList<String>();
		Random rand = new Random();
		while (true) {
			ArrayList<String> dictForLen = dictionary.get(wordLength - 5);
			String checkWord = dictForLen.get(rand.nextInt(dictForLen.size() - 1));
			if (lastChosenWord.size() < wordLength - 4 || !checkWord.equals(lastChosenWord.get(wordLength - 5))) {
				word = new Word(checkWord);
				while (lastChosenWord.size() < wordLength - 4)
					lastChosenWord.add("");
				lastChosenWord.remove(wordLength - 5);
				lastChosenWord.add(wordLength - 5, checkWord);
				System.out.println(checkWord);
				break;
			}
		}
	}

	/**
	 * reads the dictionary file into the static dictionary variable
	 */
	private void fillDict() {
		if (dictionary == null)
			dictionary = new ArrayList<ArrayList<String>>();
		try {
			File dictFile = new File("dictionary.txt");
			Scanner fscanner = new Scanner(dictFile);
			while (fscanner.hasNext()) {
				String line = fscanner.nextLine();
				line = line.strip().toUpperCase();
				if (line.length() < 5) {
					continue;
				}
				while (dictionary.size() < line.length() - 4) {
					dictionary.add(new ArrayList<String>());
				}
				dictionary.get(line.length() - 5).add(line);
			}
			fscanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found!");
			e.printStackTrace();
		}
	}

	/**
	 * check if the passed guess is valid
	 * 
	 * @param guess a string representing a word guessed by the user
	 * @return whether or not the passed guess is valid
	 */
	public boolean checkValidGuess(String guess) {
		guess = guess.toUpperCase().strip();
		if (guess.length() != word.length() || !dictionary.get(guess.length() - 5).contains(guess) || !eligible())
			return false;
		return true;
	}

	/**
	 * inputs a guess
	 * 
	 * @param guess the guess to input
	 * @return an array representing whether each character in the word was right,
	 *         wrong, or in the wrong position
	 */
	public char[] guess(String guess) {
		guess = guess.toUpperCase().strip();
		if (guess.length() != word.length() || !dictionary.get(guess.length() - 5).contains(guess) || !eligible())
			return null;
		guesses.add(guess);
		stillRunning = eligible();
		char[] result = word.checkWord(guess);
		won = true;
		for (int i = 0; i < result.length; i++) {
			if (result[i] != 'r')
			{
				if(result[i] == 'p')
				{
					knownInformation.put(guess.charAt(i), 1);
				}
				else
				{
					knownInformation.put(guess.charAt(i), 0);
				}
				won = false;
			}
			else
			{
				knownInformation.put(guess.charAt(i), 2);
			}
		}
		if(won)
		{
			stillRunning = false;
			submitScore();
		}
		return result;
	}

	/**
	 * Saves the game score
	 */
	private void submitScore() {
		if (AccountManager.accountManager.getCurrentAccount() != null) {
			AccountManager.accountManager.addScore(getScore(), getWord());
			AccountManager.accountManager.save();
		}
	}

	/**
	 * @return whether or not more guesses can be entered
	 */
	public boolean eligible() {
		return (zenMode || guesses.size() < numGuesses);
	}

	/**
	 * @return the current score
	 */
	public int getScore() {
		return guesses.size();
	}

	/**
	 * @return if the game is still playing
	 */
	public boolean isStillPlaying() {
		return stillRunning;
	}

	/**
	 * @return the number of guesses made
	 */
	public int getGuessCount() {
		return guesses.size();
	}
	
	/**
	 * @return ArrayList of letters that exist in the answer, that the user hasn't yet guessed
	 */
	public String getUnguessedLetters() {
		String unguessedLetters = "";
		for (int i = 0; i < getWord().length(); i++) {
			char currentChar = Character.toUpperCase ( getWord().charAt ( i ) ) ;
			if (!knownInformation.containsKey(currentChar)) {
				unguessedLetters += currentChar;
			}
		}
		return unguessedLetters;
	}
	
	public ArrayList<Integer> getUnguessedIndices() {
		ArrayList<Integer> unguessedIndices = new ArrayList<>();
		for (int i = 0; i < getWord().length(); i++) {
			char currentChar = Character.toUpperCase ( getWord().charAt ( i ) ) ;
			// letter hasn't been guessed, or letter is incorrect position
			Integer status = knownInformation.get(currentChar);
			if (status == null || status == 1) {
				unguessedIndices.add(i);
			}
		}
		return unguessedIndices;
	}
	
	public String getShuffledAnswer() {
		List<String> characters = Arrays.asList(getWord().split(""));
		Collections.shuffle(characters);
		String shuffledAnswer = "";
		for (String character : characters) shuffledAnswer += character;
		return shuffledAnswer;
	}

}

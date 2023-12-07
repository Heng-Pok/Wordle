package view_controller;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import model.AccountManager;

/**
 * pane to display the guess counts for the user and overall
 */
public class LeaderboardPane extends GridPane{
	/**
	 * GUI setup variable
	 */
	private final Insets	padding	= new Insets(10, 10, 10, 10);
	/**
	 * GUI setup variable
	 */
	private final double	hGap	= 5.0;
	/**
	 * GUI setup variable
	 */
	private final double	vGap	= 5.0;
	
	/**
	 * initialize the leaderboard pane showing guess counts for the passed word
	 * @param word word to show counts for 
	 */
	public LeaderboardPane(String word)
	{
		format();
		Label mainLabel = new Label("Global Score (This Device)");
		Label main2 = new Label("Your Scores");
		this.add(main2, 1, 0);
		this.add(mainLabel, 0, 0);
		this.add(drawLeaderboard(AccountManager.accountManager.getGlobalScore(word)), 0, 0);
		this.add(drawLeaderboard(AccountManager.accountManager.getCurrentScore(word)), 1, 0);
	}
	
	/**
	 * initialize a global leaderboard pane
	 */
	public LeaderboardPane()
	{
		format();
		Label mainLabel = new Label("Global Score (This Device)");
		Label main2 = new Label("Your Scores");
		this.add(main2, 1, 0);
		this.add(mainLabel, 0, 0);
		this.add(drawLeaderboard(AccountManager.accountManager.getGlobalScore()), 0, 1);
		this.add(drawLeaderboard(AccountManager.accountManager.getCurrentScore()), 1, 1);
	}
	
	/**
	 * format the pane for display
	 */
	private void format()
	{
		this.setPadding(padding);
		this.setHgap(hGap);
		this.setVgap(vGap);
	}
	
	/**
	 * @param scoreCounts an array representing guess counts (number of guesses = index+1, val at index is the number of wins with that amount of guesses)
	 * @return a pane with a histogram representing the passed guess counts
	 */
	private GridPane drawLeaderboard(int[] scoreCounts)
	{
		GridPane leaderboard = new GridPane();
		if(scoreCounts == null)
			return leaderboard;
		int total = 0;
		for(int count : scoreCounts)
			total+=count;
		for(int i = 0; i<scoreCounts.length; i++)
		{
			Label scoreLabel = new Label((i+1)+":");
			ProgressBar score = new ProgressBar(scoreCounts[i]/((float) total));
			Label totalLabel = new Label(""+scoreCounts[i]);
			leaderboard.add(scoreLabel, 0, i);
			leaderboard.add(score, 1, i);
			leaderboard.add(totalLabel, 2, i);
		}
		return leaderboard;
	}

}

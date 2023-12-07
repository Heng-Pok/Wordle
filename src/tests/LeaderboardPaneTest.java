package tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import model.AccountManager;
import view_controller.LeaderboardPane;

public class LeaderboardPaneTest extends Application{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		AccountManager manager = new AccountManager();
		sendDummyArguments(manager);
		manager.login("Charlie", "1");
		Scene scene = new Scene(new LeaderboardPane("river"), 500, 500);
		stage.setScene(scene);
		stage.show();
	}
	
	private void sendDummyArguments(AccountManager manager)
	{
		manager.add(new Account("Charlie", "1"));
		manager.add(new Account("Heng", "2"));
		manager.add(new Account("Ethan", "3"));
		manager.add(new Account("Parker", "4"));
		manager.login("Charlie", "1");
		manager.addScore(1, "river");
		manager.addScore(3, "river");
		manager.addScore(3, "river");
		manager.addScore(3, "river");
		manager.addScore(5, "river");
		manager.addScore(1, "plane");
		manager.addScore(1, "plane");
		manager.addScore(1, "plane");
		manager.addScore(3, "plane");
		manager.addScore(3, "plane");
		manager.addScore(2, "witch");
		manager.addScore(3, "witch");
		manager.addScore(3, "witch");
		manager.addScore(4, "witch");
		manager.addScore(5, "witch");
		manager.login("Heng", "2");
		manager.addScore(3, "river");
		manager.addScore(4, "river");
		manager.addScore(3, "plane");
		manager.addScore(4, "plane");
		manager.addScore(3, "witch");
		manager.addScore(4, "witch");
		manager.login("Ethan", "3");
		manager.addScore(1, "river");
		manager.addScore(2, "river");
		manager.addScore(4, "plane");
		manager.addScore(4, "plane");
		manager.addScore(1, "witch");
		manager.addScore(3, "witch");
		manager.login("Parker", "4");
		manager.addScore(5, "river");
		manager.addScore(5, "river");
		manager.addScore(4, "plane");
		manager.addScore(5, "plane");
		manager.addScore(1, "witch");
		manager.addScore(2, "witch");
	}
}

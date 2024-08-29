import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This program will read the leaderboard file and compare the current players' time
//to the leaderboard to see if the player beat any leaderboard players
//Source: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
public class LeaderBoard {

	// Create a Player object array with the top 5 and the current player
	public static Player[] players;
	int lines = 0;

	public LeaderBoard(int time, int level) {
		getLines(level);
		// This will read the leaderboard file
		if (!(LevelFrame.name.isEmpty())) {
			players = new Player[6];
		} else {
			players = new Player[5];
		}
		try {
			Scanner fileInput = new Scanner(new File("Level" + level + "LeaderBoard.txt"));
			fileInput.useDelimiter(",|\n");
			// Calculate the current player's time
			int highScoreTime = 60 - time;

			// if the txt file is empty then add the current player to the player array
			if (lines == 0) {
				players[0] = new Player(LevelFrame.name, highScoreTime);
			}
			// else add all the players in the txt file in the array
			// until all the players are in the array, then add the current player
			else {
				for (int i = 0; i < lines + 1; i++) {
					if (i == lines) {
						if (!(LevelFrame.name.isEmpty())) {
							players[i] = new Player(LevelFrame.name, highScoreTime);
						}
					} else {
						String name = fileInput.next();
						int highScore = fileInput.nextInt();
						players[i] = new Player(name, highScore);
					}
				}

				fileInput.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Sort the players by least amount of time to greatest amount of time
		Arrays.sort(players, Comparator.comparing(Player::getTime));

		// Go and write the leaderboard in the WriteFile class
		new WriteFile(players, level);
	}

	// This method will get the amount of lines in the txt file
	// (in case the file is empty)
	private void getLines(int level) {
		try {
			Scanner fileInput = new Scanner(new File("Level" + level + "LeaderBoard.txt"));
			while (fileInput.hasNextLine()) {
				lines++;
				System.out.println(fileInput.next());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
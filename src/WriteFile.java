import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This program will write in the corresponding txt file and update the 
//leaderboard

public class WriteFile{
	
	//Source: https://www.geeksforgeeks.org/java-program-to-save-a-string-to-a-file/
	public WriteFile(Player[] players, int level) {
		int j = 0;
		//create a string array for both the names and their scores
		String[] names = new String[5];
		String[] scores = new String[5];
		
		//get the txt file so that the program can write in it
		Path path = Paths.get("Level" + level + "LeaderBoard.txt");

		try {
			for (int i = 0; i<6; i++) {
				//if the player's time is greater than 0 and the user entered a name
				if (players[i].getTime() > 0 && !(players[i].getName() == "")) {
					//then fill the arrays 
					names[j] = players[i].getName();
					scores[j] = String.valueOf(players[i].getTime());
					j++;
					//This will make sure that we only include the top 5
					if (j == 5) {
						break;
					}
				}
			}
			
		//write the new leaderboard into the txt file
		Files.writeString(path, names[0] + "," + scores[0] + "\n" + names[1] 
				+ "," + scores[1] + "\n" + names[2] + "," + scores[2] 
				+ "\n" + names[3] + "," + scores[3] + "\n" + names[4] 
				+ "," + scores[4], StandardCharsets.UTF_8);

		} catch(IOException ex) {
			System.out.print("Invalid Path");
		}
	}}
import java.io.File;
/* Name: Saindhavi Thevathasan
 * Date: May 28th, 2023
 * Course: ICS3U1-04;
 * Title: Super Mario Game
 * Description: This is a video game where a character, represented by the iconic
 * Mario (this is a knock-off, this is a dupe, catfish) can
 * run through a level map and collect coins
 * 
 * This class serves as the entry point for the game. It creates an instance
 * of the LevelFrame class with the desired level number
 * Essentially this opens the game window
 * Major Skills: Swing GUI Components, Arrays, Reading and Writing
 * into external txt files, Loops, If Else Statements, Classes and Objects
 * Added Features: 
 * 1. Background Music
 * 2. Sound Effects
 * 3. Opening Screen
 * 4. Character facing correct direction
 * 5. Timing
 * 6. Score Label
 * 7. More Characters
 * 8. New Levels
 * 9. Menubar:
 * 		- Some problems with the code: If you press the New Game button twice,
 * 			the timer does not reset.
 *		- When pressing the quit button twice, a JOptionPane shows up, however
 *			when pressing "Yes" the game over screen does not show up (however,
 *			sound effect plays). 
 * 10. Leaderboard with Top 5 Players
 * Areas of Concern: To combat the second issue with the menubar, I redirected the user
 *			to the title page instead.
 * Contribution to Assignment: 
 * Saindhavi Thevathasan: 90%
 * External Source: 10% (helped with writing files and timer)
 * 
 */

public class SuperMarioApplication {
	//This will actually run the program
	public static void main(String[] args) {
		new TitleFrame();

	}

}
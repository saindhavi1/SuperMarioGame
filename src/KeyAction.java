import java.awt.event.ActionEvent;

import javax.swing.text.TextAction;
//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This class will interpret the keys pressed by the user
//so the appropriate action can be performed on the character
public class KeyAction extends TextAction {

	private String key;

	// Takes the string parameter key (the key that is bound to the action)
	// to decide what the character will do
	public KeyAction(String key) {
		super(key);
		this.key = key;
	}

	//This will react to the action the user does to the character
	//based on the key the user pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		// this will make it easier to reference the choosen character
		Character player = LevelFrame.player;

		//if the a key is pressed and the character is not hitting the wall
		//the character will move left and change the icon
		if (e.getActionCommand().equals(player.getKey()[0])
				&& LevelFrame.boardArray[player.getRow()][player.getCol()].getIcon() != Icons.WALL) {
			player.moveLeft();
			player.icon(1);
		} 
		//if the d key is pressed and the character is not hitting the wall'
		//character will move right and change the icon
		else if (e.getActionCommand().equals(player.getKey()[1])
				&& LevelFrame.boardArray[player.getRow()][player.getCol()].getIcon() != Icons.WALL) {
			player.moveRight();
			player.icon(0);
		}

		//if the spacebar is pressed and the character is not hitting the wall
		//the character will jump
		else if (e.getActionCommand().equals(player.getKey()[2])
				&& LevelFrame.boardArray[player.getRow() - 1]
						[player.getCol()].getIcon() != Icons.WALL) {
			if (!player.isJumping()) {
				player.setJumping(true);
				player.jump();
			}
			
		}
	}

}
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* Name: Saindhavi Thevathasan
 * Course: ICS3U1 - 04
 * This class represents the player's character
 * It will allow the character to jump, move and collect coins
 */
public class Character extends JLabel implements ActionListener {

	// this applies if you want the character to face a different
	// direction in the game
	private ImageIcon[] iconArray;

	// static character (stays the same regardless of the direction
	// of movement
	//private ImageIcon icon;

	private String[] key;
	private int dX = 0, dY = 0;
	private Timer jumpTimer = new Timer(25, this);
	private int jumpCounter;
	// checks if the player has jumped at the beginning of the game
	private boolean jumping = false;
	private Timer fallTimer = new Timer(25, this);
	private int fallCounter;
	// set to false since the player will start of on the brick floor
	private boolean falling = false;

	//constructor for dynamic movement of character
	public Character(ImageIcon[] iconArray, String[] key) {
		super();
		setIconArray(iconArray);
		this.key = key;
	}
	
	public ImageIcon[] getIconArray() {
		return iconArray;
	}

	public void setIconArray(ImageIcon[] iconArray) {
		this.iconArray = iconArray;
	}

	public String[] getKey() {
		return key;
	}

	public void setKey(String[] key) {
		this.key = key;
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

	public Timer getJumpTimer() {
		return jumpTimer;
	}

	public void setJumpTimer(Timer jumpTimer) {
		this.jumpTimer = jumpTimer;
	}

	public int getJumpCounter() {
		return jumpCounter;
	}

	public void setJumpCounter(int jumpCounter) {
		this.jumpCounter = jumpCounter;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public Timer getFallTimer() {
		return fallTimer;
	}

	public void setFallTimer(Timer fallTimer) {
		this.fallTimer = fallTimer;
	}

	public int getFallCounter() {
		return fallCounter;
	}

	public void setFallCounter(int fallCounter) {
		this.fallCounter = fallCounter;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	//Utility methods
	
	//will set the appropriate icon for the direction they are going in
	public void icon(int iconNum) {
		setIcon(iconArray[iconNum]);
	}

	//makes character jump
	public void jump() {
		jumping = true;
		jumpCounter = 0;
		jumpTimer.start();
	}
	
	//character falls
	public void fall() {
		falling = true;
		fallCounter = 0;
		fallTimer.start();
	}
	
	//allows character to move right
	public void moveRight() {
		dX = 5;
		setBounds(getX() + dX, getY(), 25, 25);
	}
	
	//allows character to move left
	public void moveLeft() {
		dX = -5;
		setBounds(getX() + dX, getY(), 25, 25);
	}
	
	//This will allow the program to know what tile the character is on
	//and then perform an action based on that (ex collecting a coin
	//or collision with an obstacle)
	public int getRow() {
		return (int)getY()/28;
	}
	
	public int getCol() {
		return (int)getX()/25;
	}
	
	//Collects the coins
	public void collectCoin(String coinSound) {
		int row = getRow();
		int col = getCol();
		int scoreNum = Integer.parseInt(LevelFrame.scoreNum.getText());
		if (LevelFrame.boardArray[row][col].getIcon() == Icons.COIN) {
			 //remove the coin from the screen when the character collects it
			LevelFrame.boardArray[row][col].setIcon(null);
			score(scoreNum);
			//Used this music: https://www.youtube.com/watch?v=mQSmVZU5EL4
			//Used this website: https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
			try {
				File coinFile = new File(coinSound);
				AudioInputStream coinAudio = AudioSystem.getAudioInputStream(coinFile);
				Clip clip = AudioSystem.getClip();
				clip.open(coinAudio);
				clip.start();
				
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	//This method will update the score when the character collects a coin
	//and update the score on the JLabel
	public void score(int scoreNum) {
		if (scoreNum == -1) {
			scoreNum = 0;
		}
		else {
			scoreNum++;
		}
		LevelFrame.scoreNum.setText(String.valueOf(scoreNum));
		LevelFrame.menu.add(LevelFrame.scoreNum);
	}
	
	//actions for the character
	//will check for character collisions (character colliding with obstacles
	//and if the character is jumping and there is no ground, it should fall
	@Override
	public void actionPerformed(ActionEvent e) {
		//will check for collision with the bricks (when the character 
		//jumps and falls). This method will be triggered whenever an 
		//event occurs
		
		//checks the collision of jumping up into a brick
		if (jumping && dY < 0 && LevelFrame.boardArray[getRow()-1][getCol()].getIcon() == Icons.WALL) {
			jumping = false;
			dY = 0;
			jumpTimer.stop();
			fall();
			return;
		}
		
		//checks the collision of falling off 
		if (falling && dY > 0) {
			int nextRow = getRow() + 1;
			if (nextRow >= LevelFrame.boardArray.length || LevelFrame.boardArray[nextRow][getCol()].getIcon() == Icons.WALL) {
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
		}
		
		//shows what to do if the character is jumping
		if (jumping) {
			jumpCounter++;
			if (jumpCounter <=10) //going up
				dY = -5;
			else if (jumpCounter <= 20) { //going down
				dY = 5;
			}
			else
			{
				//reset everything so character stops jumping
				jumping = false;
				dY = 0;
				jumpTimer.stop();
				fall();
			}
		}
		
		//default case not:
		else if (falling) {
			fallCounter++;
			dY = 5;
			setBounds(getX(), getY() + dY, 25, 25);
			if (LevelFrame.boardArray[getRow()+1][getCol()].getIcon() == Icons.WALL) {
				//check for collision with the ground
				falling = false;
				dY = 0;
				fallTimer.stop();
			}
			else if (fallCounter >=20) {
				//stop falling after reaching the ground level
				falling = false;
				dY = 0;
				fallTimer.stop();
			}
			//exit method since character is falling
			return;
		}
		
		//if the character is touching a wall on the left and is moving left
		//stop the character
		if (LevelFrame.boardArray[getRow()][getCol() + 1].getIcon() == Icons.WALL && dX > 0) {
			dX = 0;
		}
		
		//if the character is touching a wall on the right and is moving right
		//stop the character
		else if (LevelFrame.boardArray[getRow()][getCol()-1].getIcon() == Icons.WALL && dX< 0) {
			dX = 0;
			//setBounds(getX() + dX, getY() + dY, 25, 25);
		}
		
		//if the character is moving up and is touching a ceiling
		//stop the character
		else if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.WALL & dY < 0) {
			dY = 0;
		}
		
		//if the character is moving down and moving downwards
		//stop the character
		else if (LevelFrame.boardArray[getRow() + 1][getCol()].getIcon() == Icons.WALL && dY > 0) {
			dY = 0;
		}
		
		String coinSound = "Sounds/Coin.wav";
		//method to collect the coin if applicable
		collectCoin(coinSound);
		
		//used to set the location and size of Mario
		setBounds(getX() + dX, getY() + dY, 25, 25);
	}

}
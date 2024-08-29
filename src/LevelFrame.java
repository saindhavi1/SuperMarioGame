import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/* Name: Saindhavi Thevathasan
 * Course: ICS3U1-04
 * This class will represent the game frame
 * handles the display of the game board
 * and implements keyListener to comprehend the character's actions
 * also loads the chosen level based on the corresponding txt file
 */

public class LevelFrame extends JFrame implements KeyListener, ActionListener {
	// two dimensional array to represent the Level1.txt file
	public static JLabel[][] boardArray = new JLabel[20][25];

	// timer for the game
	public static Timer gameTimer;

	private JPanel instruction = new JPanel();
	private JLabel title = new JLabel("Instructions");
	private JLabel instructionLabel = new JLabel("Collect all the coins before the timer runs out!");
	private JLabel instructionLabel2 = new JLabel("Click the OK button to start!");
	private JButton ok = new JButton("OK");
	// Panel for the choosen level game
	private JPanel lvl1Panel = new JPanel();

	//Menu
	static JLabel menu = new JLabel(new ImageIcon());
	JButton newGame = new JButton("New Game");
	JButton quit = new JButton("Quit");
	JLabel score = new JLabel("Score:");
	static JLabel scoreNum = new JLabel("0");
	JLabel timer = new JLabel();
	int second, minute;
	String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	
	//Panel to let the user know the game is over
	private JPanel gameOverPanel = new JPanel();
	private JLabel gameOverLabel = new JLabel("Game Over");
	private JLabel winOrLose = new JLabel();
	private JLabel playAgain = new JLabel("Would you like to play another level?");
	private JButton playAgainYes = new JButton("Yes");
	private JButton playAgainNo = new JButton("No");


	//creates the character that was choosen in the AskCharacterFrame
	public static Character player;
	
	//Background music
	public static Clip clip;
	//Win or Lose music
	public static Clip clip2;
	
	//Keeps count of the coins
	public static int coin;
	//stores whether or not they won or lost
	public static boolean wonOrLost;
	
	//Stores the person's name for the leaderboard
	public static String name;
	//JOptionPane variable for the quit screen
	int quitConfirm = 0;
	
	Color panelColor = new Color(196,36,36);
	Color buttonColor = new Color(100, 163, 248);

	// will load the map corresponding to the level number
	public LevelFrame(int level) {
		// Sizing the frame
		setSize(25 * boardArray[0].length, 28 * boardArray.length);
		// length of the row(horizontal) //length of col(vertical)
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set the player choosen in the AskCharacterFrame to player
		//for efficiency
		player = AskCharacterFrame.choosenCharacter;
		//set the coins generated in this map to 0
		coin = 0;
		
		// Using this method, it will load the level txt file corresponding
		// to the level number
		loadLevel(level);

		// will create the panel for the level loaded
		createLvlPanel();

		// sets up the key bindings for the key control
		setupKeyBindings();

		setVisible(true);
	}
	
	//This method will display how to play the game
	//before the game starts
	private void howToPlay() {
		add(instruction);
		instruction.setBackground(panelColor);
		instruction.setLayout(null);
		instruction.setBounds(110, 175, 400, 200);
		
		instruction.add(title);
		title.setBounds(140, 0, 200, 50);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Sans Serif", Font.BOLD, 20));
		
		instruction.add(instructionLabel);
		instructionLabel.setBounds(30, 30, 350, 50);
		instructionLabel.setForeground(Color.WHITE);
		instructionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
		
		instruction.add(instructionLabel2);
		instructionLabel2.setBounds(95, 50, 300, 100);
		instructionLabel2.setForeground(Color.WHITE);
		instructionLabel2.setFont(new Font("Sans Serif", Font.PLAIN, 15));
		
		instruction.add(ok);
		ok.setBounds(160, 125, 90, 50);
		ok.setBorder(new EmptyBorder(0, 0, 0, 0));
		ok.setOpaque(true);
		ok.setBackground(buttonColor);
		ok.addActionListener(this);
		
		instruction.setVisible(true);
		player.setVisible(false);
	}

	// will get the file and convert each block to a JLabel
	// then these JLabels will be stored in the JLabel array
	private void loadLevel(int levelNumber) {
		howToPlay();
		String startMusic = "Sounds/SuperMario.wav";
		playBeginningMusic(startMusic);
		// create Scanner to get the file
		try {
			Scanner inputFile = new Scanner(new File("Level" + levelNumber + ".txt"));
			for (int row = 0; row < boardArray.length; row++) {
				// each element in the array will hold each character in the line
				char[] lineArray = inputFile.next().toCharArray();
				// look at each character
				for (int col = 0; col < lineArray.length; col++) {
					if (lineArray[col] == 'B') {
						boardArray[row][col] = new JLabel(Icons.WALL);

					} else if (lineArray[col] == 'C') {
						boardArray[row][col] = new JLabel(Icons.COIN);
						//coin variable will go up by 1 once a coin has been 
						//generated
						coin++;
					} else {
						boardArray[row][col] = new JLabel();
					}
				}
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// creates the JPanel and add the JLabels onto the game board
	private void createLvlPanel() {
		Color menuTextColor = new Color(250, 225, 2);
		menu.setBounds(0, 0, 25 * boardArray[0].length, 50);

		//Menu for the game
		ImageIcon menuPic = new ImageIcon("images/menu.png");
		Image menuImg = menuPic.getImage();
		Image menuScaled = menuImg.getScaledInstance(25 * boardArray[0].length, 50, Image.SCALE_SMOOTH);
		menu.setIcon(new ImageIcon(menuScaled));
		menu.setLayout(null);
		lvl1Panel.add(menu);

		menu.add(newGame);
		newGame.setBounds(0, 0, 100, 50);

		newGame.setBorder(new EmptyBorder(0, 0, 0, 0));
		newGame.setBorderPainted(false);
		newGame.setOpaque(false);
		newGame.setForeground(menuTextColor);
		newGame.setFont(new Font("Arial", Font.BOLD, 15));
		newGame.addActionListener(this);

		menu.add(quit);
		quit.setBounds(120, 0, 100, 50);
		quit.setOpaque(false);
		quit.setBorderPainted(false);
		quit.addActionListener(this);
		quit.setForeground(menuTextColor);
		quit.setFont(new Font("Arial", Font.BOLD, 15));

		menu.add(score);
		score.setBounds(450, 0, 100, 50);
		score.setForeground(menuTextColor);
		score.setFont(new Font("Arial", Font.BOLD, 15));

		menu.add(scoreNum);
		scoreNum.setBounds(500, 0, 100, 50);
		scoreNum.setForeground(menuTextColor);
		scoreNum.setFont(new Font("Arial", Font.BOLD, 15));

		menu.add(timer);
		timer.setBounds(275, 0, 100, 50);
		timer.setForeground(menuTextColor);
		timer.setFont(new Font("Arial", Font.BOLD, 25));
		timer.setVisible(false);

		// we will set the bounds for each label placed on the panel
		lvl1Panel.setLayout(null);
		lvl1Panel.setBackground(new Color(100, 163, 248));

		// use a nested for loop to put each JLabel onto the panel
		// and set the bounds for each label
		for (int row = 0; row < boardArray.length; row++) {
			for (int col = 0; col < boardArray[0].length; col++) {
				if (boardArray[row][col].getIcon() == Icons.WALL) {
					boardArray[row][col].setBounds(col * 25, 50 + (row * 25), 25, 25);
					lvl1Panel.add(boardArray[row][col]);
				} else if (boardArray[row][col].getIcon() == Icons.COIN) {
					boardArray[row][col].setBounds(col * 25, 50 + (row * 25), 25, 25);
					lvl1Panel.add(boardArray[row][col]);
					
				}

			}
		}

		// add the chosen character onto the panel and get them in their starting position
		player.setBounds(25, 475, 25, 25);
		player.icon(0);
		lvl1Panel.add(player);
		// set the size of the game board
		lvl1Panel.setBounds(0, 0, 25 * boardArray[0].length, 29 * boardArray.length);
		// add the game board to the frame so that the user can see it
		add(lvl1Panel);
		name = JOptionPane.showInputDialog(lvl1Panel, "Please enter your name. " 
		+ "If you do not, you will not be considered in the leaderboard.");
	}

	// Used this music: https://www.youtube.com/watch?v=_9bB7r0M9kg
	// Used this website:
	// https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java

	// This method will play the background music for the game
	private void playBeginningMusic(String startMusic) {
		try {
			File musicFile = new File(startMusic);
				AudioInputStream audio = AudioSystem.getAudioInputStream(musicFile);
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	// matches the key pressed to the action for the character
	private void setupKeyBindings() {
		// key identifier
		ActionMap actionMap;

		// map the key stroke to a key identifier
		InputMap inputMap;

		// key strokes for the level 1 panel are being mapped in the
		// inputMap object
		inputMap = lvl1Panel.getInputMap();

		// key strokes are being matched to the keys and the
		// action needed to be performed will be stored here
		actionMap = lvl1Panel.getActionMap();

		// maps the left movement by pressing the left key (a)
		inputMap.put(KeyStroke.getKeyStroke(player.getKey()[0].toCharArray()[0]), "left");

		// the line above specified the action needed to be performed
		// and this line will actually perform the action on the character
		actionMap.put("left", new KeyAction("left"));

		// maps the left movement by pressing the right key (d)
		inputMap.put(KeyStroke.getKeyStroke(player.getKey()[1].toCharArray()[0]), "right");

		// the line above specified the action needed to be performed
		// and this line will actually perform the action on the character
		actionMap.put("right", new KeyAction("right"));

		// maps the left movement by pressing the jump key (spacebar)
		inputMap.put(KeyStroke.getKeyStroke(player.getKey()[2].toCharArray()[0]), "jump");

		// the line above specified the action needed to be performed
		// and this line will actually perform the action on the character
		actionMap.put("jump", new KeyAction("jump"));

		//System.out.println("Key Bindings set up");
	}

	//Source: https://www.youtube.com/watch?v=zWw72j-EbqI
	//This method will turn on the timer when the game starts
	public void timer() {
		timer.setText("01:00");
		timer.setVisible(true);
		gameTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);

				timer.setText(ddMinute + ":" + ddSecond);

				if (second == -1) {
					second = 59;
					minute--;

					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					timer.setText(ddMinute + ":" + ddSecond);
				}
				
				if (minute == 0 && second == 0) {
					gameTimer.stop();
					String music = "Sounds/gameOver.wav";
					setupLoseScreen(music);
					timer.setText("");
					
				}
				//If the user has collected all the coins
				//Let the user know they won, stop the timer
				//and update the leaderboard
				if (scoreNum.getText().equals(String.valueOf(coin))) {
					String music = "Sounds/LevelComplete.wav";
					setupLoseScreen(music);
					gameTimer.stop();
					new LeaderBoard(second, AskLevelFrame.level);
				}
			}
			
		});
	}
	
	//This method will play the corresponding music (winning music or losing music)
	//and display a panel that shows that they won!
	public void setupLoseScreen(String music) {
		stopPlaying(clip);
		
		try {
			File musicFile = new File(music);
			AudioInputStream audio = AudioSystem.getAudioInputStream(musicFile);
			clip2 = AudioSystem.getClip();
			clip2.open(audio);
			clip2.start();

		} catch (Exception e) {
			System.out.println(e);
		}
		lvl1Panel.add(gameOverPanel);
		gameOverPanel.setLayout(null);
		gameOverPanel.setBackground(panelColor);
		gameOverPanel.setBounds(110, 175, 400, 200);
		
		gameOverPanel.add(gameOverLabel);
		gameOverLabel.setBounds(140, 0, 200, 50);
		gameOverLabel.setForeground(Color.WHITE);
		gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		
		gameOverPanel.add(winOrLose);
		winOrLose.setBounds(90, 40, 300, 50);
		winOrLose.setForeground(Color.WHITE);
		winOrLose.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		if (music.equals("Sounds/LevelComplete.wav")) {
			winOrLose.setText("Congratulations. You Won!");
			wonOrLost = true;
		}
		else {
			winOrLose.setText("Awwww. You Lost.");
			wonOrLost = false;
		}
		
		
		gameOverPanel.add(playAgain);
		playAgain.setForeground(Color.WHITE);
		playAgain.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		playAgain.setBounds(40, 80, 350, 50);
		
		gameOverPanel.add(playAgainYes);
		playAgainYes.setBorder(new EmptyBorder(0, 0, 0, 0));
		playAgainYes.setOpaque(true);
		playAgainYes.setBackground(buttonColor);
		playAgainYes.setBounds(100, 135, 90, 50);
		playAgainYes.addActionListener(this);
		
		gameOverPanel.add(playAgainNo);
		playAgainNo.setBorder(new EmptyBorder(0, 0, 0, 0));
		playAgainNo.setOpaque(true);
		playAgainNo.setBackground(buttonColor);
		playAgainNo.setBounds(200, 135, 90, 50);
		playAgainNo.addActionListener(this);
		
		gameOverPanel.setVisible(true);
		player.setVisible(false);

	}
	
	//This method will stop the music that is currently playing
	//so that another clip of music can be played (music does not overlap)
	public static void stopPlaying(Clip clip) {
		clip.stop();
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	// will set the horizontal velocity of the character to 0
	// when the left and right keys is released
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			player.setdX(0);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		//If the user has read the instructions and pressed ok
		//Start the game
		if (e.getSource() == ok) {
			instruction.setVisible(false);
			player.setVisible(true);
			
			second = 0;
			minute = 1;
			timer();
			gameTimer.start();
			timer.setVisible(true);
		}
		
		//If the user wants to start a new game
		//restart the timer, stop playing music
		//and create a new level panel
		else if (e.getSource() == newGame) {
			player.score(-1);
			timer.setVisible(false);
			timer.setText("");
			stopPlaying(clip);
			//playAgainNum++;
			new LevelFrame(AskLevelFrame.level);
			dispose();
		} 
		
		//if the user wants to quit the game, ask if they are sure and stop the timer
		else if (e.getSource() == quit) {
			gameTimer.stop();
			quitConfirm = JOptionPane.showConfirmDialog(this, String.format("Are you sure you want to quit?", JOptionPane.YES_NO_OPTION));
			
			//if they do want to quit, then play the loosing music and 
			//show the game over panel (Original intention)
			//However I changed it so that it would lead to the title page instead
			//as my original intention did not work
			if (quitConfirm == JOptionPane.YES_OPTION) {
				stopPlaying(clip);
				player.score(-1);
				timer.setText("");
				AskLevelFrame.level -= 1;
				//String music = "Sounds/gameOver.wav";
				//setupLoseScreen(music);
				//gameOverPanel.setVisible(true);
				new TitleFrame();
				dispose();
			}

			//if they do not want to quit, resume the game by starting the timer again
			else if (quitConfirm == JOptionPane.NO_OPTION) {
				gameTimer.start();
			}
		}
		
		//if after they win/lose they game, and they want to play again
		//load a new level frame
		else if (e.getSource() == playAgainYes) {
			stopPlaying(clip2);
			dispose();
			
			//if they won the last level, tell the user they completed all the level
			if (AskLevelFrame.level+1 == 4 && wonOrLost) {
				JOptionPane.showMessageDialog(this, "YOU COMPLETED ALL THE LEVELS");
			}
			//if they lost the last level, let them replay the last level
			else if (AskLevelFrame.level+1 <= 4 && wonOrLost == false) {
				new LevelFrame(AskLevelFrame.level);
			}
			//if they won the last level and they were not on the 
			//last level, then let them play the next level
			else {
				new LevelFrame(AskLevelFrame.level+1);
				AskLevelFrame.level += 1;
			}
			player.score(-1);
			timer.setText("");
			
		}
		
		//if they do not want to play again, lead them to the title screen
		else if (e.getSource() == playAgainNo) {
			stopPlaying(clip2);
			dispose();
			new TitleFrame();
			player.score(-1);
			timer.setText("");
			
		}
	}
}

import javax.swing.ImageIcon;

//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This class will create all the icons in the game
//Examples of icons are the characters, obstacles or boundaries
//of the map

public class Icons {
	//creates the icons in the game
	
	//creates the brick wall in the game
	public static final ImageIcon WALL = new ImageIcon("images/ground.png");
	
	//create the coins the character will collect in the game
	public static final ImageIcon COIN = new ImageIcon("images/coin.png");
	
	//create an Image array for mario
	//we use image arrays so that when moving in different directions
	//we can switch the picture
	public static final ImageIcon[] MARIO = {new ImageIcon("images/mario.gif"), new ImageIcon("images/mario1.gif")};
	
	//create the icons for yoshi
	public static final ImageIcon[] YOSHI = {new ImageIcon("images/yoshi.gif"), new ImageIcon("images/yoshi2.png")};
	
	//create the icons for princess peach
	public static final ImageIcon[] PRINCESSPEACH = {new ImageIcon("images/princessPeach.gif"), new ImageIcon("images/princessPeach2.png")};

	
}



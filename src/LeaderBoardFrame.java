import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This program will read the leaderboard txt file and display the 
//leaderboard. The user can click buttons based on the level they want
//to see for the leaderboard
public class LeaderBoardFrame extends JFrame implements ActionListener{

	//Create a leaderboard players array
	Player[] LeaderboardPlayers = new Player[5];
	
	//Set up the GUI elements for the leaderboard
	JLabel leaderboardTitle = new JLabel("Leaderboard");
	JButton level1 = new JButton("Level 1");
	JButton level2 = new JButton("Level 2");
	JButton level3 = new JButton("Level 3");
	JButton back = new JButton(new ImageIcon());
	
	JLabel firstName = new JLabel();
	JLabel firstTime = new JLabel();
	
	JLabel secondName = new JLabel();
	JLabel secondTime = new JLabel();
	
	JLabel thirdName = new JLabel();
	JLabel thirdTime = new JLabel();
	
	JLabel fourthName = new JLabel();
	JLabel fourthTime = new JLabel();
	
	JLabel fifthName = new JLabel();
	JLabel fifthTime = new JLabel();
	
	//Initialize the level variable to 1 so that when the user
	//click on leaderboard, it will automatically show the first level
	int level = 1;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public LeaderBoardFrame() {
		setSize(screenSize.width, screenSize.height);
		setTitle("Leaderboard");
		getContentPane().setBackground(new Color(100, 163, 248));
		setLayout(null);
		setVisible(true);
		
		//make 3 buttons for each leaderboard
		add(leaderboardTitle);
		leaderboardTitle.setBounds(600, 100, 500, 100);
		leaderboardTitle.setFont(new Font("Sans Serif", Font.BOLD, 30));
		leaderboardTitle.setForeground(Color.WHITE);
		
		add(level1);
		level1.setBounds(150, 0, 300, 100);
		level1.addActionListener(this);
		
		
		add(level2);
		level2.setBounds(550, 0, 300, 100);
		level2.addActionListener(this);
		
		add(level3);
		level3.setBounds(950, 0, 300, 100);
		level3.addActionListener(this);
		
		add(back);
		ImageIcon backPic = new ImageIcon("images/back.png");
		Image backPicImg = backPic.getImage();
		Image backPicScaled = backPicImg.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);
		back.setIcon(new ImageIcon(backPicScaled));
		back.setBorder(new EmptyBorder(0, 0, 0, 0));
		back.setBorderPainted(false);
		back.setOpaque(false);
		back.setBounds(0, 0, 100, 100);
		back.addActionListener(this);
		
		//After everything is set up, read the leaderboard txt file
		//to the corresponding level (will automatically open to level 1)
		readFile(level);
	}
	
	//This method will read the file and create the leaderboard players array
	private void readFile(int level) {
		Scanner inputFile;
		try {
			inputFile = new Scanner(new File("Level" + level + "LeaderBoard.txt"));
			inputFile.useDelimiter(",|\n");
			for (int i = 0; i<5; i++) {
				String name = inputFile.next();
				int time = inputFile.nextInt();
				
				LeaderboardPlayers[i] = new Player(name, time);
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then we will call this method to print out each player
		makeLabel();
	}
	
	//Source:
	//https://www.javatpoint.com/java-jtable#:~:text=The%20JTable%20class%20is%20used,composed%20of%20rows%20and%20columns.
	//This method will create a JTable and display the leaderboard
	private void makeLabel() {
		String[][] leaderboard = {{"1", LeaderboardPlayers[0].getName(), String.valueOf(LeaderboardPlayers[0].getTime())},
				{"2", LeaderboardPlayers[1].getName(), String.valueOf(LeaderboardPlayers[1].getTime())},
				{"3", LeaderboardPlayers[2].getName(), String.valueOf(LeaderboardPlayers[2].getTime())},
				{"4", LeaderboardPlayers[3].getName(), String.valueOf(LeaderboardPlayers[3].getTime())},
				{"5", LeaderboardPlayers[4].getName(), String.valueOf(LeaderboardPlayers[4].getTime())}};
		
		String[] column = {"Rank", "Name", "Best Time"};
		
		JTable leaderboardTable = new JTable(leaderboard, column);
		leaderboardTable.setRowHeight(85);
		leaderboardTable.setFont(new Font("Sans Serif", Font.PLAIN, 15));
		JTableHeader header = leaderboardTable.getTableHeader();
		header.setPreferredSize(new Dimension(1000, 50));
		header.setFont(new Font("Sans Serif", Font.BOLD, 15));
		header.setBackground(new Color(0, 119, 182));
		((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		header.setForeground(Color.WHITE);
		JScrollPane pane = new JScrollPane(leaderboardTable);
		pane.setBounds(200, 200, 1000, 500);
		add(pane);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if the user did not select back 
		if (e.getSource() != back) {
			//and they selected level 1, assign the variable level to 1
			if (e.getSource() == level1) {
				level = 1;
			}
			//if they selected level 2, assign the variable level to 2
			else if (e.getSource() == level2) {
				level = 2;
			}
			//if they selected level 3, assign the variable level to 3
			else if (e.getSource() == level3) {
				level = 3;
			}
			//then read the file for the corresponding level
			//so that the leaderboard can be displayed
			readFile(level);
		}
		//if the user clicked the back button
		//show the title frame
		else {
			new TitleFrame();
			dispose();
		}
		
	}
	
	

}

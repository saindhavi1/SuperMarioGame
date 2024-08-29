import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This program will allow the user to choose a level to play

public class AskLevelFrame extends JFrame implements ActionListener{

	//Set up the GUI stuff for this frame
	JLabel background = new JLabel(new ImageIcon());
	JButton back = new JButton(new ImageIcon());
	JButton chooseLevel1 = new JButton("Level 1");
	JButton chooseLevel2 = new JButton("Level 2");
	JButton chooseLevel3 = new JButton("Level 3");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	//Declare a variable for what level is selected.
	//this variable will be accessed by the levelFrame class
	public static int level = 0;
	
	public AskLevelFrame() {
		add(background);
		setTitle("Character");
		setVisible(true);
		ImageIcon backgroundPic = new ImageIcon("images/chooseLevel.png");
		Image backgroundPicImg = backgroundPic.getImage();
		Image backgroundPicScaled = backgroundPicImg.getScaledInstance(screenSize.width, screenSize.height,
				Image.SCALE_SMOOTH);
		background.setIcon(new ImageIcon(backgroundPicScaled));
		setBounds(0, 0, screenSize.width, screenSize.height);

		background.add(back);
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
		
		background.add(chooseLevel1);
		ImageIcon level1Pic = new ImageIcon("images/Level1.png");
		Image level1PicImg = level1Pic.getImage();
		Image level1PicScaled = level1PicImg.getScaledInstance(360, 360,
				Image.SCALE_SMOOTH);
		chooseLevel1.setIcon(new ImageIcon(level1PicScaled));
		chooseLevel1.setBounds(150, 250, 350, 350);
		chooseLevel1.addActionListener(this);

		background.add(chooseLevel2);
		ImageIcon level2Pic = new ImageIcon("images/Level2.png");
		Image level2PicImg = level2Pic.getImage();
		Image level2PicScaled = level2PicImg.getScaledInstance(360, 360,
				Image.SCALE_SMOOTH);
		chooseLevel2.setIcon(new ImageIcon(level2PicScaled));
		chooseLevel2.setBounds(550, 250, 350, 350);
		chooseLevel2.addActionListener(this);

		background.add(chooseLevel3);
		ImageIcon level3Pic = new ImageIcon("images/Level3.png");
		Image level3PicImg = level3Pic.getImage();
		Image level3PicScaled = level3PicImg.getScaledInstance(360, 360,
				Image.SCALE_SMOOTH);
		chooseLevel3.setIcon(new ImageIcon(level3PicScaled));
		chooseLevel3.setBounds(950, 250, 350, 350);
		chooseLevel3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if the user chooses level 1, load the level 1 frame
		if (e.getSource() == chooseLevel1) {
			new LevelFrame(1);
			level = 1;
		}
		//if the user chooses level 2, load the level 2 frame
		else if (e.getSource() == chooseLevel2) {
			new LevelFrame(2);
			level = 2;
		}
		//if the user chooses level 3, load the level 3 frame
		else if (e.getSource() == chooseLevel3) {
			new LevelFrame(3);
			level = 3;
		}
		//if the user wants to go back, they can go back to 
		//pick their character again
		else if (e.getSource() == back) {
			new AskCharacterFrame();
		}
		
		dispose();
	}
}

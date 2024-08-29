import java.awt.Color;
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
//This program will allow the user to choose their character

public class AskCharacterFrame extends JFrame implements ActionListener {

	JLabel background = new JLabel(new ImageIcon());
	JButton back = new JButton(new ImageIcon());
	JButton chooseMario = new JButton(new ImageIcon());
	JButton chooseYoshi = new JButton(new ImageIcon());
	JButton choosePeach = new JButton(new ImageIcon());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static Character choosenCharacter;
	public static Character mario = new Character(Icons.MARIO, new String[] { "a", "d", " " });
	public static Character yoshi = new Character(Icons.YOSHI, new String[] { "a", "d", " " });
	public static Character princessPeach = new Character(Icons.PRINCESSPEACH, new String[] { "a", "d", " " });
	
	Color btnBlue = new Color(100, 163, 248);
	public AskCharacterFrame() {
		add(background);
		//setLayout(null);
		setTitle("Character");
		setVisible(true);
		ImageIcon backgroundPic = new ImageIcon("images/askCharacter.png");
		Image backgroundPicImg = backgroundPic.getImage();
		Image backgroundPicScaled = backgroundPicImg.getScaledInstance(screenSize.width, screenSize.height,
				Image.SCALE_SMOOTH);
		background.setIcon(new ImageIcon(backgroundPicScaled));
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		background.add(chooseMario);
		ImageIcon marioPic = new ImageIcon("images/mario.png");
		Image marioPicImg = marioPic.getImage();
		Image marioPicScaled = marioPicImg.getScaledInstance(250, 350,
				Image.SCALE_SMOOTH);
		
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
		
		
		chooseMario.setIcon(new ImageIcon(marioPicScaled));
		chooseMario.setBounds(200, 250, 250, 350);
		chooseMario.setBackground(btnBlue);
		chooseMario.setOpaque(true);
		chooseMario.setBorder(new EmptyBorder(0, 0, 0, 0));
		chooseMario.addActionListener(this);
		
		background.add(chooseYoshi);
		ImageIcon yoshiPic = new ImageIcon("images/yoshi.png");
		Image yoshiPicImg = yoshiPic.getImage();
		Image yoshiPicScaled = yoshiPicImg.getScaledInstance(250, 350,
				Image.SCALE_SMOOTH);
		chooseYoshi.setIcon(new ImageIcon(yoshiPicScaled));
		chooseYoshi.setBounds(600, 250, 250, 350);
		chooseYoshi.setBackground(btnBlue);
		chooseYoshi.setOpaque(true);
		chooseYoshi.setBorder(new EmptyBorder(0, 0, 0, 0));
		chooseYoshi.addActionListener(this);
		
		background.add(choosePeach);
		ImageIcon PeachPic = new ImageIcon("images/princessPeach.png");
		Image PeachPicImg = PeachPic.getImage();
		Image PeachPicScaled = PeachPicImg.getScaledInstance(250, 350,
				Image.SCALE_SMOOTH);
		choosePeach.setIcon(new ImageIcon(PeachPicScaled));
		choosePeach.setBounds(1000, 250, 250, 350);
		choosePeach.setBackground(btnBlue);
		choosePeach.setOpaque(true);
		choosePeach.setBorder(new EmptyBorder(0, 0, 0, 0));
		choosePeach.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if the user did not press the back button
		if (e.getSource() != back) {
			//and they chose mario, then the choosen character
			//is mario 
			if (e.getSource() == chooseMario) {
				choosenCharacter = mario;
			}
			//if they chose yoshi, then the choosen character
			//is yoshi
			else if (e.getSource() == chooseYoshi) {
				choosenCharacter = yoshi;
			}
			//if they chose princess peach, then the choosen character
			//is princess peach
			else if (e.getSource() == choosePeach) {
				choosenCharacter = princessPeach;
			}
			//then ask the user what level they want to play 
			//by calling this frame
			new AskLevelFrame();
		}
		
		//if they pressed the back button then lead them
		//back to the title frame
		else {
			new TitleFrame();
		}
		
		dispose();
	}

}
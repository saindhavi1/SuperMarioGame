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
//This will show the title frame of the game
//where the user can either start a new game or look at the
//leaderboard

public class TitleFrame extends JFrame implements ActionListener{

	
	//Setting up the GUI for the title frame
	JLabel background = new JLabel(new ImageIcon());
	JButton newGame = new JButton(new ImageIcon("images/newGame.png"));
	JButton leaderboard = new JButton(new ImageIcon("images/leaderboard.png"));

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public TitleFrame(){
		setSize(screenSize.width, screenSize.height);
		add(background);
		ImageIcon backgroundPic = new ImageIcon("images/background.png");
		Image backgroundPicImg = backgroundPic.getImage();
		Image backgroundPicScaled = backgroundPicImg.getScaledInstance(screenSize.width, screenSize.height,
				Image.SCALE_SMOOTH);
		background.setIcon(new ImageIcon(backgroundPicScaled));
		
		newGame.setBorder(new EmptyBorder(0, 0, 0, 0));
		newGame.setOpaque(true);
		newGame.setBounds(300, 300, 250,125);
		background.add(newGame);
		newGame.addActionListener(this);
		
		leaderboard.setBorder(new EmptyBorder(0, 0, 0, 0));
		leaderboard.setOpaque(true);
		leaderboard.setBounds(750, 300, 250,125);
		leaderboard.addActionListener(this);
		background.add(leaderboard);
		setVisible(true);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if the user wants to play a new game
		//lead to the frame that asks what character they want
		//to play as
		if (e.getSource() == newGame) {
			new AskCharacterFrame();
		}
		//if the user wants to play a new game
		//lead to the frame that displays the leaderboard
		else if (e.getSource() == leaderboard) {
			new LeaderBoardFrame();
		}
		dispose();
	}
}


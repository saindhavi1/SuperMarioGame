//Name: Saindhavi Thevathasan
//Course: ICS3U1 - 04
//This class is a template class for players in the leaderboard
//each player object will have their name and their best time
//this will make it convenient in displaying the top 5 
//players on the leaderboard

public class Player {

	private String name;
	private int time;
	
	//Constructor
	public Player(String name, int time) {
		super();
		this.name = name;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", time=" + time + "]";
	}
	
	
}

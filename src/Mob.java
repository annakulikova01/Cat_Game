
import java.util.Random;

import javax.swing.ImageIcon;

public class Mob implements Runnable {
	
	private String name;
	
	private Cat_MUD cm;
	
	private Room currentRoom;
	
	private long waitTime;
	
	private ImageIcon image;
	
	public Mob(String name, Room currentRoom, Cat_MUD cm, long waitTime, String filename) {
		this.name = name;
		this.currentRoom = currentRoom;
		this.cm = cm;
		this.waitTime = waitTime;
		this.image = new ImageIcon(filename);
	}
	
	//returns a random time within a range of 1000 determined by the value given at object construction
	
	public long getRandomTime() {
		long leftLimit = this.waitTime - 5000L;
		long rightLimit = this.waitTime + 5000L;
		long newRandomTime = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		return newRandomTime;
	}
	
	
	//while the cat mob is running, each thread moves to a room and then sleeps for an unspecified amount of milliseconds
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (this.cm.isRunning() == true) 
			{
				mobGoRoom();
				Thread.sleep(getRandomTime());
			}
		}
		catch (InterruptedException ex) 
		{
			ex.printStackTrace();
		}
		
	}

	public String getName() {
		return this.name;
	}

	public Room getRoom() {
		return this.currentRoom;
	}
	
	//changes the mob's current room to the room that is returned from the goRandomExit method in the room class,
	//and relays the mob's name and new room to the MUD
	public void mobGoRoom() {
		Room r = this.currentRoom.goRandomExit();
		this.currentRoom = r;
		this.cm.notifyMobRoomChange(this.name, r);
		//System.out.println("Mob " + getName() + " is in the " + this.currentRoom.getRoomName());
		
	}
}


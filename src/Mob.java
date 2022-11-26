
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
	
	//Random ramdomTime = new Random();
	
	public long getRandomTime() {
		long leftLimit = this.waitTime - 5000L;
		long rightLimit = this.waitTime + 5000L;
		long newRandomTime = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		return newRandomTime;
	}
	
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
	
	public void mobGoRoom() {
		Room r = this.currentRoom.goRandomExit();
		this.currentRoom = r;
		this.cm.notifyMobRoomChange(this.name, r);
		System.out.println("Mob " + getName() + " is in the " + this.currentRoom.getRoomName());
		
	}
}


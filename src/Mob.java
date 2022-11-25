
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
		image = new ImageIcon(filename);
	}
	
	//Random ramdomTime = new Random();
	
	public long getRandomTime() {
		long leftLimit = waitTime - 5000L;
		long rightLimit = waitTime + 5000L;
		long newRandomTime = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		return newRandomTime;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (cm.isRunning() == true) 
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
		return name;
	}
	
	public void mobGoRoom() {
		Room r = currentRoom.goRandomExit();
		currentRoom = r;
		// maybe pass the mob instead
		cm.notifyMobRoomChange(name, r);
		System.out.println("Mob " + getName() + " is in the " + currentRoom.getRoomName());
		
	}
}


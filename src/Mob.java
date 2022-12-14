
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.ImageIcon;

public class Mob implements Runnable, Observer {
	
	private String name;
	
	private Cat_MUD cm;
	
	private Cat cat;
	
	
	
	private Room currentRoom;
	
	private long waitTime;
	
	private boolean mobGoToCat;
	
	private ImageIcon image;
	
	public Mob(String name, Room currentRoom, Cat_MUD cm, Cat cat, long waitTime, String filename) {
		this.name = name;
		this.currentRoom = currentRoom;
		this.cm = cm;
		this.cat = cat;
		
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
		this.cm.notifyMobRoomChangeCat(this.name, r);
		
		//System.out.println("Mob " + getName() + " is in the " + this.currentRoom.getRoomName());
		
	}
	
	public void mobGoToCat(Room catRoom) {
		Room r = catRoom;
		this.currentRoom = r;
		this.cm.notifyMobRoomChangeCat(this.name,r);
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try{
			mobGoToCat = true;
			mobGoToCat(cat.getCurrentRoom());
			Thread.sleep(getRandomTime());
		}catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}
}


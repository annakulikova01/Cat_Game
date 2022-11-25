import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Room {
	
	private String name;
	private String description;
	private ImageIcon image;
	
	public Room(String aName, String aDescription, String filename){
		name = aName;
		description = aDescription;
		image = new ImageIcon(filename);
		
	}

	private ArrayList<Item> items = new ArrayList<>();
	
	private ArrayList<Exit> exits = new ArrayList<>();
	
	public Room go(String exit) {
		for (Exit e : exits) {
			if (e.getDirection().equals(exit)) {
				return e.getToRoom();
			}
		}
		return null;
	}
	
	Random random = new Random();
	
	public Room goRandomExit() {
		int randomExit = random.nextInt(2);
		Exit newExit = exits.get(randomExit);
		return newExit.getToRoom();
		
	}
	
	public String getRoomName() {
		return name;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	public String roomDescription() {
		return description;
	}
	public Item giveItem(String it) {
		for (Item item : items) {
			if (item.getName().equals(it)) {
				items.remove(item);
				return item;
			}
		}
		return null;
	}
	
	public void addItem(Item it) {
		items.add(it);
	}
	
	public void addExit(Exit toRoom) {
		exits.add(toRoom);
	}
	
	public String getExitsList() {
			String ret = "";
			for (Exit e : exits) {
				ret = ret + e.getDirection() + " ";
			}
			return ret;
		}
		
	public ArrayList<Exit> getExits() {
		return exits;
	}
	public String getRoomItemsList() {
			String ret = "";
			for (Item i : items) {
				ret = ret + i.getName() + " ";
		}
		return ret;
	}		
}
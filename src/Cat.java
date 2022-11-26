import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Cat extends Observable implements Observer {
	private Room currentRoom;
	
	private Cat_MUD cm;
	
	private Boolean inRoomWithMob1;
	
	private Boolean inRoomWithMob2;

	public ArrayList<Item> itemsHeld = new ArrayList<>();
	
	public Cat(Room currentRoom, Cat_MUD cm) {
		this.currentRoom = currentRoom;
		this.cm = cm;
		this.inRoomWithMob1 = false;
		this.inRoomWithMob2 = false;
	}
	
	public Boolean getItem(String item) {
		Item it = this.currentRoom.giveItem(item);
		if (it != null) {
			this.itemsHeld.add(it);
			return true;
		} else 
			return false;
	}
	
	public Boolean go(String exit) {
		Room r = this.currentRoom.go(exit);
		if (r == null) {
			return false;
		} else {
			this.currentRoom = r;
			this.cm.notifyCatRoomChange();
			
			return true;
		}
	}
	public String getItemsList() {
		String ret = "";
		for (Item i : this.itemsHeld) {
			ret = ret + i.getName() + " ";
		}
	return ret;
	}
	
	public String observe() {
		String l = "In: " + this.currentRoom.getRoomName() + "\n";
		l = l + this.getRoomDescription() + "\n";
		l = l + " Holding: " + this.getItemsList() + "\n";
		l = l + " In room: " + this.currentRoom.getRoomItemsList() + "\n";
		l = l + " Exits: " + this.getRoomExitsList() + "\n";
		return l;
	}
	
	public String autoLookText() {
		String l = this.getRoomDescription() + "\n";
		l = l + " In room: " + this.currentRoom.getRoomItemsList() + "\n";
		l = l + " Exits: " + this.getRoomExitsList() + "\n";
		return l;
	}
	

	private String getRoomExitsList() {
		return this.currentRoom.getExitsList();
	}
	
	//gets an array of exit directions for the current room
	public String[] getCurrentRoomExitsDirection() {
		String direction1 = "";
		String direction2 = "";
		ArrayList<Exit> currentRoomExits = this.currentRoom.getExits();
		direction1 = currentRoomExits.get(0).getDirection();
		direction2 = currentRoomExits.get(1).getDirection();
		String[] exitDirections = new String[2];
		exitDirections[0] = new String(direction1);
		exitDirections[1] = new String(direction2);
		return exitDirections;
	}
	
	//returns the direction for the first exit in the array
	public String getRoomButton1Direction() {
		String[] roomExitDirections = getCurrentRoomExitsDirection();
		String room1Direction = roomExitDirections[0];
		return room1Direction;
		
	}
	
	//returns the direction for the second exit in the array
	public String getRoomButton2Direction() {
		String[] roomExitDirections = getCurrentRoomExitsDirection();
		String room1Direction = roomExitDirections[1];
		return room1Direction;
		
	}
	
	
	//gets an array of images corresponding to the exits of the current room 
	public ImageIcon[] getCurrentRoomExitsImage() {
		ImageIcon image1;
		ImageIcon image2;
		ArrayList<Exit> currentRoomExits = this.currentRoom.getExits();
		image1 = currentRoomExits.get(0).getButton();
		image2 = currentRoomExits.get(1).getButton();
		ImageIcon[] exitImages = new ImageIcon[2];
		exitImages[0] = image1;
		exitImages[1] = image2;
		return exitImages;
		
	}
	//returns the image corresponding to the first exit in the current room's exits
	public ImageIcon getRoomButton1Image() {
		ImageIcon[] buttonImages = getCurrentRoomExitsImage();
		ImageIcon button1Image = buttonImages[0];
		return button1Image;
	}
	
	//returns the image corresponding to the second exit in the current room's exits
	public ImageIcon getRoomButton2Image() {
		ImageIcon[] buttonImages = getCurrentRoomExitsImage();
		ImageIcon button2Image = buttonImages[1];
		return button2Image;
	}
	
	
	public String[] getCurrentRoomExitsDescription() {
		String exit1 = "";
		String exit2 = "";
		ArrayList<Exit> currentRoomExits = this.currentRoom.getExits();
		exit1 = currentRoomExits.get(0).getDescription() + "";
		exit2 = currentRoomExits.get(1).getDescription() + "";
		String[] exitDescriptions = new String[2];
		exitDescriptions[0] = new String(exit1);
		exitDescriptions[1] = new String(exit2);
		return exitDescriptions;
		
	}
	
	public String getRoomButton1Name() {
		String[] roomExitDescriptions = getCurrentRoomExitsDescription();
		String room1Description = roomExitDescriptions[0];
		return room1Description;
	}
	
	public String getRoomButton2Name() {
		String[] roomExitDescriptions = getCurrentRoomExitsDescription();
		String room2Description = roomExitDescriptions[1];
		return room2Description;
	}
	
	public String getRoomDescription() {
		return this.currentRoom.roomDescription();
	}
	
	public ImageIcon getRoomImage() {
		return this.currentRoom.getImage();
	}
	public Boolean dropItem(String it) {
		for (Item item : this.itemsHeld) {
			if (item.getName().equals(it)) {
				this.itemsHeld.remove(item);
				this.currentRoom.addItem(item);
				return true;
			} 
		} 
		return false;
	}

	public Boolean getInRoomWithMob1() {
		return this.inRoomWithMob1;
	}
	
	public Boolean getInRoomWithMob2() {
		return this.inRoomWithMob2;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		MOB_Room changedMobRoom = (MOB_Room) arg;
		String name = changedMobRoom.getMobName();
		Room room = changedMobRoom.getCurrentRoom();
		if (name.equals("1")) {
			if (this.currentRoom.equals(room)) {
				System.out.println("You are in the same room as mob " + name + ".");
				this.inRoomWithMob1 = true;
			} 
			else {
				this.inRoomWithMob1 = false;
			}
		}
		if (name.equals("2")) {
			if (this.currentRoom.equals(room)) {
				System.out.println("You are in the same room as mob " + name + ".");
				this.inRoomWithMob2 = true;
			} 
			else {
				this.inRoomWithMob2 = false;
			}
		}
		setChanged();
		notifyObservers();
	}
}
	